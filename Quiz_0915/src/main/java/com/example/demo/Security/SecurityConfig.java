package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorizeHttpRequests)
				-> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**"))
//						new AntPathRequestMatcher("/"),
//						new AntPathRequestMatcher("/users/login"),
//						new AntPathRequestMatcher("/users/signup"))
				.permitAll())
//				.anyRequest().authenticated())
				.csrf((csrf) -> csrf
						.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
				.headers((headers) -> headers
						.addHeaderWriter(new XFrameOptionsHeaderWriter(
								XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin
			    		.loginPage("/users/login")
			    		.defaultSuccessUrl("/")
			    		)
			    .logout((logout) -> logout
			    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			    		.logoutSuccessUrl("/")
			    		.invalidateHttpSession(true)
			    		)
				;
		return http.build();
		
	}
		
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
	    @Bean // 인증 관련 내용을 Bean 객체로 등록하여 우리 서비스 전체에서 사용할 수 있도록 설정
	    
	    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        // AuthenticationConfiguration : 웹 서비스의 인증 설정을 캡슐화
	    	//-> getAuthenticationManager 이라는 메서드를 호출해 가져옴
	    	return authenticationConfiguration.getAuthenticationManager();
	    }
		
		

}
