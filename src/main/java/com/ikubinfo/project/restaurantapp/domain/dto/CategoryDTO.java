package com.ikubinfo.project.restaurantapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    @NotNull(message = "Name is required!")
    @NotEmpty
    private String name;
    private List<CategoryDTO> categoryChild;
    private List<DishDTO> dishes;
    private List<DrinkDTO> drinks;
}
