package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class AgentController {
	
	private final AgentService agentService;
	
	@GetMapping("/login")
	public String login() {
		return "users/login-form";
	} // POST 어쩌고 오류 뜰때는 security경로 확인할것
	
	@GetMapping("/signup")
	public String signup(AgentForm agentForm) {
		return "users/signup-form";
	}
	
	@PostMapping("/signup")
	public String signup(AgentForm agentForm,
			BindingResult bindingResult) {
		
		agentService.create(agentForm);
		
		
		return "redirect:/";
	}
	
	
}
