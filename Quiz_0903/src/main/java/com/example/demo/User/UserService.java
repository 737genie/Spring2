package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	
	private final PasswordEncoder passwordEncoder;
	
    private final UserRepository userRepository;
	
	public void create(String username, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
	}

	public SiteUser getUser(String name) {
		return this.userRepository.findByusername(name).get();
	}
	
}
