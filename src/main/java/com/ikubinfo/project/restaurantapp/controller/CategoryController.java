package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.addCategory(null, categoryDTO));
    }
    @PostMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.addCategory(categoryId, categoryDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long categoryId){
        CategoryDTO dto = DishMapper.toDto(categoryService.findCategoryById(categoryId));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
