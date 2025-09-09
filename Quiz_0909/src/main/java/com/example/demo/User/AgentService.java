package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgentService {
	private final AgentRepository agentRepository;
	private final PasswordEncoder passwordEncoder;

	public void create(AgentForm agentForm) {
		Agent agent = Agent.builder()
				.username(agentForm.getUsername())
				.password(passwordEncoder.encode(agentForm.getPassword()))
				.role(AgentRole.AGENT)
				.build();
		
		this.agentRepository.save(agent);
		
	}
	
	
}
