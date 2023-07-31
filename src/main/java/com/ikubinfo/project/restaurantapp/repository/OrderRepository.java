package com.ikubinfo.project.restaurantapp.repository;

import com.ikubinfo.project.restaurantapp.domain.entity.Order;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {

    Order findAllByStatus(OrderStatus status);
    Optional<Order> findByUser(User user);

    List<Order> findOrdersByUser(User user);

    List<Order> findOrdersByStatus(OrderStatus status);
    List<Order> findOrdersByTable_TableId(Long tableId);

}
