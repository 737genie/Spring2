package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.TestPost;
import com.example.demo.Entity.TestPostDto;
import com.example.demo.Service.Test2Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Test2Controller {
	private final Test2Service test2Service;
	
	@GetMapping("/show")
	public String show(Model model,
			@RequestParam(value="page", defaultValue = "0") int page) {
		Page<TestPost> post = this.test2Service.findAll(page);
		model.addAttribute("paging", post);
		return "board";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("testPost", new TestPostDto());
		return "create";
	}
	
	@PostMapping("/create")
	public String create(Model model, 
			@ModelAttribute("testPost") @Valid TestPostDto testPostDto,
			BindingResult bindingResult) {
		
		model.addAttribute("testPost", testPostDto);
		
		if(bindingResult.hasErrors()) {
			return "create";
		}
		
		this.test2Service.save(testPostDto);
		
		return "redirect:/show";
	}
	
	@GetMapping("/show/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		TestPost post = this.test2Service.findById(id);
		model.addAttribute("post", post);
		return "detail";
	}
	
	@GetMapping("/show/update/{id}")
	public String update(@PathVariable("id") Long id,
		TestPostDto testPostDto,
		Model model) {
		
		TestPost post = this.test2Service.findById(id);
		model.addAttribute("post", post);

		return "update";
	}
	
	@PostMapping("/show/update/{id}")
	public String update(@PathVariable("id") Long id,
			TestPostDto testPostDto) {
		
		this.test2Service.update(id, testPostDto);
		
		return "redirect:/show/{id}";
	}
	
	@PostMapping("/show/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		
		this.test2Service.delete(id);
		
		return "redirect:/show";
	}
	
	
}
