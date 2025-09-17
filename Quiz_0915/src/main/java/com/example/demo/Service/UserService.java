package com.example.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void save(UserDto userDto) {
		FlexUser user = new FlexUser(
				userDto.getUsername(),
				passwordEncoder.encode(userDto.getPassword())
				);
		
		this.userRepository.save(user);
	}

	public FlexUser getUser(String name) {
		return this.userRepository.findByUsername(name).get();
	}
	
	
}
