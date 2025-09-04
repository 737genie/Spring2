package com.example.demo;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/diaries")
	public String showList(Model model) {
		List<HangoverDiary> diaries = this.diaryService.getList();
		model.addAttribute("diaries",diaries);
		return "list";
	}
	
	@GetMapping("/diaries/new")
	public String createDiary(DiaryForm diaryForm) {
		return "new-form";
	}
	
	@PostMapping("/diaries/new")
	public String createDiary(
			@Valid DiaryForm diaryForm,
			@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		diaryService.save(diaryForm, file);
		
		return "redirect:/diaries";
	}
	
	@GetMapping("/diaries/{id}/edit")
	public String editDiary(
			DiaryForm diaryForm,
			@PathVariable("id") Long id,
			Model model) throws Exception {
		
		HangoverDiary diary = this.diaryService.getDiary(id);
		model.addAttribute("diary", diary);
		
		diaryForm.setTitle(diary.getTitle());
		diaryForm.setSymptoms(diary.getSymptoms());
		
		return "edit-form";
	}
	
	@PostMapping("/diaries/{id}/edit")
	public String editDiary(
			DiaryForm diaryForm,
			@PathVariable("id") Long id,
			BindingResult bindingResult,
			Model model) throws Exception {
		
		HangoverDiary diary = this.diaryService.getDiary(id);
		model.addAttribute("diary", diary);
		
		this.diaryService.edit(diary, 
				diaryForm.getTitle(),
				diaryForm.getSymptoms());
		
		return "redirect:/diaries";
	}
	
	@PostMapping("/diaries/{id}/delete")
	public String deleteDiary(@PathVariable("id") Long id) {
		diaryService.delete(id);
		return "redirect:/diaries";
	}
}
