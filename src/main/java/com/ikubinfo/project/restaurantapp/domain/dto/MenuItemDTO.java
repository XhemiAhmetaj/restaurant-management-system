package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemDTO {

    private Long id;
    private Long dishId;
    private String dishName;
    private Double dishPrice;
    private Long drinkId;
    private String drinkName;
    private Double drinkPrice;
}
