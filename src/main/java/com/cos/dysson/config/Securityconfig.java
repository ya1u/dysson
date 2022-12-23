package com.cos.dysson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.dysson.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Securityconfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public AuthenticationManager getAuthentication() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("로그인 패스워드 인코더 호출");
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
			.csrf().disable()
			.rememberMe()//자동로그인
				.rememberMeParameter("remember")//해당 체크박스의 name 속성 값을 지정			
				.userDetailsService(principalDetailService)// user정보
			.and()
			.authorizeRequests()
			
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/fonts/**","/product/**","/board/**","/img/**","/_assets/**")
			.permitAll()
			.anyRequest()//이게 아닌 다른 모든 요청은 
			.authenticated()//인증이 필요
			
			
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
			
			.defaultSuccessUrl("/");
		
		
			
		
	}

}
