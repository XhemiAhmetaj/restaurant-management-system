package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.AddItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.CheckOutDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderItemDTO;
import com.ikubinfo.project.restaurantapp.service.OrderService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<OrderDTO> addItem(@RequestBody AddItemDTO itemDTO){
        return ResponseEntity.ok(orderService.addItemToOrder(itemDTO));
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
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long orderId, @RequestBody CheckOutDTO paymentMethod){
        return ResponseEntity.ok(orderService.placeOrder(orderId,paymentMethod));
    }

}
