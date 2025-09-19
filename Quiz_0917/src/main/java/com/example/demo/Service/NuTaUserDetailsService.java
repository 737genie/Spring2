package com.example.demo.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.NuTaMember;
import com.example.demo.Repository.NuTaMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NuTaUserDetailsService implements UserDetailsService{
	
	private final NuTaMemberRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		NuTaMember ntUser = this.userRepository.findByUsername(username);
		
		return User.builder()
				.username(ntUser.getUsername())
				.password(ntUser.getPassword())
				.roles(ntUser.getRole().toString())
				.build();
	}
	
}
