package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Domain.NuTaMemberSignupDto;
import com.example.demo.Service.NuTaUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NuTaUserController {
	
	private final NuTaUserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("dto", new NuTaMemberSignupDto());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid NuTaMemberSignupDto dto,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("dto", new NuTaMemberSignupDto());
			return "signup";
		}
		
		if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
	        bindingResult.rejectValue("passwordConfirm", "passwordInconsistency", "비밀번호가 일치하지 않습니다.");
	        return "signup";
	    }
		
		this.userService.save(dto);
		
		return "redirect:/login";
	}
	
}
