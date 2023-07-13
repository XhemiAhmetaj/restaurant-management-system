package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderItemDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .orderItems(order.getOrderItems()!=null?order.getOrderItems().stream().map(OrderMapper::toDto).collect(Collectors.toList()):null)
                .totalAmount(order.getOrderItems().stream().map(i->(i.getDish().getPrice() * i.getQuantity())).mapToDouble(Double::doubleValue).sum())
                .orderStatus(order.getStatus().getValue())
                .userId(order.getUser().getId())
                .tableId(order.getTable().getTableId())
//                .receiptId(order.getReceipt().getId()!=null?order.getReceipt().getId():null)
                .build();
    }

    public static OrderItemDTO toDto(OrderItem orderItem){
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .dishId(orderItem.getDish().getId())
                .dishName(orderItem.getDish().getName())
                .dishDescription(orderItem.getDish().getDescription())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getDish().getPrice())
                .build();
    }

}
