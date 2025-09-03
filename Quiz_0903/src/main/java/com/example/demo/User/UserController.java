package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		
		userService.create(
				userCreateForm.getUsername(),
				userCreateForm.getPassword());
		
		return "redirect:/";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
