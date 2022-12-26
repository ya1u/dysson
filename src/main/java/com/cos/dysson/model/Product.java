package com.cos.dysson.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@SequenceGenerator(
		name = "BALMUDA_SEQ_GENERATOR2"
		, sequenceName = "BALMUDA_SEQ2"
		, initialValue = 1
		, allocationSize = 1
		)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BALMUDA_SEQ_GENERATOR2")
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name; // 상품이름
	
	@Lob
	private String content; // 상품 설명
	
	@Column(nullable = false, length = 20)
	private String made; //제조사
	
	@Column(nullable = false, length = 30)
	private int price; // 가격
	
	@Column(nullable = false, length = 20)
	private String category;
	
	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItem = new ArrayList<>();
	
	@Formula("(SELECT count(1) FROM review r WHERE r.productid = id)")
	private int reviewCnt;
	

	@Column(length = 10) 
	private double ratingAvg;
	
	@Column
	private double ratingCount = 0;
	
	@Column
	private double ratingSum = 0;


	
	private String imgName;
	
	private String imgOriName;
	
	private String imgUrl;
	
	/*
	 * @Column(nullable = false, length = 100) private String imgUrl;
	 */
	
	@OneToMany (mappedBy = "product", fetch = FetchType.EAGER)
	private List<Review> review;
	
//	@Enumerated(EnumType.STRING)
//	private Category category;
}