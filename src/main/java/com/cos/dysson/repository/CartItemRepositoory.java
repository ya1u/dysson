package com.cos.dysson.repository;

import com.cos.dysson.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.CartItem;

import java.util.List;

public interface CartItemRepositoory extends JpaRepository<CartItem, Integer> {

	CartItem findByCartIdAndProductId(int cartId, int productId);

    List<CartItem> findByCart(Cart userCart);

}
