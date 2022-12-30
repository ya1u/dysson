package com.cos.dysson.repository;

import com.cos.dysson.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findOrderItemsByUserId(int userId);
    List<OrderItem> findAll();
    OrderItem findOrderItemById(int orderItemId);

    List<OrderItem> findByCategory(String category);
}
