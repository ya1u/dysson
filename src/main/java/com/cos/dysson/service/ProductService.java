package com.cos.dysson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.model.Product;
import com.cos.dysson.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public void 제품등록(Product product) {
		productRepository.save(product);
	}
	
	@Transactional(readOnly = true)
	public Page<Product> 제품리스트(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional
	public Product 제품상세보기(int id) {
		return productRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("제품 상세보기 실패");
		});
	}
	
	@Transactional
	public void 제품수정하기(int id, Product requestProduct) {
		Product product = productRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("제품 찾기 실패");
		});
		product.setName(requestProduct.getName());
		product.setContent(requestProduct.getContent());
		product.setMade(requestProduct.getMade());
		product.setPrice(requestProduct.getPrice());
		product.setCategory(requestProduct.getCategory());
	}
	
	@Transactional
	public void 제품삭제하기(int id) {
		productRepository.deleteById(id);
	}

	public Product productView(Integer id) {
		return productRepository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public List<Product> productKitchen(Pageable pageable) {
		String category = "KITCHEN";
		return productRepository.findByCategory(pageable, category);
	}
	@Transactional(readOnly = true)
	public List<Product> productAir(Pageable pageable) {
		String category = "AIR";
		return productRepository.findByCategory(pageable, category);
	}
	@Transactional(readOnly = true)
	public List<Product> productCleaner(Pageable pageable) {
		String category = "CLEANER";
		return productRepository.findByCategory(pageable, category);
	}

}
