package com.cos.dysson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.model.RoleType;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //생성자 주입
public class UserService {
	
	private final JavaMailSender javaMailSender;//starter-mail이 제공하는 인터페이스
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encodeer;
	
	@Transactional
	public void 회원가입(Users user) {
		String rawPassword =user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRoles(RoleType.user);
		userRepository.save(user); //하나의 트랜젝션
	}
	
	@Transactional(readOnly = true)
	public Users 회원찾기(String username) {
		
		//orElseGet-회원을 찾았는데 없으면 빈 객체를 리턴
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
			
			
	}
	
	//비밀번호 찾기 구현중!
	
//	@Value("${String.mail.username}")
//	private String sendFrom;
//	
//	@Transactional
//	public void sendTmpPwd(SendTmpPwdDto dto) {
//		
//        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//
//        String tmpPwd = "";
//        
//        //문자 배열의 길이의 값을 랜덤으로 10개를 뽑아 구문작성
//        int idx = 0;
//        for (int i = 0; i < 10; i++) {
//        	idx = (int)(charSet.length * Math.random());
//        	tmpPwd += charSet[idx];
//        }
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(dto.getEmail());
//            message.setFrom(sendFrom);
//            message.setSubject("baeg-won 임시 비밀번호 안내 이메일입니다.");
//            message.setText("안녕하세요.\n"
//            		+ "baeg-won 임시비밀번호 안내 관련 이메일 입니다.\n"
//            		+ "임시 비밀번호를 발급하오니 블로그에 접속하셔서 로그인 하신 후\n"
//            		+ "반드시 비밀번호를 변경해주시기 바랍니다.\n\n"
//            		+ "임시 비밀번호 : " + tmpPwd);
//            javaMailSender.send(message);
//	        } catch (MailParseException e) {
//	            e.printStackTrace();
//	        } catch (MailAuthenticationException e) {
//	            e.printStackTrace();
//	        } catch (MailSendException e) {
//	            e.printStackTrace();
//	        } catch (MailException e) {
//	            e.printStackTrace();	      
//	        }
//        
//	        Users user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> {
//	            return new IllegalArgumentException("임시 비밀번호 변경 실패: 사용자 이름을 찾을 수 없습니다.");
//	        });
//        
//	        user.setPassword(encodeer.encode(tmpPwd));
//        }
		

	
	
	
	@Transactional(readOnly = true)
	public Users findUser(int id) {
		Users users = userRepository.findById(id).orElseGet(()->{
			return new Users();
		});
		return users;
	}
	
}
	


