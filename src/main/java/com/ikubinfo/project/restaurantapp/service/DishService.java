package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;

import java.util.List;

public interface DishService {

    Dish findById(Long id);
    List<DishDTO> listAllDishes();
    DishUpdatedDTO updateDish(Long id, DishUpdatedDTO dto);
    DishDTO addDish(DishDTO dto);
    void deleteDish(Long id);

}
