package com.cos.dysson.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private Users seller;
		
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