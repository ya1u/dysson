package com.cos.dysson.service;

import com.cos.dysson.model.*;
import com.cos.dysson.repository.OrderItemRepository;
import com.cos.dysson.repository.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

    // 회원가입 하면 회원 당 주문 하나 생성
    public void createOrder(Users user) {
        Order order = Order.createOrder(user);
        orderRepository.save(order);
    }

    // id에 해당하는 주문상품 찾기
    public List<OrderItem> findUserOrderItems(int userId) {
        return orderItemRepository.findOrderItemsByUserId(userId);
    }

    // OrderItem 하나 찾기
    public OrderItem findOrderItem(int orderItemId) {
        return orderItemRepository.findOrderItemById(orderItemId);
    }

    @Transactional
    public OrderItem addCartOrder(int itemId, int userId, CartItem cartItem) {
        Users user = userService.findUser(userId);

        OrderItem orderItem = OrderItem.createOrderItem(itemId, user, cartItem);

        orderItemRepository.save(orderItem);

        return orderItem;
    }

    @Transactional
    public void addOrder(Users user, List<OrderItem> orderItemList) {
        Order userOrder = Order.createOrder(user, orderItemList);
        orderRepository.save(userOrder);
    }

    // 단일 상품 주문
    @Transactional
    public void addOneItemOrder(int userId, Product product, int count) {
        Users user = userService.findUser(userId);

        Order userOrder = Order.createOrder(user);

        OrderItem orderItem = OrderItem.createOrderItem(product.getId(), user, product, count, userOrder);

        orderItemRepository.save(orderItem);
        orderRepository.save(userOrder);
    }

    // 주문 취소 기능
    @Transactional
    public void orderCancel(Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);

        order.setIsCancel(order.getIsCancel() + 1);
        order.setIsDelivery(0);

        orderRepository.save(order);
    }

    public List<Order> findByUserId(Integer id) {
        return orderRepository.findOrdersByUserId(id);
    }

    @Transactional
    public void isDeliverySet(int orderId) {
        Order order = orderRepository.findOrderById(orderId);

        if (order.getIsDelivery() < 2) {
            order.setIsDelivery(order.getIsDelivery() + 1);
        } else {
            order.setIsDelivery(0);
        }
    }
}
