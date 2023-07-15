package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.MenuDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.ReservationDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.RestaurantTableDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Menu;
import com.ikubinfo.project.restaurantapp.domain.entity.Reservation;
import com.ikubinfo.project.restaurantapp.domain.entity.RestaurantTable;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.TableStatus;

import java.util.stream.Collectors;

public class RestaurantMapper {

    public static RestaurantTableDTO toDto(RestaurantTable table){
        return RestaurantTableDTO.builder()
                .id(table.getId())
                .tableId(table.getTableId())
                .capacity(table.getCapacity())
                .description(table.getDescription())
                .status(table.getTableStatus().getValue())
                .build();
    }

    public static RestaurantTable toEntity(RestaurantTableDTO restaurantTableDTO){
        return RestaurantTable.builder()
                .tableId(restaurantTableDTO.getTableId())
                .capacity(restaurantTableDTO.getCapacity())
                .description(restaurantTableDTO.getDescription())
                .tableStatus(TableStatus.fromValue(restaurantTableDTO.getStatus()))
                .build();
    }


    public static ReservationDTO toDto(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .comment(reservation.getComment())
                .tableId(reservation.getTable().getTableId())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .build();
    }

    public static Reservation createReservation(Reservation reservation, ReservationDTO reservationDTO, RestaurantTable table){
        reservation.setComment(reservationDTO.getComment());
        reservation.setDate(reservationDTO.getDate());
        reservation.setTime(reservationDTO.getTime());
        reservation.setTable(table);
        return reservation;
    }

//    public static MenuDTO toDto(Menu menu){
//        return MenuDTO.builder()
//                .id(menu.getId())
//                .categoryDTOList(menu.getCategories()!=null?menu.getCategories().stream().map(DishMapper::toDto).collect(Collectors.toList()):null)
//                .build();
//    }


}
