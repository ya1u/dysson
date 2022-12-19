package com.cos.dysson.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "REPLY_SEQ_GENERATOR1"
	    , sequenceName = "REPLY_SEQ1"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="REPLY_SEQ_GENERATOR1")
	private int id;
	@Column(nullable=false, length=200)
	private String content;
	@ManyToOne
	@JoinColumn(name="boardsId")
	private Boards boards;
	
	@ManyToOne
	@JoinColumn(name="usersId")
	private Users users;
	
	@CreationTimestamp
	private Timestamp createDate;

}
