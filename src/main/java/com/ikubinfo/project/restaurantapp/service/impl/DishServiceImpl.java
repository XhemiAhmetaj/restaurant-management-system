package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.DishIngredientDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.update.DishUpdatedDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.DishMapper;
import com.ikubinfo.project.restaurantapp.repository.DishRepository;
import com.ikubinfo.project.restaurantapp.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
//    private final DishIngredientDTO dishIngredientDTO;

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Dish not found!"));
    }

    @Override
    public List<DishDTO> listAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(DishMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DishUpdatedDTO updateDish(Long id, DishUpdatedDTO dto) {
        Dish dish = findById(id);
        dish = DishMapper.buildDish(dish,dto);
        return DishMapper.updateDishDTO(dishRepository.save(dish));
    }

    @Override
    public DishDTO addDish(DishDTO dto) {
        return DishMapper.toDto(dishRepository.save(DishMapper.toEntity(dto)));
    }

    @Override
    public void deleteDish(Long id) {
        try {
            dishRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

}
