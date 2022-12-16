package com.cos.dysson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUsersId(int id);

}
