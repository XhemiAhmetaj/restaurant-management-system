package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Drink;

import java.util.List;

public interface DrinkService {

    List<DrinkDTO> getDrinks();
    DrinkDTO addDrink(Long categoryId, DrinkDTO dto);

    Drink getDrinkById(Long drinkId);
    String deleteDrink(Long drinkId);
}
