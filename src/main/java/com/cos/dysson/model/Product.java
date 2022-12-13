package com.cos.dysson.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "BALMUDA_SEQ_GENERATOR2"
		, sequenceName = "BALMUDA_SEQ2"
		, initialValue = 1
		, allocationSize = 1
		)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BALMUDA_SEQ_GENERATOR2")
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name; // 상품이름
	
	@Column(nullable = false, length = 100)
	private String content; // 상품 설명
	
	@Column(nullable = false, length = 20)
	private String made; //제조사
	
	@Column(nullable = false, length = 30)
	private int price; // 가격
	
	@Column(nullable = false, length = 20)
	private String category;
	
	@Column(nullable = false, length = 100)
	private String imgName;
	
	@Column(nullable = false, length = 100)
	private String imgUrl;
	
	@OneToMany (mappedBy = "product", fetch = FetchType.EAGER)
	private List<Review> review;
	
//	@Enumerated(EnumType.STRING)
//	private Category category;
}