package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.entity.DishIngredient;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.repository.DishIngredientRepository;
import com.ikubinfo.project.restaurantapp.repository.DishRepository;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import com.ikubinfo.project.restaurantapp.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.*;
import static com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper.toDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishIngredientRepository dishIngredient;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(format(DISH_NOT_FOUND,id)));
    }

//    @Override
//    public Category findCategoryById(Long id) {
//        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format(CATEGORY_NOT_FOUND,id)));
//    }

//    @Override
//    public CategoryDTO addCategory(Long catId, CategoryDTO categoryDTO) {
//        Category cat = DishMapper.toEntity(categoryDTO);
//        if(catId!=null){
//            cat.setCategoryParent(categoryRepository.findById(catId).orElseThrow(()-> new ResourceNotFoundException(format(CATEGORY_NOT_FOUND,catId))));
//        }else {
//            cat.setCategoryParent(null);
//        }
//        return toDto(categoryRepository.save(cat));
//    }
//
//    @Override
//    public List<CategoryDTO> listCategories() {
//        return categoryRepository.findAll().stream().map(DishMapper::toDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public Void deleteCategory(Long id) {
//        Category category = findCategoryById(id);
//        categoryRepository.delete(category);
//        return null;
//    }

    @Override
    public List<DishDTO> listAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(DishMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DishUpdatedDTO updateDish(Long id, DishUpdatedDTO dto) {
        Dish dish = findById(id);
        dish = dishRepository.save(DishMapper.buildDish(dish,dto));
        return  DishMapper.updateDishDTO(dish);
    }

    @Override
    public DishDTO addDish(Long categoryId, DishDTO dto) {
        Category category = categoryService.findCategoryById(categoryId);
        Dish dish = dishRepository.save(DishMapper.toEntity(category,dto));
        return toDto(dish);
    }

    @Override
    public Void deleteDish(Long id) {
        try {
            dishRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public DishIngredientDTO addIngredient(Long dishId, DishIngredientDTO ingredientDTO, Long productId) {
        Dish dish = findById(dishId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(format(DISH_NOT_FOUND,dishId)));
        DishIngredient ingredient = DishMapper.toEntity(dish,ingredientDTO, product);
        return toDto(dishIngredient.save(ingredient));
    }


    @Override
    public List<DishIngredientDTO> listDishIngredients(Long id) {
        Dish dish = findById(id);
        return dishIngredient.findDishIngredientsByDish_Id(dish.getId()).stream().map(DishMapper::toDto).collect(Collectors.toList());
    }

}
