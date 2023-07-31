package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDrinkItemDTO {
    private Long id;
    @NotNull(message = "Quantity is required!")
    @NotEmpty
    private Integer quantity;
    @NotNull(message = "Drink is required!")
    @NotEmpty
    private Long drinkId;
}
