package com.example.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClubMainController {
	
	private final ClubUserDetailService userDetailService;
	
	@GetMapping("/club/main")
	public String main(Authentication authentication,
			Model model) {
		
		
		model.addAttribute("username", authentication.getName());
		model.addAttribute("authorities", authentication.getAuthorities());
		
		return "main";
	}
	
    @GetMapping("/club/lounge")
    public String loungePage() {
        return "lounge";
    }
    
    @GetMapping("/club/games")
    public String gamesPage() {
        return "games";
    }
    
    @GetMapping("/club/counseling")
    public String counselingPage() {
        return "counseling";
    }
    
    
    @GetMapping("/club/profile")
    public String profilePage(Authentication authentication, Model model) {
        
    // 사용자 정보 조회
    var user = userDetailsService.findByUsername(authentication.getName());
    model.addAttribute("user", user);
        
        return "profile";
    }
}
