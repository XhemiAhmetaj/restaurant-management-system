package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.Status;
import com.ikubinfo.project.restaurantapp.domain.mapper.RestaurantMapper;
import com.ikubinfo.project.restaurantapp.repository.RestaurantTableRepository;
import com.ikubinfo.project.restaurantapp.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository repository;
    @Override
    public RestaurantTableDTO addTable(RestaurantTableDTO dto) {
        return RestaurantMapper.toDto(RestaurantMapper.toEntity(dto));
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
                () -> new UsernameNotFoundException(
                        format("Table not found!")));
        table.setStatus(Status.fromValue(status));
        return RestaurantMapper.toDto(repository.save(table));
    }

    @Override
    public List<RestaurantTableDTO> listTablesByStatus(String status) {
        return repository.findRestaurantTablesByStatus(Status.valueOf(status))
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<RestaurantTableDTO> listAllAvailableTables() {
//        return repository.findRestaurantTablesByStatus(Status.AVAILABLE)
//                .stream()
//                .map(RestaurantMapper::toDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<RestaurantTableDTO> listTablesByCapacity(Integer capacity) {
        return repository.findRestaurantTablesByCapacity(capacity)
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }
}
