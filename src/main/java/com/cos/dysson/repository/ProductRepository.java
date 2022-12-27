package com.cos.dysson.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Boards;
import com.cos.dysson.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findProductById(int productId);
	
	List<Product> findByCategory(Pageable pageable, String category);
	
	Page<Product> findByNameContaining(String searchKeyword, Pageable pageable);
	
}
