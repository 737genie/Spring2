package com.example.demo.JWT;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {
	private final SecretKey key;
	private final long validityInMilliseconds;
	
	public JwtTokenProvider(SecretKey key, long validityInMilliseconds) {
		this.key = key;
		this.validityInMilliseconds = validityInMilliseconds;
	}
	
	public String createToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		// 토큰 유효기간 지정
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.validityInMilliseconds);
        
        
        // subject : 토큰의 주체 설정
        // claim : 사용자의 권한 정보를 저장할 때 씀
        // signWith : 지정된 키로 토큰에 서명 : 토큰의 무결성 보장
        // expiration : 만료 시간을 계산하여 집어 넣음
        // compact : 최종 JWT 문자열 생성
        // ==> Base64URL로 인코딩된 형태
		return Jwts.builder()
				.subject(authentication.getName())
				.claim("auth", authorities)
				.signWith(key)
				.expiration(validity)
				.compact();
	}
	
	// JWT 파싱을 위한 파서 객체 생성
	// verifyWith : 토큰 서명 검증을 위한 키 설정
	// -> 조건 : 토큰 생성 시 사용된 키와 동일해야함
	// -> 만약 잘못된 키로 검증하면 SignatureException 발생
	// build : 설정된 옵션으로 파서 생성
	// parseSignedClaims : 실제 JWT 문자열 파싱(서명의 검증)
	// -> 잘못된 토큰일 경우 JWTException 발생
	// getPayload : 검증된 토큰에서 Claims(payload 영역) 정보를 추출
	// ---> Claims는 토큰에 포함된 사용자 정보와 메타 데이터 포함되어 있음
	public Authentication getAuthentication(String token) {
		
		Claims claims = Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		
		return null;
	}
	
	public boolean validateToken() {
		
	}
	
}
