package com.ikubinfo.project.restaurantapp.domain.mapper;


import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.UserUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.User;

public class UserMapper {

    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .lastname(u.getLastname())
                .birthday(u.getBirthday())
                .address(u.getAddress())
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
                .address(u.getAddress())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .password(u.getPassword())
                .build();
    }

    public static UserUpdatedDTO toUpdateDto(User u){
        return UserUpdatedDTO.builder()
                .id(u.getId())
                .address(u.getAddress())
                .password(u.getPassword())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }

    public static User buildUpdateUser(User u, UserUpdatedDTO req){
        u.setAddress(req.getAddress());
        u.setPassword(req.getPassword());
        u.setPhoneNumber(req.getPhoneNumber());
        return u;
    }
}
