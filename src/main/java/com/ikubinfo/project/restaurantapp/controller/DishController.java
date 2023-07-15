package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.service.DishService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/category/{categoryId}/add")
    public ResponseEntity<DishDTO> registerDish(@RequestBody @Valid DishDTO req, @PathVariable Long categoryId) {
        DishDTO dto = dishService.addDish(categoryId,req);
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<List<DishDTO>> listAllDishes(){
        return ResponseEntity.ok(dishService.listAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Long id){
        DishDTO dishDTO = DishMapper.toDto(dishService.findById(id));
        return ResponseEntity.ok(dishDTO);
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PutMapping("/{id}")
    public ResponseEntity<DishUpdatedDTO> updateDish(@PathVariable Long id, @RequestBody DishUpdatedDTO dto){
        return ResponseEntity.ok(dishService.updateDish(id,dto));
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id){
        return ResponseEntity.ok(dishService.deleteDish(id));
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/{dishId}/ingredient/{productId}")
    public ResponseEntity<DishIngredientDTO> addIngredient(@PathVariable Long dishId, @RequestBody DishIngredientDTO ingredientDTO, @PathVariable Long productId){
        return ResponseEntity.ok(dishService.addIngredient(dishId,ingredientDTO, productId));
    }

    @GetMapping("/{dishId}/ingredients")
    public ResponseEntity<List<DishIngredientDTO>> listDishIngredients(@PathVariable Long dishId){
        return ResponseEntity.ok(dishService.listDishIngredients(dishId));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(dishService.addCategory(null, categoryDTO));
    }
    @PostMapping("{categoryId}/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
        return ResponseEntity.ok(dishService.addCategory(categoryId, categoryDTO));
    }

    @GetMapping("/list/categories")
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        return ResponseEntity.ok(dishService.listCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long categoryId){
        CategoryDTO dto = DishMapper.toDto(dishService.findCategoryById(categoryId));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(dishService.deleteCategory(categoryId));
    }
}
