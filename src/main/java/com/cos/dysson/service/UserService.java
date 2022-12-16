package com.cos.dysson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.model.RoleType;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.UserRepository;

@Service
public class UserService {
	
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
	}

