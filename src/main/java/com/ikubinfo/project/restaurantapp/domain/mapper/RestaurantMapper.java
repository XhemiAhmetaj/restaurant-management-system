package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;

public class RestaurantMapper {

    public static RestaurantTableDTO toDto(RestaurantTable table){
        return RestaurantTableDTO.builder()
                .id(table.getId())
                .tableId(table.getTableId())
                .capacity(table.getCapacity())
//                .description(restaurant.getDescription())
                .status(table.getStatus().getValue())
                .build();
    }

    public static RestaurantTable toEntity(RestaurantTableDTO restaurantTableDTO){
        return RestaurantTable.builder()
                .tableId(restaurantTableDTO.getTableId())
                .capacity(restaurantTableDTO.getCapacity())
//                .description(restaurantTableDTO.getDescription())
                .build();
    }

//    public static TableUpdatedDTO toUpdateDto(TableUpdatedDTO dto){
//        return TableUpdatedDTO.builder()
//                .capacity(dto.getCapacity())
//                .status(dto.getStatus())
//                .build();
//    }
//
//    public static RestaurantTable updateTable(RestaurantTable table,TableUpdatedDTO dto){
//        table.setCapacity(dto.getCapacity());
//        table.setStatus(dto);
//        return table;
//    }

}
