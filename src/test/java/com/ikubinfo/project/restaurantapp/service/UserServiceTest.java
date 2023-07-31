package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.BaseTest;
import com.ikubinfo.project.restaurantapp.domain.dto.PageParameterDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.UserRole;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest extends BaseTest {

    @MockBean
    private UserRepository userRepository;


    @Autowired
    private UserService userService;

    public UserServiceTest(){
    }

    @Test
    public void UserService_CreateUser_ReturnUserDto(){
        User user = User.builder().name("User Test")
                .lastname("User Test Lastname")
                .email("usertest@email.com")
                .password("usertest")
                .birthday(LocalDate.parse("1997-12-01"))
                .phoneNumber("0692255441")
                .build();

        UserDTO userDTO = UserDTO.builder().name("User Test")
                .lastname("User Test Lastname")
                .email("usertest@email.com")
                .password("usertest")
                .birthday(LocalDate.parse("1997-12-01"))
                .phoneNumber("0692255441")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userSaved = userService.registerUser(userDTO, "CUSTOMER");
        assertNotNull(userSaved);
    }

    @Test
    public void UserService_GetAllUsers_ReturnAllUsers(){
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        List<UserDTO> out =userService.findAllUsers();
        assertNotNull(out);
    }

    @Test
    public void UserService_GetUserById_ReturnUser(){
        User user = new User();
        user.setName("User Test");
        user.setLastname("User Test Lastname");
        user.setEmail("usertest@email.com");
        user.setPassword("usertest");
        user.setBirthday(LocalDate.parse("1997-12-01"));
        user.setPhoneNumber("0692255441");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userSaved = userService.findById(1L);
        assertNotNull(userSaved);
    }

//    @Test
//    public void UserService_UpdateUser_ReturnUser(){
//        User user = new User();
//        user.setName("User Test");
//        user.setLastname("User Test Lastname");
//        user.setEmail("usertest@email.com");
//        user.setPassword("usertest");
//        user.setBirthday(LocalDate.parse("1997-12-01"));
//        user.setPhoneNumber("0692255441");
//
//        UserUpdatedDTO userDTO = UserUpdatedDTO.builder()
//                .password("usertest")
//                .phoneNumber("0692255441")
//                .build();
//        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//        when(userRepository.findByEmail("usertest@gmail.com")).thenReturn(Optional.of(user));
//
//        UserUpdatedDTO userSaved = userService.updateUser(userDTO);
//        assertNotNull(userSaved);
//    }


    @Test
    public void UserService_deleteUser_Return(){
        User user = new User();
        user.setName("User Test");
        user.setLastname("User Test Lastname");
        user.setEmail("usertest@email.com");
        user.setPassword("usertest");
        user.setBirthday(LocalDate.parse("1997-12-01"));
        user.setPhoneNumber("0692255441");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        assertNotNull(user);

    }

}
