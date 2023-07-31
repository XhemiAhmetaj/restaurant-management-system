package com.ikubinfo.project.restaurantapp.domain.mapper;


import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .lastname(u.getLastname())
                .birthday(u.getBirthday())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .totalPoints(u.getTotalPoints())
                .build();
    }

    public static User toEntity(UserDTO u){
        return User.builder()
                .name(u.getName())
                .lastname(u.getLastname())
                .birthday(u.getBirthday())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .password(u.getPassword())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static UserUpdatedDTO toUpdateDto(User u){
        return UserUpdatedDTO.builder()
                .id(u.getId())
                .password(u.getPassword())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static User buildUpdateUser(User u, UserUpdatedDTO req){
        u.setPhoneNumber(req.getPhoneNumber());
        u.setLastModifiedDate(LocalDateTime.now());
        return u;
    }
}
