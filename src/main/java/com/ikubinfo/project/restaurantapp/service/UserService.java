package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<UserDTO> findAllUsers();
    UserDTO registerUser(UserDTO req, String userRole);
    void deleteUser(Long id);
    User getUserFromToken(Jwt jwt);
    List<UserDTO> findUserByRole(String role);
    UserUpdatedDTO updateUser(Long id, UserUpdatedDTO u);

}
