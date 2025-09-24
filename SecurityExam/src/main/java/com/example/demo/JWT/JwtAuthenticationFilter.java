package com.example.demo.JWT;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// JWT 품질 검사
// OncePerRequestFilter : 시큐리티에서 제공하는 기본 필터
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
	public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
	
    // 모든 HTTP 요청이 이 필터를 거쳐감
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = resolveToken(request);
		
		// jwt Token이 유효한 지 확인
		// 서명, 만료시간 등
		if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
			// getAuthentication : jwt 기반으로 인증 정보 생성 및 리턴
			// jwt Token을 디코딩하여 검증 후 Authentication으로 해당 정보가 들어간 객체 생성
			Authentication auth = jwtTokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(auth);
			// getContext : 컨텍스트(정보)를 가져온 후 
			// setAuthentication 메서드를 통해 인증 정보 설정
			
			// 필터 처리 완료 후 최종 서블릿으로 원래의 요청을 전달
			// 이곳은 기존 요청 자체를 스틸 해오는 곳
			filterChain.doFilter(request, response);
		}
		
	}

	private String resolveToken(HttpServletRequest request) {
		
		// Authorization_Header ; jwt Header
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		
		// bearerToken이 Null이 아니고 공백이 아닌 실제 텍스트를 포함하는지 확인
		// bearerToken이 (literal) Bearer 값으로 시작하는지 확인 
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
        	// Token의 시작이 Bearer임을 확인했으니 그 문자열을 빼고 Token 반환
        	return bearerToken.substring(BEARER_PREFIX.length());
        }
		return null;
	}

}
