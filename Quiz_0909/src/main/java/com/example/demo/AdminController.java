package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/dashboard")
	public String showDash(Model model) {
		List<Mission> missions = adminService.getList();
		model.addAttribute("missions", missions);
		return "/admin/dashboard";
	}
	
	@GetMapping("/missions/new")
	public String createMission(MissionForm missionForm) {
		return "admin/mission-form";
	}
	
	@PostMapping("/missions/new")
	public String createMission(MissionForm missionForm,
			BindingResult bindingResult) {
		
		adminService.save(missionForm);
		
		return "redirect:admin/dashboard";
	}
}
