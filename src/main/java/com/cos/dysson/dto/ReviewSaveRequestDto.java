package com.cos.dysson.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveRequestDto {
	private int usersId;
	private int productId;
	private int rate;
	private String content;
}
