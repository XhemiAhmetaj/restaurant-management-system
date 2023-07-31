package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

//    OrderDTO addItemToOrder(Long tableId, AddDishItemDTO itemDTO);

    OrderDTO addItemToOrder(Long tableId, AddDishItemDTO dishItemDTO, AddDrinkItemDTO drinkItemDTO);

    String removeItem(Long orderId, Long itemId);
    OrderDTO getOrder(Long id);
    List<OrderDTO> getAllUserOrders(Long user_id);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByStatus(String orderStatus);

    ReceiptDTO placeOrder(Long orderId, CheckOutDTO checkOutDTO);

    String changeStatus(Long orderId, String status);

    Page<OrderDTO> filterOrders(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO);
}
