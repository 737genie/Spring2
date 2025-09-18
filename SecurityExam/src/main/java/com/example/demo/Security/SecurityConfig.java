package com.example.demo.Security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain clubFilterChain(HttpSecurity http) throws Exception{
		
		return http
				.csrf(csrf -> {
					csrf.disable(); // 개발단계
					// 운영단계 :
//					csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
				})
				// 세션관리 & 세션 설정 정의할 때 사용
				// .sessionCreationPolicy(...) -> 세션생성정책
				// If_REQUIRED : 필요할 때만 세션 생성하도록 설정
				// 필요함의 기준 : 인증이 필요할때
				// ALWAYS, NEVER, STATELESS 등
				// maximumSessions(1) : 한 사용자가 동시에 유지할 수 있는 세션
				// maxSessionsPreventsLogin : 최대 세션 수를 초과 했을 때 어떻게 동작할 것인가를 정의
				// false : 이전 세션 만료 -> 새로운 로그인 허용
				// true : 새로운 로그인 차단 & 기존 세션 유지
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
						.maximumSessions(1)
						.maxSessionsPreventsLogin(false)
						)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/club/login", "club/register",
								"/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
						.requestMatchers("/club/vip/**").hasRole("VIP")
						.requestMatchers("/club/admin/**").hasRole("ADMIN")
						.requestMatchers("/club/secret/**").hasAuthority("SECRET_ACCESS")
						.anyRequest().authenticated()
						)
				.formLogin(form -> form
						.loginPage("/club/login")
						.loginProcessingUrl("/club/authentication")
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/club/main", true)
						.failureUrl("/club/login?error=true")
						.successHandler(successHandler)              // 성공 핸들러
	                    .failureHandler(failureHandler)
	                    .permitAll()
						)
				// Logout 설정 (퇴장 처리용!)
                .logout(logout -> logout
                    .logoutUrl("/club/logout")                   // 로그아웃 URL
                    .logoutSuccessUrl("/club/login?logout=true") // 로그아웃 성공 시 이동할 페이지
                    .invalidateHttpSession(true)                // 세션 무효화
                    .deleteCookies("JSESSIONID")                // 쿠키 삭제
                    .clearAuthentication(true)                  // 인증 정보 삭제
                    .permitAll()
                )
               // 예외 처리 설정
                .exceptionHandling(ex -> ex
                		// 인증되지 않은 사용자가 접근 시도 시
                		// 해당 요청 URI를 로그에 출력
                    .authenticationEntryPoint((request, response, authException) -> {
                        System.out.println("인증되지 않은 접근 시도: " + request.getRequestURI());
                        response.sendRedirect("/club/login");
                    })
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        System.out.println("권한 없는 접근 시도: " + request.getRequestURI());
                        response.sendRedirect("/club/access-denied");
                    })
                )
             // Remember Me 기능 (선택사항)
                .rememberMe(remember -> remember
                    .key("clubSecretKey")
                    .tokenValiditySeconds(7 * 24 * 60 * 60) // 7일 초단위
                    .userDetailsService(clubUserDetailsService)
                )
                
		.build();
	}
	
    /**
     * 셜록 누렁의 성공 핸들러용!
     */
    @Component
    public static class ClubAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, 
                                          HttpServletResponse response,
                                          Authentication authentication) throws IOException {
            
            System.out.println("클럽 입장 성공: " + authentication.getName() + "님 환영합니다용!");
            
            // 사용자 역할에 따른 리다이렉트 (선택사항)
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    System.out.println("🔱 관리자가 입장하셨습니다!");
                    response.sendRedirect("/club/admin/dashboard");
                    return;
                }
                if (authority.getAuthority().equals("ROLE_VIP")) {
                    System.out.println("💎 VIP 회원이 입장하셨습니다!");
                    response.sendRedirect("/club/vip/lounge");
                    return;
                }
            }
            
            // 기본 사용자는 메인홀로
            response.sendRedirect("/club/main");
        }

    }

    /**
     * 셜록 누렁의 실패 핸들러용!
     */
    @Component
    public static class ClubAuthenticationFailureHandler implements AuthenticationFailureHandler {
        
  

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, 
                                          HttpServletResponse response,
                                          AuthenticationException exception) throws IOException {
            
            System.out.println("❌ 클럽 입장 실패: " + exception.getMessage());
            
            // 실패 원인별 메시지 (선택사항)
            String errorMessage = "로그인에 실패했습니다용!";
            
            if (exception instanceof BadCredentialsException) {
                errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다용!";
            } else if (exception instanceof DisabledException) {
                errorMessage = "계정이 비활성화되었습니다용! 관리자에게 문의해주세요.";
            } else if (exception instanceof AccountExpiredException) {
                errorMessage = "계정이 만료되었습니다용! 갱신이 필요해요.";
            }
            
            // 세션에 에러 메시지 저장 후 리다이렉트
            request.getSession().setAttribute("errorMessage", errorMessage);
            response.sendRedirect("/club/login?error=true");
        }
    }
}
