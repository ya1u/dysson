package com.cos.dysson.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
			name = "BALMUDA_SEQ_GENERATOR3"
			, sequenceName = "BALMUDA_SEQ3"  
			, initialValue = 1
			, allocationSize = 1
		)
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BALMUDA_SEQ_GENERATOR3")
	private int id;
	
	@Column(nullable = false, length = 10)
	private int rate;
	
	@Lob
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users users;
	
	@CreationTimestamp
	private Timestamp createDate;
}