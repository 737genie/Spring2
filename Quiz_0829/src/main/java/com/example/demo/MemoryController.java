package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemoryController {

	private final MemoryService memoryService;
	
	@GetMapping("/memories")
	public String showMemoryList(Model model) {
		List<Memory> memories = this.memoryService.getList();
		model.addAttribute("memories", memories);
		return "list";
	}
	
	@GetMapping("/memories/new")
	public String create(MemoryForm memoryForm) {
		return "new-form";
	}
	
	@PostMapping("/memories/new")
	public String create(@Valid MemoryForm memoryForm,
			BindingResult bindingResult) {
		
		this.memoryService.createMemory(
				memoryForm.getSource(),
				memoryForm.getTaste(),
				memoryForm.getRating());
		
		
		return "redirect:/memories";
	}
	
	@GetMapping("/memories/{id}/edit")
	public String updateMemory(Model model,
			@PathVariable("id") Long id) throws Exception {
		
		Memory m = this.memoryService.getMemory(id);
		model.addAttribute("memory", m);
		
		return "edit-form";
	}
	
	@PostMapping("/memories/{id}/edit")
	public String updateMemory(Model model,
			@PathVariable("id") Long id,
			MemoryForm memoryForm) throws Exception {
		
		Memory m = this.memoryService.getMemory(id);
		memoryService.update(m, memoryForm);
		
		return "redirect:/memories";
	}
	
	
	@PostMapping("/memories/{id}/delete")
	public String deleteMemory(@PathVariable("id") Long id) {
		memoryService.delete(id);
		return "redirect:/memories";
	}
}
