package com.cos.dysson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.dysson.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// about
	@GetMapping({"/auth/about"})
	public String about() {
		return "about/about";
	}
	
	// 제품 등록
	@GetMapping({"/product/addForm"})
	public String addForm() {
		return "product/addForm";
	}
	
	// 제품 전체리스트
	@GetMapping("/product/store")
	public String list(Model model, @PageableDefault(size = 6, sort = "id",
	direction = Sort.Direction.ASC) Pageable pageable) {
		model.addAttribute("product", productService.제품리스트(pageable));
		return "product/store";
	}
	
	// 제품 상세보기
	@GetMapping("/product/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.제품상세보기(id));
		return "product/detail";
	}
	
	// 장바구니
	@GetMapping("/auth/cart")
	public String store() {
		return "product/cart";
	}
}
