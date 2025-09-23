package com.example.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/club/vip")
@Controller
public class VipController {
	
	@GetMapping("/")
	public String root() {
		return "redirect:/club/vip/lounge";
	}
	
	@GetMapping("/lounge")
	@PreAuthorize("hasRole('VIP')")
	public String vipLounge() {
		return "vip-lounge";
	}
}
