package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDishItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;
    private Long dishId;
    private String dishName;

}
