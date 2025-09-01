package com.example.demo.User;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public void create(String username, String password1, String email) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password1));
		this.userRepository.save(user);
	}

	public SiteUser getUser(String name) {
		return this.userRepository.findByusername(name).get();
	}

}
//	public void create(UserCreateForm userCreateForm) {
//		SiteUser user = new SiteUser();
//		user.setEmail(userCreateForm.getEmail());
//	}


