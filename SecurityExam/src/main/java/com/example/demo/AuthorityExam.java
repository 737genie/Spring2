package com.example.demo;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorityExam {

	// 현재 인증된 사용자 정보 가져오는 예시.
	public void authTest() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// 사용자의 권한을 출력해보기
	    for (GrantedAuthority authority : auth.getAuthorities()) {
	        System.out.println("User Authority: " + authority.getAuthority());
	    }
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	    for (GrantedAuthority authority : authorities) {
	        System.out.println(authority.getAuthority());
	    }
	}

}
