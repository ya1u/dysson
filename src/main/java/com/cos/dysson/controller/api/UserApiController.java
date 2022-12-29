package com.cos.dysson.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cos.dysson.dto.ResponseDto;
import com.cos.dysson.dto.SendTmpPwdDto;
import com.cos.dysson.dto.UserRequestDto;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.UserRepository;
import com.cos.dysson.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@PostMapping("/auth/joinProc")
//	public ResponseDto<Integer> save(@RequestBody Users user) {
//		System.out.println("UserApiController 호출됨");
//		userService.회원가입(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<?> save(@Valid @RequestBody UserRequestDto userDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(bindingResult);
			
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
		}
		
		userService.회원가입(userDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	//회원정보수정
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody Users user) {
		//json 형태를 받기위한 RequestBody
		userService.회원수정(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}


	@DeleteMapping("/user/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		System.out.println(id);
		userService.userDel(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/auth/find")
	public ResponseDto<?> find(@RequestBody SendTmpPwdDto dto) {
				
		if(!userRepository.existsByUsername(dto.getUsername()) || !Pattern.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", dto.getEmail())) {
			Map<String, String> validResult = new HashMap<>();
			
			if(!userRepository.existsByUsername(dto.getUsername())) {
				validResult.put("valid_username", "존재하지 않는 사용자 이름입니다.");
			}
			if(!Pattern.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", dto.getEmail())) {
				validResult.put("valid_email", "올바르지 않은 이메일 형식입니다.");
			}
			
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validResult); 
		}
		
		userService.sendTmpPwd(dto);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
}
