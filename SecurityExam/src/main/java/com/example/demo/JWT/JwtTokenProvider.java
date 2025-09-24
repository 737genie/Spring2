package com.example.demo.JWT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final SecretKey key;
	private final long validityInMilliseconds;
	
    public JwtTokenProvider(@Value("${jwt.secret:DefaultSecretKeyDefaultSecretKeyDefaultSecretKeyDefaultSecretKey}") String secretKey,
            @Value("${jwt.expiration:3600000}") long validityInMilliseconds) {
    		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    		this.key = Keys.hmacShaKeyFor(keyBytes);
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
		
		// 권한 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
		
		
//      Optional.ofNullable(claims.get("auth"))
//      .map(Object::toString)
//      .map(auth -> auth.split(","))
//      .map(Arrays::stream)
//      .filter(StringUtils::hasText)
//      .map(SimpleGrantedAuthority::new)
//      .collect(Collectors.toList());
        
        
        UserDetails principal = new User(claims.getSubject(), "", authorities);
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	// JWT 토큰 검증 영역
	public boolean validateToken(String token) {
		
		// JWT 파싱을 위한 새로운 파서 빌더 생성
		// -> JWT 서명을 검증하는데 사용할 비밀 키 설정
		// -> 설정된 옵션으로 JWT 파서 생성
		// -> 주어진 JWT 토큰 문자열 파싱 후 서명 검증
		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			return true;
		} catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			System.out.println("잘못된 JWT 시그니처입니다.");
		} catch(ExpiredJwtException e) {
			System.out.println("만료된 토큰입니다.");
		} catch(UnsupportedJwtException e) {
			System.out.println("지원하지 않는 JWT 토큰입니다.");
		} catch(IllegalArgumentException e){
			System.out.println("지원하지 않는 토큰 형식입니다.");
		} catch(Exception e) {
			System.out.println("error");
		}
		
		return false;
	}
	
}
