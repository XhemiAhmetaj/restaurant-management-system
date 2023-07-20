package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryById(Long id);
    //    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO addCategory(Long id, CategoryDTO categoryDTO);
    List<CategoryDTO> listCategories();
    Void deleteCategory(Long id);
}
