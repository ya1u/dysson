package com.cos.dysson.controller;


import com.cos.dysson.model.Order;
import com.cos.dysson.repository.UserRepository;
import com.cos.dysson.service.OrderService;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.model.CartItem;
import com.cos.dysson.model.KakaoProfile;
import com.cos.dysson.model.OAuthToken;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.UserRepository;
import com.cos.dysson.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private OrderService orderService;
	@Autowired
	private BCryptPasswordEncoder encodeer;

	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	@GetMapping("/mypage/{id}")
	public String mypage(@PathVariable("id") Integer id, Model model) {
		List<Order> order = orderService.findByUserId(id);

		model.addAttribute("orders", order);
		return "user/mypage";
	}
	@GetMapping("/auth/findPw")
	public String findPwd() {
		return "user/findPw";
	}
	@GetMapping("/mypage/userInfo")
	public String userInfo() {
		return "user/userInfo";
	}

	@GetMapping("/mypage/userWithdrawal")
	public String userWithdrawal() {
		return "user/userWithdrawal";
	}


	//????????? ?????????
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {//Data??? ??????????????? ???????????? ??????
		
		//POST???????????? key=value ???????????? ??????(??????????????????)
		
		
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader ???????????? ??????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody ???????????? ??????
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "2fadb3c76663155318a907aa33153a61");
		params.add("redirect_uri", "http://localhost:8050/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader??? HttpBody??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = //kakaoTokenRequest??? headers, params?????? ????????????
				new HttpEntity<>(params, headers);
		//Http???????????? - POST???????????? - ????????? response????????? ????????????
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class //String ???????????? ?????????
								
			);
	
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("????????? ????????? ?????? : " + oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader ???????????? ??????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader??? HttpBody??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = //kakaoTokenRequest??? headers, params?????? ????????????
				new HttpEntity<>(headers2);
		//Http???????????? - POST???????????? - ????????? response????????? ????????????
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class //String ???????????? ?????????
								
			);
			
			ObjectMapper objectMapper2 = new ObjectMapper();
			KakaoProfile kakaoProfile = null;
			try {
				kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			//Users ???????????? : username,password,email
			System.out.println("????????? ?????????(??????) : " + kakaoProfile.getId());
			System.out.println("????????? ????????? : " + kakaoProfile.getKakao_account().getEmail());
		
			System.out.println("??????????????? ????????????: " + kakaoProfile.getKakao_account().getEmail());
			System.out.println("??????????????? ?????????: " + kakaoProfile.getKakao_account().getEmail());
			
			System.out.println("??????????????? ????????????: " + cosKey);
			
			//?????? ????????? ???????????? ??????
			Users kakaoUser = Users.builder()
					.username(kakaoProfile.getKakao_account().getEmail())
					.password(cosKey)
					.email(kakaoProfile.getKakao_account().getEmail())
					.oauth("kakao")
					.build();
					
			//????????? ?????? ???????????? ???????????? ??????
			Users originUser = userService.????????????(kakaoUser.getUsername());
			
			if(originUser.getUsername()==null) {

				System.out.println("??????????????? ???????????? ?????? ??????????????????");
				userService.????????????(kakaoUser);
			}
			
			System.out.println("?????? ????????? ??????");
			//????????? ??????
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);		
			
			return "redirect:/";
	}

	@GetMapping("/auth/idCheck/username/{username}")
	public String pop(@PathVariable("username") String username, Model model) {
		System.out.println("????????? : " + username);

		model.addAttribute("username", username);
		return "/user/checkid";
	}
	@GetMapping("/auth/checkId.do")
	public String doGet(Model model, HttpServletResponse response, @RequestParam("username") String username) throws ServletException, IOException {
		boolean chk = userService.chkUsername(username);
		int result = 0;
		System.out.println("????????????????????????" + chk);
		if(chk == true) {
			result = 1;

		} else {
			result = -1;
		}
		model.addAttribute("result",result);
		model.addAttribute("username",username);
		return "/user/checkid";
	}
}
