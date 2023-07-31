package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderDishItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderDrinkItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderItemDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .orderItems(order.getOrderItems()!=null?order.getOrderItems().stream().map(OrderMapper::toDto).collect(Collectors.toList()) : null)
                .totalAmount(order.getOrderItems().stream().map(i->{
                    double value;
                    if(i.getDish()!=null){
                        value = i.getDish().getPrice() * i.getQuantity();
                    }else{
                        value = i.getDrink().getPrice() * i.getQuantity();
                    }
                    return value;
                }).mapToDouble(Double::doubleValue).sum())
                .orderStatus(order.getStatus().getValue())
                .userId(order.getUser().getId())
                .tableId(order.getTable().getTableId())
                .build();
    }

//    public static OrderDishItemDTO toDishItemDto(OrderItem orderItem){
//        return OrderDishItemDTO.builder()
//                .id(orderItem.getId())
//                .dishId(orderItem.getDish().getId()!=null?orderItem.getDish().getId():null)
//                .dishName(orderItem.getDish().getName())
//                .quantity(orderItem.getQuantity())
//                .price(orderItem.getDish().getPrice())
//                .build();
//    }
//
//    public static OrderDrinkItemDTO toDrinkItemDto(OrderItem orderItem){
//        return OrderDrinkItemDTO.builder()
//                .id(orderItem.getId())
//                .drinkId(orderItem.getDrink().getId()!=null?orderItem.getDrink().getId():null)
//                .drinkName(orderItem.getDrink().getName())
//                .quantity(orderItem.getQuantity())
//                .price(orderItem.getDish().getPrice())
//                .build();
//    }

    public static OrderItemDTO toDto(OrderItem orderItem){
        if(orderItem.getDish()!=null){
            return OrderItemDTO.builder()
                    .id(orderItem.getId())
                    .productOrdered(orderItem.getDish().getName())
                    .price(orderItem.getPrice())
                    .quantity(orderItem.getQuantity())
                    .build();
        }
        if(orderItem.getDrink()!=null){
            return OrderItemDTO.builder()
                    .id(orderItem.getId())
                    .productOrdered(orderItem.getDrink().getName())
                    .price(orderItem.getPrice())
                    .quantity(orderItem.getQuantity())
                    .build();
        }
        return null;
    }


}
