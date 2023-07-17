package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<MenuDTO> getMenu();

    RestaurantTableDTO addTable(RestaurantTableDTO dto);
    List<RestaurantTableDTO> listAllTables();
    RestaurantTableDTO updateTable(Long id, String status);
    List<RestaurantTableDTO> listTablesByStatus(String status);
    List<RestaurantTableDTO> listAllAvailableTables();
    List<RestaurantTableDTO> listTablesByCapacity(Integer capacity);
}
