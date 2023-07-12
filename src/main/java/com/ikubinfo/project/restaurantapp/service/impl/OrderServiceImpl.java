package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.AddItemDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.CheckOutDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.OrderDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.OrderStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.OrderMapper;
import com.ikubinfo.project.restaurantapp.repository.*;
import com.ikubinfo.project.restaurantapp.service.OrderService;
import com.ikubinfo.project.restaurantapp.service.PaymentService;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.mapper.OrderMapper.toDto;

@Service
@RequiredArgsConstructor @Slf4j
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DishRepository dishRepository;
    private final ProductRepository productRepository;
    private final DishIngredientRepository dishIngredientRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    @Override
    public OrderDTO addItemToOrder(AddItemDTO itemDTO) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            var sub = (String) jwt.getClaims().get("sub");
            Optional<User> u  = userRepository.findByEmail(sub);
            List<Order> orderFromUser = orderRepository.findOrdersByUser(u.get())
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.CREATED))
                    .collect(Collectors.toList());

            OrderItem addItem;
            if (orderFromUser.isEmpty()) {
//                Order order = new Order();
//                order.setStatus(OrderStatus.CREATED);
//                order.setUser(u.get());
//                order = orderRepository.save(order);
//                addItem = buildItem(order, itemDTO);
//                order.getOrderItems().add(addItem);
//                order = orderRepository.save(order);
                return toDto(addFirstItem(u, itemDTO));

            } else {
                addItem = buildItem(orderFromUser.get(0), itemDTO);
                orderFromUser.get(0).getOrderItems().add(addItem);
                return toDto(orderRepository.save(orderFromUser.get(0)));
            }
        } else {
            throw new IllegalStateException("user not authenticated");
        }
    }

    private Order addFirstItem(Optional<User> u, AddItemDTO item){
        OrderItem addItem;
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setUser(u.get());
        order = orderRepository.save(order);
        addItem = buildItem(order, item);
        order.getOrderItems().add(addItem);
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Void removeItem(Long orderId, Long itemId) {
        orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderItemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        orderItemRepository.deleteById(itemId);
        return null;
    }

    private OrderItem buildItem(Order order, AddItemDTO itemDTO) {
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setQuantity(itemDTO.getQuantity());
        item.setDish(dishRepository.findById(itemDTO.getDishId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Dish with id %s not found!", itemDTO.getDishId()))));
        item.setPrice(item.getDish().getPrice());
        orderItemRepository.save(item);
        return item;
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Dish with id %s not found!", id)));
        return toDto(order);
    }

    @Override
    public List<OrderDTO> getAllUserOrders(Long user_id) {
        return orderRepository.findOrdersByUser(userService.findById(user_id)).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String orderStatus) {
        return orderRepository.findOrdersByStatus(OrderStatus.fromValue(orderStatus)).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO placeOrder(Long orderId, CheckOutDTO checkOutDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %s not found!", orderId)));
        if(order.getStatus().equals(OrderStatus.CREATED))
        {
            order.setTotalAmount(order.getOrderItems().stream().map(o -> o.getQuantity() * o.getPrice()).mapToDouble(Double::doubleValue).sum());
            Payment payment = paymentService.addPayment(checkOutDTO.getPaymentMethod(), order.getTotalAmount());
            order.setPayment(payment);
            order.setStatus(OrderStatus.CONFIRMED);
            updateProducts(order.getId());
            return toDto(orderRepository.save(order));
        }else{
            return toDto(order);
        }

    }

    @Override
    public Void changeStatus(Long orderId, String status) {
        orderRepository.findById(orderId)
                .map(o -> {
                    o.setStatus(OrderStatus.fromValue(status));
                    return orderRepository.save(o);
                }).orElseThrow(() -> new ResourceNotFoundException("order not found"));
        return null;
    }

    public Void updateProducts(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.getOrderItems().stream()
                .map(orderItem->orderItem.getDish().getIngredients().stream()
                        .peek(dishIngredient ->{
                    dishIngredient.getProduct().setQuantity(dishIngredient.getProduct().getQuantity() - (dishIngredient.getMeasure() * orderItem.getQuantity()));
                    productRepository.save(dishIngredient.getProduct());
                    dishIngredientRepository.save(dishIngredient);

                        })).collect(Collectors.toList());

        return null;

    }

}
