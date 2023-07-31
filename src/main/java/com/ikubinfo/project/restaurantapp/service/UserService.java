package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);
    List<UserDTO> findAllUsers();
    UserDTO registerUser(UserDTO req, String userRole);
    void deleteUser(Long id);
    User getUserFromToken(Jwt jwt);
    List<UserDTO> findUserByRole(String role);
    UserUpdatedDTO updateUser( UserUpdatedDTO u);

    Page<UserDTO> findUserByRole (String role, PageParameterDTO pageDTO);
    Page<UserDTO> filterUsers(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO);

    void getOffer();
    void getBirthdayOffer();
}
