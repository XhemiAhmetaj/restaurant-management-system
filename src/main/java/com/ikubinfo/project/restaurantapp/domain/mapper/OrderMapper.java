package com.ikubinfo.project.restaurantapp.domain.mapper;

import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderItemDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.OrderItem;
import com.ikubinfo.project.restaurantapp.domain.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .orderItems(order.getOrderItems()!=null?order.getOrderItems().stream().map(OrderMapper::toDto).collect(Collectors.toList()):null)
                .totalAmount(order.getOrderItems().stream().map(i->(i.getDish().getPrice() * i.getQuantity())).mapToDouble(Double::doubleValue).sum())
                .orderStatus(order.getStatus().getValue())
                .userDTO(UserMapper.toDto(order.getUser()))
                .build();
    }

    public static OrderItemDTO toDto(OrderItem orderItem){
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .dish(orderItem.getDish()!=null?DishMapper.toDto(orderItem.getDish()):null)
                .quantity(orderItem.getQuantity())
                .price(orderItem.getDish().getPrice())
                .build();
    }

//    public static Order buildOrder(User u, Order order){

//        List<OrderItem> items = orderI.findAllByOrder_Id(orderId).stream().map(item->{
//            OrderItem i = new OrderItem();
//            i.setQuantity(item.getQuantity());
//            i.setDish(item.getDish());
//            i.setPrice(item.getPrice());
//            i.setOrder(order);
//            return i;
//        }).collect(Collectors.toList());
//        order.setOrderItems(items);
//    }


}
