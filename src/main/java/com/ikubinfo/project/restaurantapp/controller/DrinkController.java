package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import com.ikubinfo.project.restaurantapp.service.DishService;
import com.ikubinfo.project.restaurantapp.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final CategoryService categoryService;

    @RolesAllowed({"ADMIN"})
    @PostMapping("/{categoryID}")
    public ResponseEntity<DrinkDTO> addDrink(@RequestBody DrinkDTO drinkDTO, @PathVariable Long categoryID){
        return ResponseEntity.ok(drinkService.addDrink(categoryID,drinkDTO));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{drinkId}")
    public ResponseEntity<String> deleteDrink(@PathVariable Long drinkId){
        return ResponseEntity.ok(drinkService.deleteDrink(drinkId));
    }

    @GetMapping
    public ResponseEntity<List<DrinkDTO>> listAllDrinks(){
        return ResponseEntity.ok(drinkService.getDrinks());
    }


}
