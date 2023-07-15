package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.UserRole;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper;
import com.ikubinfo.project.restaurantapp.repository.OrderRepository;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import com.ikubinfo.project.restaurantapp.repository.specification.GenereticSpecification;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.repository.specification.UserSpecification;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.USER_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", email)));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException(format(USER_NOT_FOUND,id)));
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

        return UserMapper.toDto(u);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(u->{
            u.setDeleted(true);
            userRepository.save(u);
        },()->new ResourceNotFoundException(format(USER_NOT_FOUND,id)));

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
    public UserUpdatedDTO updateUser(Long id, UserUpdatedDTO req) {
        User u = findById(id);
        u = UserMapper.buildUpdateUser(u,req);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        return UserMapper.toUpdateDto(userRepository.save(u));
    }

//    @Override
//    public Page<User> findAllCustomers(PageParameterDTO pageParams) {
//        return  userRepository.findUsersByRole_Customer(
//                pageParams.getSortDirection().isAscending()
//                        ?
//                        PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize()
//                                , Sort.Direction.ASC, pageParams.getSort())
//                        :
//                        PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize()
//                                , Sort.Direction.DESC, pageParams.getSort()));
//    }

    @Override
    public Page<UserDTO> findUserByRole(String role, PageParameterDTO pageDTO) {
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),
                pageDTO.getPageSize(),sort);
        return userRepository.findUserByRole(UserRole.fromValue(role),pageable).map(UserMapper::toDto);
    }

    @Override
    public Page<UserDTO> filterUsers(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO) {
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),
                pageDTO.getPageSize(),sort);
        if(searchCriteria!=null && searchCriteria.size()>0){
            var userSpec = UserSpecification.toSpecification(searchCriteria);
            return userRepository.findAll(userSpec,pageable).map(UserMapper::toDto);
        }else {
            return userRepository.findAll(pageable).map(UserMapper::toDto);
        }
    }


}
