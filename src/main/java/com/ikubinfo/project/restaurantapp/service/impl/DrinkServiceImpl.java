package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.DrinkDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.Drink;
import com.ikubinfo.project.restaurantapp.domain.entity.Product;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
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

import static com.ikubinfo.project.restaurantapp.domain.Constants.DRINK_DELETED;
import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.DRINK_NOT_FOUND;
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
        Product product = productRepository.save(ProductMapper.buildProduct(drink));
        drink.setProduct(product);
        repository.save(drink);
        return toDto(drink);
    }

    @Override
    public Drink getDrinkById(Long drinkId){
        return repository.findById(drinkId).orElseThrow(()->new ResourceNotFoundException(String.format(DRINK_NOT_FOUND,drinkId)));
    }

    @Override
    public String deleteDrink(Long drinkId){
        repository.findById(drinkId).ifPresentOrElse(d->{
            d.setDeleted(true);
            repository.save(d);
        },null);
        return String.format(DRINK_DELETED,drinkId);
    }


}
