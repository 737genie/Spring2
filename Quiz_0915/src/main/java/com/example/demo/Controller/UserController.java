package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.UserDto;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "users/login-form";
	}
	
	@GetMapping("/signup")
	public String signup(UserDto userDto, Model model) {
		model.addAttribute("user", userDto);
		return "users/signup-form";
	}
	
	@PostMapping("/signup")
	public String signup(UserDto userDto,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "users/signup-form";
		}
		
		this.userService.save(userDto);
		
		return "redirect:/";
	}
}
