package com.cos.dysson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("file:///C:/image/"); // 외부경로 window
//		registry.addResourceHandler("/img/**").addResourceLocations("file:///Users/yalu/Documents/image/"); // 외부경로 mac

		
		
	}
	
}
