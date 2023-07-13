package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.UserRole;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper;
import com.ikubinfo.project.restaurantapp.repository.OrderRepository;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
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
                        ()->new ResourceNotFoundException("User not found"));
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
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
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

    @Override
    public Integer getUserPoints(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found!"));
        user.setTotalPoints(user.getOrders().stream().map(o-> o.getReceipt().getPoints()).mapToInt(Integer::intValue).sum());
        userRepository.save(user);
        return user.getTotalPoints();
    }


}
