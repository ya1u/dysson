package com.cos.dysson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Cart;
import com.cos.dysson.model.CartItem;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUsersId(int id);
	
//	List<CartItem> findAll(Cart userCart);

}
