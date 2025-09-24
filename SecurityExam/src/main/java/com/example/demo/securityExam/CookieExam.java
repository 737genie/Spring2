package com.example.demo.securityExam;

import java.util.Date;

import io.jsonwebtoken.Claims;

public class CookieExam {
	
	public void jwtProperties() {
	    // 와플곰의 비밀 재료 (절대 공개하면 안 돼요!)
	    private String secret = "waffle-bear-secret-ingredient-for-membership-cookies-very-long-key-256-bits";
	    
	    // Access 쿠키 유통기한 (1시간)
	    private long accessTokenExpiration = 60 * 60 * 1000; // 1 hour
	    
	    // Refresh 쿠키 유통기한 (7일)  
	    private long refreshTokenExpiration = 7 * 24 * 60 * 60 * 1000; // 7 days
	    
	    // 쿠키 제작자 서명
	    private String issuer = "waffle-club";
	    
	    // Authorization 헤더 접두사
	    private String headerPrefix = "Bearer ";

	}

	public boolean valCookie(String jwtCookie) {
		
		String[] parts = jwtCookie.split("\\.");
		String header1 = parts[0];
		String payload1 = parts[1];
		String signature1 = parts[2];
		
        String expectedSignature = createSignature(header1 + "." + payload1);
        
        // 3단계: 서명이 일치하는지 확인
        if (!signature1.equals(expectedSignature)) {
            System.out.println(" 와플곰: 가짜 쿠키예요! 제가 만든 게 아니에요!");
            return false;
        }
        
        // 4단계: 유통기한 확인
        Claims claims = extractClaims(jwtCookie);
        if (claims.getExpiration().before(new Date())) {
            System.out.println("와플곰: 유통기한이 지난 쿠키예요!");
            return false;
        }
        
        System.out.println(" 와플곰: 정품 쿠키 확인! 맛있게 드세요!");
        return true;

	}
	
}
