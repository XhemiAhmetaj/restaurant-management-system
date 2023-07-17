package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

}
