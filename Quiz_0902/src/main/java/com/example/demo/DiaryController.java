package com.example.demo;

import java.security.Principal;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.User.DiaryKeeper;
import com.example.demo.User.KeeperService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DiaryController {

    private final AuthenticationManager authenticationManager;
	
	private final DiaryService diaryService;

    private final KeeperService keeperService;
	
	@GetMapping("/")
	public String basic() {
		return "index";
	}
	
	@GetMapping("/entries")
	public String showDiary(Model model, 
			Principal principal) {
		String username = principal.getName();
		List<Diary> entries = this.diaryService.getList(username);
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
			Principal principal,
			DiaryKeeper keeper) {
		
		DiaryKeeper diaryKeeper = this.keeperService.getUser(principal.getName());
		this.diaryService.save(diaryCreateForm, diaryKeeper);
		
		return "redirect:/entries";
	}
	
	@GetMapping("/entries/{id}/edit")
	public String editDiary(Model model,
			@PathVariable("id") Long id) {
		
		Diary d = this.diaryService.getDiary(id);
		model.addAttribute("entry", d);
		
		return "edit-form";
	}
	
	@PostMapping("/entries/{id}/edit")
	public String editDiary(
			@PathVariable("id") Long id,
			DiaryCreateForm diaryCreateForm) {
		
		Diary d = this.diaryService.getDiary(id);
		this.diaryService.update(d, diaryCreateForm);
		
		return "redirect:/entries";
	}
	
	@PostMapping("/entries/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		
		this.diaryService.delete(id);
		
		return "redirect:/entries";
	}
}
