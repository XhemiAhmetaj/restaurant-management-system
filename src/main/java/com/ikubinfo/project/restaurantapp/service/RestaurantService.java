package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.AddMenuItem;
import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.MenuItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Menu;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantService {

    MenuDTO addMenuItem(AddMenuItem menuItem);
    MenuDTO saveMenu(Long menuId);
    List<MenuDTO> getMenu();

    RestaurantTableDTO addTable(RestaurantTableDTO dto);
    List<RestaurantTableDTO> listAllTables();
//    RestaurantTableDTO updateTable(Long id, String status);
//    List<RestaurantTableDTO> listTablesByStatus(String status);
    List<RestaurantTableDTO> listAllAvailableTables(LocalDateTime datetime);
    List<RestaurantTableDTO> listTablesByCapacity(Integer capacity);
}
