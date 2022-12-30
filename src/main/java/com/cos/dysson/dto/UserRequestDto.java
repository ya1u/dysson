package com.cos.dysson.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String username;

	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "최소 8자, 최소 하나의 문자 및 하나의 숫자를 사용하세요.")
	private String password;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	private String address;
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	private String phone;
	
	
	
	

}