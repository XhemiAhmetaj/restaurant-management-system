package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.dto.*;
import com.ikubinfo.project.restaurantapp.domain.entity.*;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.OrderStatus;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import com.ikubinfo.project.restaurantapp.domain.mapper.OrderMapper;
import com.ikubinfo.project.restaurantapp.domain.mapper.ProductMapper;
import com.ikubinfo.project.restaurantapp.domain.mapper.ReceiptMapper;
import com.ikubinfo.project.restaurantapp.domain.mapper.UserMapper;
import com.ikubinfo.project.restaurantapp.repository.*;
import com.ikubinfo.project.restaurantapp.repository.specification.OrderSpecification;
import com.ikubinfo.project.restaurantapp.repository.specification.ProductSpecification;
import com.ikubinfo.project.restaurantapp.repository.specification.SearchCriteria;
import com.ikubinfo.project.restaurantapp.repository.specification.UserSpecification;
import com.ikubinfo.project.restaurantapp.service.OrderService;
import com.ikubinfo.project.restaurantapp.service.PaymentService;
import com.ikubinfo.project.restaurantapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.ORDER_NOT_FOUND;
import static com.ikubinfo.project.restaurantapp.domain.exception.ExceptionConstants.ORDER_PLACED_BEFORE;
import static com.ikubinfo.project.restaurantapp.domain.mapper.OrderMapper.toDto;
import static java.lang.String.format;

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
    private final RestaurantTableRepository tableRepository;
    private final PaymentService paymentService;
    private final ReceiptRepository receiptRepository;

//    @Override
//    public OrderDTO addItemToOrder(Long tableId, AddItemDTO itemDTO) {
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof Jwt) {
//            Jwt jwt = (Jwt) principal;
//            var sub = (String) jwt.getClaims().get("sub");
//            Optional<User> u  = userRepository.findByEmail(sub);
//            List<Order> orderFromUser = orderRepository.findOrdersByUser(u.get())
//                    .stream()
//                    .filter(order -> order.getStatus().equals(OrderStatus.CREATED))
//                    .collect(Collectors.toList());
//
//            if (orderFromUser.isEmpty()) {
//                return toDto(addFirstItem(tableId, u, itemDTO));
//
//            } else {
//                OrderItem addItem = buildItem(orderFromUser.get(0), itemDTO);
//                orderFromUser.get(0).getOrderItems().add(addItem);
//                return toDto(orderRepository.save(orderFromUser.get(0)));
//            }
//        } else {
////            throw new BadRequestException("User not authenticated");
//            List<Order> orderFromTable = orderRepository.findOrdersByTable_TableId(tableId)
//                    .stream()
//                    .filter(order -> order.getStatus().equals(OrderStatus.CREATED))
//                    .collect(Collectors.toList());
//            if(orderFromTable.isEmpty()){
//                return toDto(addFirstItem(tableId, Optional.empty(), itemDTO));
//            } else {
//                OrderItem addItem = buildItem(orderFromTable.get(0), itemDTO);
//                orderFromTable.get(0).getOrderItems().add(addItem);
//                return toDto(orderRepository.save(orderFromTable.get(0)));
//            }
//        }
//    }

    @Override
    public OrderDTO addItemToOrder(Long tableId, AddItemDTO itemDTO) {

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var sub = (String) jwt.getClaims().get("sub");
        Optional<User> u  = userRepository.findByEmail(sub);
        if(!u.get().getEmail().equals("guest@gmail.com")) {
            List<Order> orderFromUser = orderRepository.findOrdersByUser(u.get())
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.CREATED))
                    .collect(Collectors.toList());

            return getOrderDTO(tableId, itemDTO, u, orderFromUser);
        }else {
            log.info("------User NOT authenticated---- "+u.get().getEmail());
            List<Order> orderFromTable = orderRepository.findOrdersByTable_TableId(tableId)
                    .stream()
                    .filter(order -> order.getStatus().equals(OrderStatus.CREATED))
                    .collect(Collectors.toList());
            log.info("--------- orderFromTable size------  " + orderFromTable.size());
            return getOrderDTO(tableId, itemDTO, u, orderFromTable);
        }
    }

    private OrderDTO getOrderDTO(Long tableId, AddItemDTO itemDTO, Optional<User> u, List<Order> orderFromUser){
        if (orderFromUser.isEmpty()) {
            return toDto(addFirstItem(tableId, u, itemDTO));

        } else {
            OrderItem addItem = buildItem(orderFromUser.get(0), itemDTO);
            orderFromUser.get(0).getOrderItems().add(addItem);
            return toDto(orderRepository.save(orderFromUser.get(0)));
        }
    }

    private Order addFirstItem(Long tableId, Optional<User> u, AddItemDTO item){
        OrderItem addItem;
        Order order = new Order();
        order.setTable(tableRepository.findById(tableId).orElseThrow(()-> new ResourceNotFoundException("Table id not found!")));
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
        item.setDish(dishRepository.findById(itemDTO.getDishId()).orElseThrow(() -> new ResourceNotFoundException(format("Dish with id %s not found!", itemDTO.getDishId()))));
        item.setPrice(item.getDish().getPrice());
        orderItemRepository.save(item);
        return item;
    }

    @Override
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format("Dish with id %s not found!", id)));
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
    public ReceiptDTO placeOrder(Long orderId, CheckOutDTO checkOutDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(format(ORDER_NOT_FOUND, orderId)));
        if(order.getStatus().equals(OrderStatus.CREATED))
        {
            order.setTotalAmount(order.getOrderItems().stream().map(o -> o.getQuantity() * o.getPrice()).mapToDouble(Double::doubleValue).sum());
            Payment payment = paymentService.addPayment(checkOutDTO.getPaymentMethod(), order.getTotalAmount());
            order.setPayment(payment);
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
            if(order.getUser().getEmail().equals("guest@gmail.com")){
                order.setPoints(0);
            }else{
                setOrderPoints(order);
            }
            setUserPoints(order);
            updateProducts(order.getId());

            Receipt receipt= ReceiptMapper.toCreate(order);
            receiptRepository.save(receipt);
//            order.setReceipt(receipt);

            orderRepository.save(order);
            return ReceiptMapper.toDto(receipt);
        }else{
            throw new ResourceNotFoundException(ORDER_PLACED_BEFORE);
        }

    }

    public void setOrderPoints(Order order){
        order.setPoints((int) (order.getTotalAmount()*0.005));
        orderRepository.save(order);
    }
    private void setUserPoints(Order order) {
        User user = order.getUser();
        user.setTotalPoints(user.getTotalPoints() + order.getPoints());
        userRepository.save(user);
    }

    @Override
    public Void changeStatus(Long orderId, String status) {
        orderRepository.findById(orderId)
                .map(o -> {
                    o.setStatus(OrderStatus.fromValue(status));
                    return orderRepository.save(o);
                }).orElseThrow(() -> new ResourceNotFoundException(format(ORDER_NOT_FOUND,orderId)));
        return null;
    }

    public void updateProducts(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.getOrderItems().stream()
                .flatMap(orderItem->
                        orderItem.getDish().getIngredients().stream()
                        .map(dishIngredient ->{
                            Product product = dishIngredient.getProduct();
                            log.info("-------------------------dishIngredient.getMeasure()------------ "+dishIngredient.getMeasure());
                            log.info("-----------------orderItem.getQuantity()-------------" + orderItem.getQuantity());
                            log.info("------------------before----------------------" +product.getProductWeight());
                            product.setProductWeight(product.getProductWeight() - (dishIngredient.getMeasure() * orderItem.getQuantity()));
                            log.info("------------------after----------------------" +product.getProductWeight());

                    productRepository.save(product);
                        return null;
                        })).collect(Collectors.toList());

    }

    @Override
    public Page<OrderDTO> filterOrders(List<SearchCriteria> searchCriteria, PageParameterDTO pageDTO){
        Sort sort = Sort.by(pageDTO.getSortDirection(), pageDTO.getSort());
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),pageDTO.getPageSize(),sort);
        if(searchCriteria!=null && searchCriteria.size()>0){
            var orderSpec = OrderSpecification.toSpecification(searchCriteria);

            return orderRepository.findAll(orderSpec,pageable).map(OrderMapper::toDto);
        }else {
            return orderRepository.findAll(pageable).map(OrderMapper::toDto);
        }
    }


}
