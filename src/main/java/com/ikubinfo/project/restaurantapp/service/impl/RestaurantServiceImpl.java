package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import com.ikubinfo.project.restaurantapp.repository.MenuRepository;
import com.ikubinfo.project.restaurantapp.repository.RestaurantTableRepository;
import com.ikubinfo.project.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.TABLE_NOT_FOUND;
import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final MenuRepository menuRepository;
    private final RestaurantTableRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<MenuDTO> getMenu() {
//        return categoryRepository.findAll().stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
        return null;
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

    @Override
    public RestaurantTableDTO updateTable(Long id, String status) {
        RestaurantTable table = repository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(format(TABLE_NOT_FOUND,id)));
        table.setTableStatus(TableStatus.fromValue(status));
        return RestaurantMapper.toDto(repository.save(table));
    }

    @Override
    public List<RestaurantTableDTO> listTablesByStatus(String status) {
        return repository.findRestaurantTablesByTableStatus(TableStatus.valueOf(status))
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantTableDTO> listAllAvailableTables() {
        return repository.findRestaurantTablesByTableStatus(TableStatus.AVAILABLE)
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantTableDTO> listTablesByCapacity(Integer capacity) {
        return repository.findRestaurantTablesByCapacity(capacity)
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }


}
