package com.cos.dysson.service;

import java.util.List;

import com.cos.dysson.model.OrderItem;
import com.cos.dysson.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.dto.ReviewSaveRequestDto;
import com.cos.dysson.model.Product;
import com.cos.dysson.model.Review;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.ProductRepository;
import com.cos.dysson.repository.ReviewRepository;
import com.cos.dysson.repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
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
	public Product ratingAvg(int productId,int rate) {
		Product product = productRepository.findById(productId).orElseThrow(() -> {
			return new IllegalArgumentException("제품 찾기 실패");
		});
	
		double count = product.getRatingCount();

		count++;

		
		double sum = product.getRatingSum() + rate;
		
		double avg = sum / count;
		
		System.out.println("평균평균평균평균평균평균평균평균평균평균평균평균평균평균평균"+avg);
		product.setRatingCount(count);
		product.setRatingSum(sum);
		product.setRatingAvg(Math.round(avg));
		System.out.println("zkdnsxm third"+count);
		
		return product;
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

	
	@Transactional
	public void reviewSave(ReviewSaveRequestDto reviewSaveRequestDto) {
		System.out.println(reviewSaveRequestDto.getUsersId());
		Users user = userRepository.findById(reviewSaveRequestDto.getUsersId()).orElseThrow(()->{
			return new IllegalArgumentException("리뷰 쓰기 실패: 유저 id를 찾을수 없습니다");
			
		});//영속화 완료
		
		Product product = productRepository.findById(reviewSaveRequestDto.getProductId()).orElseThrow(()->{
			return new IllegalArgumentException("리뷰 쓰기 실패: 상품 id를 찾을수 없습니다");
			
		});//영속화 완료
		Review review = Review.builder()
				.users(user)
				.product(product)
				.rate(reviewSaveRequestDto.getRate())
				.content(reviewSaveRequestDto.getContent())
				.build();
		reviewRepository.save(review);
				
		
	}

	

}
