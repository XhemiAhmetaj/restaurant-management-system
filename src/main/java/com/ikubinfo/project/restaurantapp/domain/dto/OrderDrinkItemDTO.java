package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDrinkItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;
    private Long drinkId;
    private String drinkName;
}
