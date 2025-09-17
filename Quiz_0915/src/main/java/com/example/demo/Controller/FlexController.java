package com.example.demo.Controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.PostDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Service.FlexService;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FlexController {
	private final FlexService flexService;
	private final UserService userService;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/posts")
	public String read(Model model,
			@RequestParam(value="keyword", defaultValue="") String keyword,
			@RequestParam(value="page", defaultValue="0") int page) {
		Page<Flex> flex = this.flexService.findAll(keyword, page);
		model.addAttribute("keyword", keyword);
		model.addAttribute("posts", flex);
		return "posts/list";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/posts/new")
	public String create(PostDto postDto) {
		return "posts/new-form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/posts/new")
	public String create(PostDto postDto,
			BindingResult bindingResult,
			@RequestParam("imageFile") MultipartFile file,
			Principal principal) throws IllegalStateException, IOException {
		
		if(bindingResult.hasErrors()) {
			return "posts/new-form";
		}
		
		FlexUser flexUser = this.userService.getUser(principal.getName());
		
		this.flexService.create(postDto, file, flexUser);
		
		return "redirect:/posts";
	}
	
	@GetMapping("/posts/{id}")
	public String detail(@PathVariable("id") Long id,
			Model model) {
		
		Flex flex = this.flexService.detail(id);
		model.addAttribute("post", flex);
		
		return "posts/detail";
	}
	
	@GetMapping("/posts/{id}/edit")
	public String edit(@PathVariable("id") Long id,
			Model model, PostDto postDto) {
		
		Flex flex = this.flexService.detail(id);
		postDto.setTitle(flex.getTitle());
		postDto.setContent(flex.getContent());
		
		model.addAttribute("post", flex);

		return "posts/edit-form";
	}
	
	@PostMapping("/posts/{id}/edit")
	public String edit(@PathVariable("id") Long id,
			Model model,
			PostDto postDto,
			BindingResult bindingResult) {
		
		Flex flex = this.flexService.detail(id);
		model.addAttribute("post", flex);
		
		this.flexService.edit(postDto, flex);
		
		return "redirect:/posts";
	}
	
	@PostMapping("/posts/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		
		this.flexService.delete(id);
		
		return "redirect:/posts";
	}
	
}
