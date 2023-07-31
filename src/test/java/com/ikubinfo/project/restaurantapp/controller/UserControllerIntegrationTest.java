package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.service.UserService;
import com.ikubinfo.project.restaurantapp.service.UserServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldRegisterUser(){
    }

    @Test
    void findUser(){
    }

    @Test
    void returnAllUsers(){
    }

    @Test
    void deleteUser(){
    }

    @Test
    void returnUserByRole(){
    }

    @Test
    void updateUser(){
    }

    @Test
    void getUsersByRole(){
    }

    @Test
    void filterUsers(){
    }
}