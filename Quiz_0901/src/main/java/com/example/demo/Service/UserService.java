package com.example.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SiteUser;
import com.example.demo.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void create(String username, String password) {
		SiteUser user = new SiteUser(
				username, passwordEncoder.encode(password));
		this.userRepository.save(user);
	}
	
	public SiteUser getUser(String name) {
		return this.userRepository.findByusername(name).get();
	}
	
}
