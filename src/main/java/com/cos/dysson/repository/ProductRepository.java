package com.cos.dysson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
