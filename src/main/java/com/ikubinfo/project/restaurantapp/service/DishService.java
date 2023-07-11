package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.entity.DishIngredient;

import java.util.List;

public interface DishService {

    Category findCategoryById(Long id);
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> listCategories();
    Void deleteCategory(Long id);

    Dish findById(Long id);
    List<DishDTO> listAllDishes();
    DishUpdatedDTO updateDish(Long id, DishUpdatedDTO dto);
    DishDTO addDish(Long categoryId, DishDTO dto);
    Void deleteDish(Long id);

    DishIngredientDTO addIngredient(Long dishId, DishIngredientDTO ingredientDTO, Long productId);
    List<DishIngredientDTO> listDishIngredients(Long id);

}
