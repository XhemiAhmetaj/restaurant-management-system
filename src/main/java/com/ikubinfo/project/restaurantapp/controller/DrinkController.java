package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import com.ikubinfo.project.restaurantapp.service.DishService;
import com.ikubinfo.project.restaurantapp.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final CategoryService categoryService;

    @PostMapping("/{categoryID}")
    public ResponseEntity<DrinkDTO> addDrink(@RequestBody DrinkDTO drinkDTO, @PathVariable Long categoryID){
        return ResponseEntity.ok(drinkService.addDrink(categoryID,drinkDTO));
    }


}
