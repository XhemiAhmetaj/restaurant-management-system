package com.ikubinfo.project.restaurantapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @JsonIgnore
//    private String name;
//    @JsonIgnore
//    private String measurement;

    private ProductDTO product;
    private Double measure;
}
