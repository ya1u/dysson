package com.cos.dysson.service;

import com.cos.dysson.model.CartItem;
import com.cos.dysson.model.OrderItem;
import com.cos.dysson.model.SaleItem;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem addCartOrder(int itemId, int userId, CartItem cartItem, SaleItem saleItem) {
        Users user = userService.findUser(userId);

        OrderItem orderItem = OrderItem.createOrderItem(itemId, user, cartItem, saleItem);

        orderItemRepository.save(orderItem);

        return orderItem;
    }

    public void addOrder(Users user, List<OrderItem> orderItemList) {
    }
}
