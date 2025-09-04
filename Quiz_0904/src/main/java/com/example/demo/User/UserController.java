package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(Model model, UserForm userForm) {
		model.addAttribute("user", userForm);
		return "users/signup-form";
	}
	
	
}
