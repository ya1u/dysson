package com.cos.dysson.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.dysson.dto.ResponseDto;
import com.cos.dysson.dto.ReviewSaveRequestDto;
import com.cos.dysson.model.Product;
import com.cos.dysson.service.ProductService;

@RestController
public class ProductApiController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/api/product")
	public ResponseDto<Integer> save(@RequestBody Product product) {
		productService.제품등록(product);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("api/product/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Product product) {
		productService.제품수정하기(id, product);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/product/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		productService.제품삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//리뷰작성
	@PostMapping("/api/product/{productId}/review")
	public ResponseDto<Integer> reviewSave(@RequestBody ReviewSaveRequestDto reviewSaveRequestDto){
	
		
		productService.reviewSave(reviewSaveRequestDto);
		
		System.out.println(reviewSaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
