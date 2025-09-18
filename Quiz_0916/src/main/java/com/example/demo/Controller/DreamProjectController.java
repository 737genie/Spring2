package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.DreamFragment;
import com.example.demo.Service.DreamProjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DreamProjectController {
	
	private final DreamProjectService dreamProjectService;
	
	@GetMapping("/workshop")
	public String show(Model model) {
		List<DreamFragment> fragment = this.dreamProjectService.findNullFrag();
		model.addAttribute("unassignedFragments", fragment);
		return "home";
	}
}
