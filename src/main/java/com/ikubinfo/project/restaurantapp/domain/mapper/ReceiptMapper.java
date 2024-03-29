package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.ReceiptDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.Receipt;
import com.ikubinfo.project.restaurantapp.domain.entity.Restaurant;

import java.util.stream.Collectors;

public class ReceiptMapper {

    public static ReceiptDTO toDto(Receipt receipt){
        return ReceiptDTO.builder()
//                .restaurantAddress(receipt.getRestaurant().getAddress())
                .restaurantName("Restaurant")
//                .restaurantNipt(receipt.getRestaurant().getNipt())
//                .dishItems(receipt.getOrder().getOrderItems().stream().map(OrderMapper::toDishItemDto).collect(Collectors.toList()))
//                .drinkItems(receipt.getOrder().getOrderItems().stream().map(OrderMapper::toDrinkItemDto).collect(Collectors.toList()))
                .orderItems(receipt.getOrder().getOrderItems().stream().map(OrderMapper::toDto).collect(Collectors.toList()))
                .total(receipt.getOrder().getTotalAmount())
                .points(receipt.getPoints())
                .build();
    }

    public static Receipt toCreate(Order order){
        Receipt receipt = new Receipt();
//        receipt.setRestaurant(restaurant);
        receipt.setOrder(order);
        receipt.setTotal(order.getTotalAmount());
        receipt.setPoints(order.getPoints());
        return receipt;
    }

}
