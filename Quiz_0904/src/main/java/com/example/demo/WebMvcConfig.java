package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	private String uploadPath = "file:\\C:\\project0904\\";
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	// 리소스들의 저장 경로 설정
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
	
}
