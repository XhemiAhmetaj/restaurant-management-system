package com.ikubinfo.project.restaurantapp.domain.dto.update;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdatedDTO {
    private Long id;
    private String password;
    @NotEmpty
    private String phoneNumber;
}
