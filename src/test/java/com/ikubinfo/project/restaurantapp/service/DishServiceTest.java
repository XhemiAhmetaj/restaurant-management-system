package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ProductDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.repository.DishIngredientRepository;
import com.ikubinfo.project.restaurantapp.repository.DishRepository;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DishServiceTest {

    @MockBean
    private DishRepository dishRepository;

    @Autowired
    private DishService dishService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private DishIngredientRepository dishIngredientRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void DishService_CreateDish_ReturnDish(){
        Category category = new Category();
        category.setName("Category");
        category.setCategoryParent(null);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setDescription(" ");
        dish.setPrice(250.0);

        DishDTO dishDTO = DishDTO.builder()
                .name("Dish")
                .description(" ")
                .price(250.0)
                .build();

        when(dishRepository.save(any(Dish.class))).thenReturn(dish);
        DishDTO savedDish = dishService.addDish(1L,dishDTO);
        assertNotNull(savedDish);
    }

    @Test
    public void DishService_GetAllDishes_ReturnDishes(){
        List<Dish> dishes = new ArrayList<>();
        when(dishRepository.findAll()).thenReturn(dishes);
        List<DishDTO> out = dishService.listAllDishes();
        assertNotNull(out);
    }

    @Test
    public void DishService_GetDishById_ReturnDish(){
        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setDescription(" ");
        dish.setPrice(250.0);

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Dish savedDish = dishService.findById(1L);
        assertNotNull(savedDish);
    }

    @Test
    public void DishService_UpdateDish_ReturnDish(){
        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setDescription(" ");
        dish.setPrice(250.0);

        DishUpdatedDTO dishDTO = DishUpdatedDTO.builder()
                .description(" ")
                .price(250.0)
                .build();

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        DishUpdatedDTO savedDish = dishService.updateDish(1L,dishDTO);
        assertNotNull(savedDish);
    }

    @Test
    public void DishService_AddIngredientToDish_ReturnIngredient(){
        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setDescription(" ");
        dish.setPrice(250.0);

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Product product = new Product();
        product.setName("Product");
        product.setMeasurement(Measurement.MILILITER);
        product.setProductQuantity(null);
        product.setProductWeight(500.0);

        ProductDTO productDTO = ProductDTO.builder()
                .name("Product")
                .measurement("MILILITER")
                .productQuantity(null)
                .productWeight(500.0)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        DishIngredient ingredient = new DishIngredient();
        ingredient.setDish(dish);
        ingredient.setProduct(product);
        ingredient.setMeasure(50.0);

        DishIngredientDTO ingredientDTO = DishIngredientDTO.builder()
                .product(productDTO)
                .measure(50.0)
                .build();

        when(dishIngredientRepository.save(any(DishIngredient.class))).thenReturn(ingredient);
        DishIngredientDTO savedIngredient = dishService.addIngredient(1L, ingredientDTO, 1L);
        assertNotNull(savedIngredient);
    }

    @Test
    public void DishService_GetAllIngredients_ReturnIngredients(){
        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setDescription(" ");
        dish.setPrice(250.0);

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setMeasurement(Measurement.MILILITER);
        product1.setProductQuantity(null);
        product1.setProductWeight(500.0);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setMeasurement(Measurement.MILILITER);
        product2.setProductQuantity(null);
        product2.setProductWeight(1000.0);


        DishIngredient ingredient1 = new DishIngredient();
        ingredient1.setDish(dish);
        ingredient1.setProduct(product1);
        ingredient1.setMeasure(50.0);

        DishIngredient ingredient2 = new DishIngredient();
        ingredient2.setDish(dish);
        ingredient2.setProduct(product2);
        ingredient2.setMeasure(100.0);

        List<DishIngredient> ingredients = new ArrayList<>();

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        when(dishIngredientRepository.findAll()).thenReturn(ingredients);
        List<DishIngredientDTO> out = dishService.listDishIngredients(1L);
        assertNotNull(out);
    }

}
