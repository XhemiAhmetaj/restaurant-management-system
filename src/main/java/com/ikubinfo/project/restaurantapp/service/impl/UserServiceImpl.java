package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.UserRole;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.repository.specification.UserSpecification;
import com.ikubinfo.project.restaurantapp.service.EmailSenderService;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.configuration.SecurityConfig.getJwt;
import static com.ikubinfo.project.restaurantapp.domain.Constants.*;
import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.USER_NOT_FOUND;
import static com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper.toDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format(USER_NOT_FOUND, email)));
    }

    @Override
    public UserDTO findById(Long id) {
        return toDto(userRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException(format(USER_NOT_FOUND,id))));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO registerUser(UserDTO req, String userRole) {
        User u = UserMapper.toEntity(req);
        u.setRole(userRole!=null? UserRole.fromValue(userRole): UserRole.CUSTOMER);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setTotalPoints(0);
        u = userRepository.save(u);

        return toDto(u);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(u->{
            u.setDeleted(true);
            u.setTotalPoints(0);
            userRepository.save(u);
        },null);

    }

    @Override
    public User getUserFromToken(Jwt jwt) {
        String sub = (String) jwt.getClaims().get("sub");
        return userRepository.findByEmail(sub).get();
    }

    @Override
    public List<UserDTO> findUserByRole(String role) {
        return userRepository.findUsersByRole(UserRole.fromValue(role)).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserUpdatedDTO updateUser(UserUpdatedDTO req) {
        User u = getUserFromToken(getJwt());
        UserMapper.buildUpdateUser(u,req);
        if(!(req.getPassword().isEmpty() || req.getPassword().isBlank())) {
            u.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        return UserMapper.toUpdateDto(userRepository.save(u));
    }

    @Override
    public Page<UserDTO> findUserByRole(String role, PageParameterDTO pageDTO) {
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),pageDTO.getPageSize(),sort);
        return userRepository.findUserByRole(UserRole.fromValue(role),pageable).map(UserMapper::toDto);
    }

    @Override
    public Page<UserDTO> filterUsers(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO) {
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),pageDTO.getPageSize(),sort);
        if(searchCriteria!=null && searchCriteria.size()>0){
            var userSpec = UserSpecification.toSpecification(searchCriteria);
            return userRepository.findAll(userSpec,pageable).map(UserMapper::toDto);
        }else {
            return userRepository.findAll(pageable).map(UserMapper::toDto);
        }
    }

    @Scheduled(cron = "0 0 0 L * ?") //last day of every month
    @Override
    public void getOffer(){
        List<User> users = userRepository.findTopTenUsersByTotalPoint(PageRequest.of(0,10));
        Random random = new Random();
        int elements = 2;
        for(int i=0; i<elements; i++){
            int rand = random.nextInt(users.size());
            User randomUser = users.get(rand);
            users.remove(rand);

            emailService.sendEmail(randomUser.getEmail(), WINNER_EMAIL_SUBJECT,
                    "Dear "+randomUser.getName() +
                    WINNER_EMAIL);
        }

    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void getBirthdayOffer(){
        List<User> users = userRepository.findUsersByBirthdayIsToday();
        for (User user : users) {
            emailService.sendEmail(user.getEmail(), BIRTHDAY_EMAIL_SUBJECT,
                    format(BIRTHDAY_EMAIL_BODY,user.getName()));
        }
    }


}
