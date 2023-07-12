package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;
//    private DishDTO dish;

    private Long dishId;
    private String dishName;
    private String dishDescription;
}
