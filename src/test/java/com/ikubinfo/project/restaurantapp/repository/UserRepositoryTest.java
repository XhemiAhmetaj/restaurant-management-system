package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save_ReturnSavedUser(){
        User user = User.builder().name("User Test")
                .lastname("User Test Lastname")
                .email("usertest@email.com")
                .password("usertest")
                .birthday(LocalDate.parse("1997-12-01"))
                .phoneNumber("0692255441")
                .build();
        User savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
