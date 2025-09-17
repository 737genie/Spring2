package com.example.demo;

import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityservice implements UserDetails {

	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    String username = auth.getName();
	    String password = auth.getCredentials().toString();

	    // 사용자 이름과 비밀번호 검증 로직
	    if (isValidUser(username, password)) {
	        return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(username));
	    } else {
	        throw new BadCredentialsException("Invalid credentials");
	    }
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
