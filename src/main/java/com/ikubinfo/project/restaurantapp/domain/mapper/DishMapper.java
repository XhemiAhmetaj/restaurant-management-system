package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.entity.DishIngredient;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;

import java.util.stream.Collectors;

public class DishMapper {

    public static DishDTO toDto(Dish dish){
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .dishIngredients(dish.getIngredients()!=null?dish.getIngredients().stream()
                        .map(DishMapper::toDto).collect(Collectors.toList()):null)
                .build();
    }

    public static Dish toEntity(Category category, DishDTO dto){
        return Dish.builder()
                .name(dto.getName())
                .category(category)
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }

    public static DishUpdatedDTO updateDishDTO(Dish d){
        return DishUpdatedDTO.builder()
                .description(d.getDescription())
                .price(d.getPrice())
                .build();
    }

    public static Dish buildDish(Dish dish, DishUpdatedDTO dto){
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        return dish;
    }

    public static DishIngredientDTO toDto(DishIngredient dishIngredient){
        return DishIngredientDTO.builder()
                .id(dishIngredient.getId())
                .product(ProductMapper.toDto(dishIngredient.getProduct()))
                .measure(dishIngredient.getMeasure())
                .build();
    }

    public static DishIngredient toEntity(Dish dish, DishIngredientDTO ingredientDTO, Product product){
        return DishIngredient.builder()
                .id(ingredientDTO.getId())
                .dish(dish)
                .product(product)
                .measure(ingredientDTO.getMeasure())
                .build();
    }

    public static CategoryDTO toDto(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .categoryParent(category.getCategoryParent()!=null?DishMapper.toDto(category.getCategoryParent()):null)
                .dishes(category.getDishes()!=null?category.getDishes().stream().map(DishMapper::toDto).collect(Collectors.toList()) : null)
                .build();
    }

    public static Category toEntity(CategoryDTO dto){
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
//                .dishes()
                .build();
    }


}
