package com.cos.dysson.controller.api;

import com.cos.dysson.dto.ResponseDto;
import com.cos.dysson.dto.SendTmpPwdDto;
import com.cos.dysson.dto.UserRequestDto;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.UserRepository;
import com.cos.dysson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encodeer;
	
//	@PostMapping("/auth/joinProc")
//	public ResponseDto<Integer> save(@RequestBody Users user) {
//		System.out.println("UserApiController 호출됨");
//		userService.회원가입(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}


	
	@PostMapping("/auth/joinProc")
	public ResponseDto<?> save(@Valid @RequestBody UserRequestDto userDto, BindingResult bindingResult) {
		System.out.println("테스트테스트테스트테스트테스트테스트"+userDto);
		if(bindingResult.hasErrors()) {
			Map<String, String> validatorResult = userService.validateHandling(bindingResult);

			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
		}
		System.out.println("테스트테스트테스트테스트테스트테스트"+userDto);
		userService.회원가입(userDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PostMapping("/user/{id}")
	public ResponseDto<Integer> deleteById2(@PathVariable int id){

		userService.userDel(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

	}

	@PostMapping("/userdel/{id}")
	public void del(@RequestParam("inputPwd") String inputPwd, @PathVariable("id") int id, HttpServletResponse response) throws IOException {
		Users users = userService.findUser(id);
		String enPwd = users.getPassword();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(encodeer.matches(inputPwd,enPwd)) {

			userService.userDel(id);
			out.println("<script language='javascript'>");
			out.println("alert('회원탈퇴가 완료되었습니다');");
			out.println("location.href = '/logout'");
			out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
			out.println("alert('비밀번호를 다시 확인해주세요');");
			out.println("location.href = '/mypage/userWithdrawal'");
			out.println("</script>");
		}

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
