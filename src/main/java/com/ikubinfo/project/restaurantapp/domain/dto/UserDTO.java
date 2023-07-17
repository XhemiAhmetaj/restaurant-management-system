package com.ikubinfo.project.restaurantapp.domain.dto;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Lastname is required")
    private String lastname;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid format")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;
    private LocalDate birthday;

    private Integer totalPoints;
}
