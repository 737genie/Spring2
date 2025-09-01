package com.example.demo.User;

import lombok.Getter;

// 현업에선 보기 힘든 형태
// 권한 관련하여 처리할 것이 거의 없는 경우 활용
@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value) {
		this.value=value;
	}
}
