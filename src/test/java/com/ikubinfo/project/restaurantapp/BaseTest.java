package com.ikubinfo.project.restaurantapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public class BaseTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mvc;
    protected ObjectMapper mapper;

    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity()).build();
        mapper = new ObjectMapper();

    }

    protected Authentication getAuthentication(String role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        Authentication authToken = new UsernamePasswordAuthenticationToken
                ("admin@email.com", "admin", authorities);
        return  authToken;


    }
}
