package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.entity.DishIngredient;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class DishMapper {

    public static DishDTO toDto(Dish dish){
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .price(dish.getPrice())
//                .dishIngredients(dish.getIngredients()!=null?dish.getIngredients().stream()
//                        .map(d->toDto(d)).collect(Collectors.toList()):null)
                .build();
    }

    public static Dish toEntity(DishDTO dto){
        return Dish.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .createdAt(LocalDateTime.now())
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
                .measurement(dishIngredient.getMeasurement().getValue())
                .measure(dishIngredient.getMeasure())
                .build();
    }

//    public static ProductDTO toDto(Product product){
//        return ProductDTO.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .measurement(product.getMeasurement().getValue())
//                .quantity(product.getQuantity())
//                .build();
//    }
//
//    public static Product toEntity(ProductDTO dto){
//        return Product.builder()
//                .name(dto.getName())
//                .measurement(Measurement.fromValue(dto.getMeasurement()))
//                .quantity(dto.getQuantity())
//                .build();
//    }

}
