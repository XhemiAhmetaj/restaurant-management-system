package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Drink;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.domain.mapper.ProductMapper;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.repository.DrinkRepository;
import com.ikubinfo.project.restaurantapp.repository.ProductRepository;
import com.ikubinfo.project.restaurantapp.service.CategoryService;
import com.ikubinfo.project.restaurantapp.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper.toDto;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository repository;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public List<DrinkDTO> getDrinks(){
        return repository.findAll().stream().map(DishMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public DrinkDTO addDrink(Long categoryId, DrinkDTO dto){
        Category category = categoryService.findCategoryById(categoryId);
        Drink drink = repository.save(DishMapper.toEntity(category,dto));
        productRepository.save(ProductMapper.buildProduct(drink));
        return toDto(drink);
    }
}
