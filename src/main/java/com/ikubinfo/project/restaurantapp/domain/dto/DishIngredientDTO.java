package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishIngredientDTO {
    private Long id;
    private String measurement;
    private Double measure;
}
