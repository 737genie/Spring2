package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeeperService {
	
	private final KeeperRepository keeperRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public void create(String username, String password) {
		DiaryKeeper keeper = DiaryKeeper.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.build();
		
		keeperRepository.save(keeper);
	}

	public DiaryKeeper getUser(String name) {
		return this.keeperRepository.findByUsername(name).get();
	}

}
