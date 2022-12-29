package com.cos.dysson.controller;

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

import javax.servlet.http.HttpServletRequest;

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
	private BCryptPasswordEncoder encodeer;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	@GetMapping("/auth/mypage")
	public String mypage() {
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
	public String userWithdrawal(Model model, @AuthenticationPrincipal PrincipalDetail principal) {
		String enPwd = principal.getUser().getPassword();

//		int id = principal.getUser().getId();
//		model.addAttribute("enPwd",enPwd);
//		System.out.println("해시코드 : " + enPwd);
//		System.out.println(pwd);

//		if(encodeer.matches(inputPwd,enPwd)) {
//			userService.userDel(id);
//
//		} else {
//			// 오류메세지 제공 (생략)
//		}
		return "user/userWithdrawal";
	}





	
	//카카오 로그인
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {//Data를 리턴해주는 컨트롤러 함수
		
		//POST방식으로 key=value 데이터를 요청(카카오쪽으로)
		
		
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "2fadb3c76663155318a907aa33153a61");
		params.add("redirect_uri", "http://localhost:8050/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = //kakaoTokenRequest이 headers, params값을 가지게됨
				new HttpEntity<>(params, headers);
		//Http요청하기 - POST방식으로 - 그리고 response변수의 응답받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class //String 타입으로 가져옴
								
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
		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = //kakaoTokenRequest이 headers, params값을 가지게됨
				new HttpEntity<>(headers2);
		//Http요청하기 - POST방식으로 - 그리고 response변수의 응답받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class //String 타입으로 가져옴
								
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
			
			//Users 오브젝트 : username,password,email
			System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
			System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		
			System.out.println("블로그서버 유저네임: " + kakaoProfile.getKakao_account().getEmail());
			System.out.println("블로그서버 이메일: " + kakaoProfile.getKakao_account().getEmail());
			
			System.out.println("블로그서버 패스워드: " + cosKey);
			
			//값을 받아서 회원가입 시킴
			Users kakaoUser = Users.builder()
					.username(kakaoProfile.getKakao_account().getEmail())
					.password(cosKey)
					.email(kakaoProfile.getKakao_account().getEmail())
					.oauth("kakao")
					.build();
					
			//가입자 혹은 비가입자 체크해서 처리
			Users originUser = userService.회원찾기(kakaoUser.getUsername());
			
			if(originUser.getUsername()==null) {

				System.out.println("기존회원이 아니므로 자동 회원가입진행");
				userService.회원가입(kakaoUser);
			}
			
			System.out.println("자동 로그인 진행");
			//로그인 처리
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);		
			
			return "redirect:/";
	}
}
