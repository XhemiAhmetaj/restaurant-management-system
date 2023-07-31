package com.ikubinfo.project.restaurantapp.domain.dto;

import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkDTO {

    private Long id;
    @NotNull(message = "Name is required")
    private String productName;
    @NotNull(message = "Price is required")
    private Double price;

}
