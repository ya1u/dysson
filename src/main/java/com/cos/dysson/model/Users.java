package com.cos.dysson.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Table(name="users")
@SequenceGenerator (
		name = "USER_SEQ_GENERATOR"
		, sequenceName = "USER_SEQ"
		, initialValue = 1
		, allocationSize = 1
		
	)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Users {
	@Id //primary key
    @GeneratedValue(strategy=GenerationType.AUTO, generator="USER_SEQ_GENERATOR")
	//프로젝트에 연결된 DB의 넘버링 전략을 사용
	private int id;//시퀀스
	
	@Column(nullable=false, length=100,unique = true)
	private String username;//아이디
	
	@Column(length=50)
	private String name;//이름

	@Column(nullable=false, length=100)//해쉬로 변경하여 암호화 length 크게
	private String password; 
	
	@Column(nullable=false, length=50)
	private String email;
	
	@Column(length=50)
	private String phone;
	
	@Column(length=200)
	private String address;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType roles;

	@OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
	private Cart cart;

	// 판매자가 가지고 있는 상품들
	@OneToMany(mappedBy = "seller")
	private List<Product> products = new ArrayList<>();

	// 구매자의 주문
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Order> userOrder = new ArrayList<>();

	// 구매자의 주문 상품들
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<OrderItem> userOrderItem = new ArrayList<>();

	private String oauth; //Kakao 회원판별
	 
	@CreationTimestamp //시간이 자동으로 입력
	private Timestamp createDate;

}