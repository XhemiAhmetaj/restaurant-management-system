package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;

import java.util.List;

public interface DrinkService {

    List<DrinkDTO> getDrinks();
    DrinkDTO addDrink(Long categoryId, DrinkDTO dto);
}
