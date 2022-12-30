package com.cos.dysson.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "orderItem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "ORDERITEM_SEQ_GENERATOR1"
        , sequenceName = "ORDERITEM_SEQ1"
        , initialValue = 1
        , allocationSize = 1
)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDERITEM_SEQ_GENERATOR1")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Users user;

    private int itemId;
    private String itemName;
    private int itemPrice;
    private int itemCount;
    private int itemTotalPrice;
    private String category;

//    private int isCancel;


    public static OrderItem createOrderItem(int itemId, Users user, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setUser(user);
        orderItem.setItemName(cartItem.getProduct().getName());
        orderItem.setItemPrice(cartItem.getProduct().getPrice());
        orderItem.setItemCount(cartItem.getCount());
        orderItem.setItemTotalPrice(cartItem.getProduct().getPrice()*cartItem.getCount());
        orderItem.setCategory(cartItem.getProduct().getCategory());
        return orderItem;
    }

    public static OrderItem createOrderItem(int itemId, Users user, Product product, int count, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setUser(user);
        orderItem.setOrder(order);
        orderItem.setItemName(product.getName());
        orderItem.setItemPrice(product.getPrice());
        orderItem.setItemCount(count);
        orderItem.setItemTotalPrice(product.getPrice()*count);
        orderItem.setCategory(product.getCategory());
        return orderItem;
    }

}
