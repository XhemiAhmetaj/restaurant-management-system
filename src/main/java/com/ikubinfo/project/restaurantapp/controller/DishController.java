package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.DishDTO;
import com.ikubinfo.project.restaurantapp.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @PostMapping("/add")
    public ResponseEntity<DishDTO> registerUser(@RequestBody @Valid DishDTO req) {
        DishDTO dto = dishService.addDish(req);
        return ResponseEntity.ok(dto);

    }
}
