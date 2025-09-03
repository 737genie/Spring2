package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@GetMapping("/")
	public String basic() {
		return "index";
	}
	
	@GetMapping("/entries")
	public String showDiary(Model model) {
		List<Diary> entries = this.diaryService.getList();
		model.addAttribute("entries", entries);
		return "list";
	}
	
	@GetMapping("/entries/new")
	public String createDiary(DiaryCreateForm diaryCreateForm) {
		return "new-form";
	}
	
	@PostMapping("/entries/new")
	public String createDiary(DiaryCreateForm diaryCreateForm,
			Model model, 
			BindingResult bindingResult) {
		
		this.diaryService.save(diaryCreateForm);
		
		return "redirect:/entries";
	}
}
