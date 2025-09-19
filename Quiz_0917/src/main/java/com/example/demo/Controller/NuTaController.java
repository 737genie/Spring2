package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Domain.NuTaBook;
import com.example.demo.Service.NuTaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NuTaController {
	
	private final NuTaService nutaService;
	
	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/books")
	public String main(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			Model model
			) {
		
		Page<NuTaBook> book = this.nutaService.findAll(page, keyword);
		model.addAttribute("paging", book);
		model.addAttribute("keyword", keyword);
		
		return "main";
	}
}
