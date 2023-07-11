package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.AddItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.CheckOutDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderItemDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.OrderItem;

import java.util.List;

public interface OrderService {

    OrderDTO addItemToOrder(AddItemDTO itemDTO);

//    OrderDTO removeItem(Long itemId);
    OrderDTO getOrder(Long id);
    List<OrderDTO> getAllUserOrders(Long user_id);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByStatus(String orderStatus);

    OrderDTO placeOrder(Long orderId, CheckOutDTO checkOutDTO);

    Void changeStatus(Long orderId, String status);
}
