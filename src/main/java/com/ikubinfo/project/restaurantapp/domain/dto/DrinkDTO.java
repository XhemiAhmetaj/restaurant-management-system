package com.ikubinfo.project.restaurantapp.domain.dto;

import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkDTO {

    private Long id;
    private String productName;
    private Double price;

}
