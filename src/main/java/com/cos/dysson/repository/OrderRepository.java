package com.cos.dysson.repository;

import com.cos.dysson.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUserId(Integer id);

    Order findOrderById(Integer orderId);
}
