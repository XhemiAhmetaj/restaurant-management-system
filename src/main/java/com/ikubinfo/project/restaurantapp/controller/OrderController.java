package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderDTO>> listOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/{tableId}")
    public ResponseEntity<OrderDTO> addItem(@PathVariable Long tableId, @RequestBody AddItemDTO itemDTO){
        return ResponseEntity.ok(orderService.addItemToOrder(tableId, itemDTO));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status){
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDTO>> getUsersOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getAllUserOrders(userId));
    }

    @PostMapping("/{orderId}/place")
    public ResponseEntity<ReceiptDTO> placeOrder(@PathVariable Long orderId, @RequestBody CheckOutDTO paymentMethod){
        return ResponseEntity.ok(orderService.placeOrder(orderId,paymentMethod));
    }

    @GetMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<Void> changeOrderStatus(@PathVariable Long orderId, @PathVariable String orderStatus){
        return ResponseEntity.ok(orderService.changeStatus(orderId,orderStatus));
    }

    @DeleteMapping("/{orderId}/item/{orderItem}")
    public ResponseEntity<Void> removeItem(@PathVariable Long orderId, @PathVariable Long orderItem){
        return ResponseEntity.ok(orderService.removeItem(orderId,orderItem));
    }

    @PostMapping("/list")
    public ResponseEntity<Page<OrderDTO>> filterProducts (@RequestBody List<SearchCriteria> criteria, PageParameterDTO pageParameterDTO) {
        return ResponseEntity.ok(orderService.filterOrders(criteria,pageParameterDTO));
    }

}
