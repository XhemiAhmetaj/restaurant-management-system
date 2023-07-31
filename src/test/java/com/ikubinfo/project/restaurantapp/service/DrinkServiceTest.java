package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Measurement;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.repository.DrinkRepository;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
public class DrinkServiceTest {

    @MockBean
    private DrinkRepository drinkRepository;

    @Autowired
    private DrinkService drinkService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void DrinkService_CreateDrink_ReturnDrink(){
        Category category = new Category();
        category.setName("Category");
        category.setCategoryParent(null);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Drink drink = new Drink();
        drink.setName("Drink");
        drink.setPrice(200.0);
        drink.setCategory(category);

        when(drinkRepository.save(any(Drink.class))).thenReturn(drink);

        Product product = new Product();
        product.setName(drink.getName());
        product.setMeasurement(Measurement.MILILITER);
        product.setProductQuantity(10);
        product.setProductWeight(330.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        drink.setProduct(product);

        DrinkDTO drinkDTO = DrinkDTO.builder()
                .productName("Drink")
                .price(250.0)
                .build();

        when(drinkRepository.save(any(Drink.class))).thenReturn(drink);
        DrinkDTO savedDrink = drinkService.addDrink(1L,drinkDTO);
        assertNotNull(savedDrink);
    }

    @Test
    public void DrinkService_GetAllDrinks_ReturnDrinks(){
        List<Drink> drinks = new ArrayList<>();
        when(drinkRepository.findAll()).thenReturn(drinks);
        List<DrinkDTO> out = drinkService.getDrinks();
        assertNotNull(out);
    }

    @Test
    public void DrinkService_GetDrinkById_ReturnDrink(){
        Category category = new Category();
        category.setName("Category");
        category.setCategoryParent(null);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Drink drink = new Drink();
        drink.setName("Drink");
        drink.setPrice(200.0);
        drink.setCategory(category);

        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

        Drink savedDrink = drinkService.getDrinkById(1L);
        assertNotNull(savedDrink);
    }

    @Test
    public void DrinkService_DeleteDrink_ReturnDrink(){
        Category category = new Category();
        category.setName("Category");
        category.setCategoryParent(null);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Drink drink = new Drink();
        drink.setName("Drink");
        drink.setPrice(200.0);
        drink.setCategory(category);

        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

        drinkService.deleteDrink(1L);

        assertNotNull(drink);

    }

}
