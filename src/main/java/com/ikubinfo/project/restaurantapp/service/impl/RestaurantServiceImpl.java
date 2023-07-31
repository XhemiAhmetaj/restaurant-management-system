package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.AddMenuItem;
import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.MenuItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.MenuStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.BadRequestException;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.*;
import com.ikubinfo.project.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.*;
import static java.lang.String.format;


@Service
@RequiredArgsConstructor @Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final MenuRepository menuRepository;
    private final RestaurantTableRepository repository;
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;
    private final DrinkRepository drinkRepository;
    private final MenuItemRepository menuItemRepository;


    @Override
    public MenuDTO addMenuItem(AddMenuItem addMenuItem){
        Menu menu = new Menu();
        MenuItem menuItem;
        List<Menu> menuList = menuRepository.findAllByStatus(MenuStatus.CREATED);

        log.info("-----menuList----" + menuList.size());
        if(!menuList.isEmpty()){
            menu = menuList.get(0);
            menuRepository.save(menu);
        }else{
            menu.setStatus(MenuStatus.CREATED);
            menuRepository.save(menu);

        }

        menuItem = buildMenuItem(addMenuItem, menu);
        menuItemRepository.save(menuItem);
        menu.getItems().add(menuItem);
        menuRepository.save(menu);
        return RestaurantMapper.toDto(menu);
    }

    @Override
    public MenuDTO saveMenu(Long menuId){
        Menu menu = menuRepository.findById(menuId).orElseThrow(()-> new ResourceNotFoundException(format(MENU_NOT_FOUND, menuId)));
        if (menu.getStatus().equals(MenuStatus.CREATED)){
            List<Menu> menuList = menuRepository.findAllByStatus(MenuStatus.ACTIVE);
            for(Menu menuListItem : menuList){
                menuListItem.setStatus(MenuStatus.DEACTIVATED);
            }
            menu.setStatus(MenuStatus.ACTIVE);
            menuRepository.save(menu);
            return RestaurantMapper.toDto(menu);
        }else {
            throw new ResourceNotFoundException(format(MENU_NOT_FOUND,menuId));
        }
    }

    public MenuItem buildMenuItem(AddMenuItem addMenuItem, Menu menu ){
        MenuItem menuItem = new MenuItem();
            log.info("------- Perpara ------");
            if(addMenuItem.getDrinkId()==null){
                log.info("----- Brenda metodes1----");
            menuItem.setMenu(menu);
            menuItemRepository.save(menuItem);
            Dish dish = dishRepository.findById(addMenuItem.getDishId()).orElseThrow(()->new ResourceNotFoundException(format(DISH_NOT_FOUND, addMenuItem.getDishId())));
            menuItem.setDish(dish);
            menuItem.setDrink(null);
            log.info("-----menuItem.get-----"+ menuItem.getId());
        }
        else if(addMenuItem.getDishId()==null){
            menuItem.setMenu(menu);
            Drink drink = drinkRepository.findById(addMenuItem.getDrinkId()).orElseThrow(()-> new ResourceNotFoundException(format(DRINK_NOT_FOUND,addMenuItem.getDrinkId())));
            menuItem.setDish(null);
            menuItem.setDrink(drink);
            menuItemRepository.save(menuItem);

            }else{
            throw new BadRequestException(format(ADD_ITEM_BAD_REQUEST));
        }

        menuItemRepository.save(menuItem);
        return menuItem;

    }


    @Override
    public List<MenuDTO> getMenu() {
//        return categoryRepository.findAll().stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
        return menuRepository.findAllByStatus(MenuStatus.ACTIVE).stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RestaurantTableDTO addTable(RestaurantTableDTO dto) {
        RestaurantTable table = RestaurantMapper.toEntity(dto);
        return RestaurantMapper.toDto(repository.save(table));
    }

    @Override
    public List<RestaurantTableDTO> listAllTables() {
        return repository.findAll()
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public RestaurantTableDTO updateTable(Long id, String status) {
//        RestaurantTable table = repository.findById(id).orElseThrow(
//                () -> new UsernameNotFoundException(format(TABLE_NOT_FOUND,id)));
//        table.setTableStatus(TableStatus.fromValue(status));
//        return RestaurantMapper.toDto(repository.save(table));
//    }

//    @Override
//    public List<RestaurantTableDTO> listTablesByStatus(String status) {
//        return repository.findRestaurantTablesByTableStatus(TableStatus.valueOf(status))
//                .stream()
//                .map(RestaurantMapper::toDto)
//                .collect(Collectors.toList());
//    }

    //****to change, parameter -- datetime , will list all tables available in that timestamp
    @Override
    public List<RestaurantTableDTO> listAllAvailableTables(LocalDateTime dateTime) {

        return null;
    }

    @Override
    public List<RestaurantTableDTO> listTablesByCapacity(Integer capacity) {
        return repository.findRestaurantTablesByCapacity(capacity)
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }


}
