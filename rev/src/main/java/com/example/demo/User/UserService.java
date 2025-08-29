package com.example.demo.User;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public void create(String username, String password1, String password2, String email) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password1);
		this.userRepository.save(user);
	}
	
//	public void create(UserCreateForm userCreateForm) {
//		SiteUser user = new SiteUser();
//		user.setEmail(userCreateForm.getEmail());
//	}

}

