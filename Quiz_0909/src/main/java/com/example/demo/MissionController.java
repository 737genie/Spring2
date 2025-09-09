package com.example.demo;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MissionController {
	
	private final MissionService missionService;
	
	@GetMapping("/")
	public String root() {
		return "home";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/missions/my-missions")
	public String showMyMission(Model model,
			Principal principal) {
		List<Mission> myMission = missionService.getMyList();
		model.addAttribute("missions", myMission);
		
		return "my-list";
	}
	
}
