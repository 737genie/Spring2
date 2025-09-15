package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/keepers")
public class KeeperController {
	
	private final KeeperService keeperService;
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	
	@GetMapping("/signup")
	public String signup(DiaryKeeper keeper, Model model) {
		model.addAttribute("keeper", keeper);
		return "signup-form";
	}
	
	@PostMapping("/signup")
	public String signup(DiaryKeeper keeper,
			BindingResult bindingResult) {
		
		this.keeperService.create(
				keeper.getUsername(),
				keeper.getPassword());
		
		return "redirect:/";
	}
}
