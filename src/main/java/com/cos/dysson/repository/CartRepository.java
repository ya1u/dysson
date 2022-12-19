package com.cos.dysson.repository;

import com.cos.dysson.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUsersId(int id);

	List<CartItem> findAll(Cart userCart);
}
