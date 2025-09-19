package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.ClubUser;
import com.example.demo.Repository.ClubUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService{
	
	private final ClubUserRepository clubUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ClubUser clubUser = this.clubUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다." + username));
		
		clubUser.setLastLoginAt(LocalDateTime.now());
		clubUserRepository.save(clubUser);
		
		return User.builder()
                .username(clubUser.getUsername())
                .password(clubUser.getPassword())
                .authorities(mapRolesToAuthorities(clubUser))
                // -> mapRolesToAuthorities : 객체의 권한을 시큐리티에 맞게 매핑
                // accountExpired, accountLocked, credentialsExpired
                // 계정 만료 			계정 잠금			증명 xx
                .accountExpired(!clubUser.isAccountNonExpired())
                .accountLocked(!clubUser.isAccountNonLocked())
                .credentialsExpired(!clubUser.isCredentialsNonExpired())
                // is...Non...() -> false : 잠겨있음, true : 잠겨있지 않음
                // account...(content) -> content가 true면 잠금 false면 잠그지 않음
                .disabled(!clubUser.isEnabled())
                .build();
	}

	
	// 한 유저가 여러 개의 권한을 가질 경우에 쓰는 방법
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(ClubUser clubUser) {
		 return clubUser.getRoles().stream()
				 	// 사용자 역할 정보를 SimpleGrantedAuthority 타입으로 변환
	                .map(role -> new SimpleGrantedAuthority(role.name()))
	                // 동작 수행 후 결과를 List로 수집
	                .collect(Collectors.toList());
	}
	
	
//	List<SimpleGrantedAuthority> authorities = [
//	                                            new SimpleGrantedAuthority("ADMIN"),
//	                                            new SimpleGrantedAuthority("USER")
//	                                            ];

}
