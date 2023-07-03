package com.ikubinfo.project.restaurantapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDTO {

    private Long id;
    @NotNull(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    private double price;
//    @JsonIgnore
//    private List<DishIngredientDTO> dishIngredients;
}
