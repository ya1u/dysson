package com.cos.dysson.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="users")
@SequenceGenerator (
		name = "USER_SEQ_GENERATOR"
		, sequenceName = "USER_SEQ"
		, initialValue = 1
		, allocationSize = 1
		
	)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users {
	@Id //primary key
    @GeneratedValue(strategy=GenerationType.AUTO, generator="USER_SEQ_GENERATOR")
	//프로젝트에 연결된 DB의 넘버링 전략을 사용
	private int id;//시퀀스
	
	@Column(nullable=false, length=100,unique = true)
	private String username;//아이디

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

	@OneToOne(mappedBy = "users")
	private Cart cart;
	
	private String oauth; //Kakao 회원판별
	 
	@CreationTimestamp //시간이 자동으로 입력
	private Timestamp createDate;

}