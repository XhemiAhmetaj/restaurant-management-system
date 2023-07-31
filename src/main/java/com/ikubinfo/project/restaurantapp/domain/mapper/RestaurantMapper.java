package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;

import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.DISH_NOT_FOUND;
import static java.lang.String.format;

public class RestaurantMapper {

    public static RestaurantTableDTO toDto(RestaurantTable table){
        return RestaurantTableDTO.builder()
                .id(table.getId())
                .tableId(table.getTableId())
                .capacity(table.getCapacity())
                .description(table.getDescription())
                .build();
    }

    public static RestaurantTable toEntity(RestaurantTableDTO restaurantTableDTO){
        return RestaurantTable.builder()
                .tableId(restaurantTableDTO.getTableId())
                .capacity(restaurantTableDTO.getCapacity())
                .description(restaurantTableDTO.getDescription())
                .build();
    }


    public static ReservationDTO toDto(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .numberOfPeople(reservation.getNumberOfPeople())
                .comment(reservation.getComment())
                .tableId(reservation.getTable().getTableId())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .confirmationName(reservation.getCreatedBy().getName())
                .build();
    }

    public static Reservation createReservation(Reservation reservation, ReservationDTO reservationDTO, RestaurantTable table){
        reservation.setNumberOfPeople(reservationDTO.getNumberOfPeople());
        reservation.setComment(reservationDTO.getComment());
        reservation.setDate(reservationDTO.getDate());
        reservation.setTime(reservationDTO.getTime());
        reservation.setTable(table);
        return reservation;
    }

    public static TableReservationDTO toDto(TableReservation reservation){
        return TableReservationDTO.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .startTime(reservation.getStartTime())
                .tableId(reservation.getTableReservation().getTableId())
                .build();
    }

    public static TableReservation createTableReservation(Reservation reservation, TableReservation tableReservation, RestaurantTable table){
        tableReservation.setDate(reservation.getDate());
        tableReservation.setStartTime(reservation.getTime());
        tableReservation.setTableReservation(table);
        return tableReservation;
    }

    public static MenuDTO toDto(Menu menu){
        return MenuDTO.builder()
                .id(menu.getId())
                .items(menu.getItems().stream().map(RestaurantMapper::toDto).collect(Collectors.toList()))
                .status(menu.getStatus().getValue())
                .build();
    }

    public static MenuItemDTO toDto(MenuItem menuItem){
        if(menuItem.getDish()!=null){
            return MenuItemDTO.builder()
                    .id(menuItem.getId())
                    .dishId(menuItem.getDish().getId())
                    .dishName(menuItem.getDish().getName())
                    .dishPrice(menuItem.getDish().getPrice())
                    .drinkId(null)
                    .build();
        }
        if(menuItem.getDrink()!=null){
            return MenuItemDTO.builder()
                    .id(menuItem.getId())
                    .dishId(null)
                    .drinkId(menuItem.getDrink().getId())
                    .drinkName(menuItem.getDrink().getName())
                    .drinkPrice(menuItem.getDrink().getPrice())
                    .build();
        }
        return null;
    }




}
