package com.cos.dysson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.CartItem;

public interface CartItemRepositoory extends JpaRepository<CartItem, Integer> {

	CartItem findByCartIdAndProductId(int cartId, int productId);

}
