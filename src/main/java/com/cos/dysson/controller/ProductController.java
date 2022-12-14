package com.cos.dysson.controller;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cos.dysson.model.Product;
import com.cos.dysson.repository.ProductRepository;
import com.cos.dysson.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
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
	
	@RequestMapping("product/saveProduct")
	public String saveProduct(Product product, MultipartFile imgProduct) throws Exception {
		String sourceFileName = imgProduct.getOriginalFilename();
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:\\workspace\\image\\";
		
		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());
		
		destinationFile.getParentFile().mkdirs();
		
		imgProduct.transferTo(destinationFile);
		
		product.setImgName(destinationFileName);
		product.setImgOriName(sourceFileName);
		product.setImgUrl(fileUrl);
		
		productRepository.save(product);
		
		return "redirect:/product/store";
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
