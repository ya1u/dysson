package com.cos.dysson.repository;

import com.cos.dysson.model.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Cart;
import com.cos.dysson.model.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUsersId(int id);
	
//	List<CartItem> findAll(Cart userCart);

	List<CartItem> findAll(Cart userCart);
}
