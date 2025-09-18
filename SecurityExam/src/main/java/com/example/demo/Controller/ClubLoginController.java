package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClubLoginController {
	
	@GetMapping("/club/login")
	public String loginPage(
			@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			Model model
			) {
		
		if(error!=null) {
			model.addAttribute("error", true);
		}
		if(logout!=null) {
			model.addAttribute("logout", true);
		}
		
		model.addAttribute("param", model);
		
		return "login";
	}
	
    @GetMapping("/club/access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }
}
