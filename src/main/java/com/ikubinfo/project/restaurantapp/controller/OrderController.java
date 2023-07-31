package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @RolesAllowed({"ADMIN","RECEPTIONIST"})
    @GetMapping
    public ResponseEntity<List<OrderDTO>> listOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @RolesAllowed({"ADMIN","RECEPTIONIST","COOKER"})
    @GetMapping("{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @RolesAllowed("COOKER")
    @PostMapping("/{tableId}")
    public ResponseEntity<OrderDTO> addDishItem(@PathVariable Long tableId, @RequestBody AddDishItemDTO itemDTO){
        return ResponseEntity.ok(orderService.addItemToOrder(tableId, itemDTO, null));
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/{tableId}/drink")
    public ResponseEntity<OrderDTO> addDrinkItem(@PathVariable Long tableId, @RequestBody AddDrinkItemDTO itemDTO){
        return ResponseEntity.ok(orderService.addItemToOrder(tableId,null, itemDTO));
    }

    @RolesAllowed({"ADMIN","RECEPTIONIST"})
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status){
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }
    @RolesAllowed({"ADMIN","RECEPTIONIST"})
    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDTO>> getUsersOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getAllUserOrders(userId));
    }

    @RolesAllowed("CUSTOMER")
    @PostMapping("/{orderId}/place")
    public ResponseEntity<ReceiptDTO> placeOrder(@PathVariable Long orderId, @RequestBody CheckOutDTO paymentMethod){
        return ResponseEntity.ok(orderService.placeOrder(orderId,paymentMethod));
    }

    @RolesAllowed({"RECEPTIONIST"})
    @GetMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Long orderId, @PathVariable String orderStatus){
        return ResponseEntity.ok(orderService.changeStatus(orderId,orderStatus));
    }
    @RolesAllowed("CUSTOMER")
    @DeleteMapping("/{orderId}/item/{orderItem}")
    public ResponseEntity<String> removeItem(@PathVariable Long orderId, @PathVariable Long orderItem){
        return ResponseEntity.ok(orderService.removeItem(orderId,orderItem));
    }

    @RolesAllowed({"ADMIN","COOKER"})
    @PostMapping("/list")
    public ResponseEntity<Page<OrderDTO>> filterProducts (@RequestBody List<SearchCriteria> criteria, PageParameterDTO pageParameterDTO) {
        return ResponseEntity.ok(orderService.filterOrders(criteria,pageParameterDTO));
    }

}
