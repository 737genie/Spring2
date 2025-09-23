package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
	
	// 왜 pwdEncoder를 분리하는가?
	// -> 순환 참조 때문에
	// --> SecurityConfig에서 상황에 맞게 CustomOAuthUserService를 생성자로 받음
	// --> COS 내부적으로 pwdEncoder 사용
	// --> COS도 SecurityCOnfig가 참조하고 있고 pwdEncoder가 있음
	// ---> 따라서 서로를 무한 호출 
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 셜록 누렁: "BCrypt로 비밀번호를 안전하게 암호화합니다용!"
		return new BCryptPasswordEncoder(12); // strength를 12로 설정하여 더 강력한 암호화
	}
}
