package com.cos.dysson.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "BOARD_SEQ_GENERATOR1"
	    , sequenceName = "BOARD_SEQ1"
	    , initialValue = 1
	    , allocationSize = 2
	)
public class Boards {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="BOARD_SEQ_GENERATOR1")
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	@Lob //대용량 데이터
	private String content;
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	//Many=Board, User = One 한명이 여러개의 게시글을 쓸 수 있다.
	
	@JoinColumn(name="userid")
	private Users users;
	
	@OrderBy("id desc") //댓글 최신순 정렬
	@OneToMany (mappedBy = "boards", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"boards"})//무한참조 방지
	private List<Reply> replys;
	
	@Formula("(SELECT count(1) FROM reply r WHERE r.boardsid = id)")
	private int replyCnt;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(nullable = false, length = 20)
	private String category;
	
	@Column(length=1)
	private boolean secret;
	
	

}
