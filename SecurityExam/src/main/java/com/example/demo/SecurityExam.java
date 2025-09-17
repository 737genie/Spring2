package com.example.demo;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

public class SecurityExam {
	// 셜록 누렁의 비밀 상황실 접근법
	
	// 변수 context에는 현재 접속중인 혹은 현재 요청된 내용을 볼 수 있음
	SecurityContext context = SecurityContextHolder.getContext();
	
	// 시큐리티 컨텍스트에서 현재 인증된 객체를 가져오는 역할
	// 포함된 정보 목록
	// 1. 사용자 ID, 이름 (principal)
	// 2. 사용자 비밀번호 : credentials
	// 3. 사용자 권한 목록
	// 4. 인증여부
	Authentication auth = context.getAuthentication();
	
	String memberName = auth.getName(); // principal 대신 username, id를 직접 가져온 경우
	Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); // ? 와일드카드

}
	// ThreadLocal 전략의 핵심 코드
final class ThreadLocalSecurityContextHolderStrategy 
    implements SecurityContextHolderStrategy {
    
    private static final ThreadLocal<SecurityContext> contextHolder = 
        new ThreadLocal<>();
    
    @Override
    public SecurityContext getContext() {
        SecurityContext ctx = contextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }
    
    // 컨텍스트 종료시 실행할 메서드
	@Override
	public void clearContext() {
		// TODO Auto-generated method stub
		
	}
	
	// 새로운 정보를 저장할 때 활용
	@Override
	public void setContext(SecurityContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SecurityContext createEmptyContext() {
		// TODO Auto-generated method stub
		return null;
	}
}


