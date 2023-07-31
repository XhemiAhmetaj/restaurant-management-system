package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.CATEGORY_NOT_FOUND;
import static com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper.toDto;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format(CATEGORY_NOT_FOUND,id)));
    }

    @Override
    public CategoryDTO addCategory(Long catId, CategoryDTO categoryDTO) {
        Category cat = DishMapper.toEntity(categoryDTO);
        if(catId!=null){
            cat.setCategoryParent(categoryRepository.findById(catId).orElseThrow(()-> new ResourceNotFoundException(format(CATEGORY_NOT_FOUND,catId))));
        }else {
            cat.setCategoryParent(null);
        }
        return toDto(categoryRepository.save(cat));
    }

    @Override
    public List<CategoryDTO> listCategories() {
        return categoryRepository.findAll().stream().map(DishMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(c->{
            c.setDeleted(true);
            categoryRepository.save(c);
        },null);
        return null;
    }
}
