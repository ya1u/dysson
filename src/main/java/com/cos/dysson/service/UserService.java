package com.cos.dysson.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.dysson.dto.SendTmpPwdDto;
import com.cos.dysson.dto.UserRequestDto;
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
	public void 회원가입(Users user) {	//카카오 로그인위해 메소드 두개사용
		String rawPassword =user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRoles(RoleType.user);
		userRepository.save(user); //하나의 트랜젝션
	}


	
	@Transactional
	public void 회원가입(UserRequestDto userDto) {
		System.out.println("테스트테스트테스트테스트테스트테스트테스트" + userDto);
		Users user = Users.builder()
				.username(userDto.getUsername())
				.password(encodeer.encode(userDto.getPassword()))
				.email(userDto.getEmail())
				.address(userDto.getAddress())
				.name(userDto.getName())
				.phone(userDto.getPhone())
				.roles(RoleType.user)
				.build();
		
		userRepository.save(user);
	}

	@Transactional
	public void userDel(int id) { //유저삭제
		userRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> validateHandling(BindingResult bindingResult) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	@Transactional(readOnly = true)
	public Users 회원찾기(String username) {
		
		//orElseGet-회원을 찾았는데 없으면 빈 객체를 리턴
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
			
			
	}
	
	@Transactional
	public void 회원수정(Users user) {
		Users persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword=user.getPassword();
		String encPassword= encodeer.encode(rawPassword);
		String email = user.getEmail();
		String address = user.getAddress();
		persistance.setPassword(encPassword);
		persistance.setEmail(email);
		persistance.setAddress(address);
		
		//회원수정 메서드종료 = 서비스종료  = 트랜잭션 종료= commit
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹 되어 update문 날림.
	}
	@Transactional
	public boolean chkUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	
	@Value("${spring.mail.username}")
	private String sendFrom;
	
	
	//임시패스워드 발송
	@Transactional
	public void sendTmpPwd(SendTmpPwdDto dto) {
		
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String tmpPwd = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            tmpPwd += charSet[idx];
        }
		
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(dto.getEmail());
            message.setFrom(sendFrom);
            message.setSubject("Dysson 임시 비밀번호 안내 이메일입니다.");
            message.setText("안녕하세요.\n"
            		+ "Dysson 임시비밀번호 안내 관련 이메일 입니다.\n"
            		+ "임시 비밀번호를 발급하오니 http://localhost:8050/auth/loginForm 에 접속하셔서 로그인 하신 후\n"
            		+ "반드시 비밀번호를 변경해주시기 바랍니다.\n\n"
            		+ "임시 비밀번호 : " + tmpPwd);
            javaMailSender.send(message);
        } catch (MailParseException e) {
            e.printStackTrace();
        } catch (MailAuthenticationException e) {
            e.printStackTrace();
        } catch (MailSendException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }
		
        Users user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> {
            return new IllegalArgumentException("임시 비밀번호 변경 실패: 사용자 이름을 찾을 수 없습니다.");
        });
		
        user.setPassword(encodeer.encode(tmpPwd));
	}
		

	
	
	
	@Transactional(readOnly = true)
	public Users findUser(int id) {
		Users users = userRepository.findById(id).orElseGet(()->{
			return new Users();
		});
		return users;
	}
	
}
	


