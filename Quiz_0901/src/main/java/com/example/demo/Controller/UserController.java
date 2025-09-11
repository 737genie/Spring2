package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.UserForm;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserForm userForm) {
		return "users/signup-form";
	}
	
	@PostMapping("/signup")
	public String signup(UserForm userForm,
			BindingResult bindingResult) {
		
		this.userService.create(
				userForm.getUsername(),
				userForm.getPassword()
				);
		
		return "redirect:/recipes";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "users/login-form";
	}
}
