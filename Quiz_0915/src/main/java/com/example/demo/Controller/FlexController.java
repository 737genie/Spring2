package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.PostDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Service.FlexService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FlexController {
	private final FlexService flexService;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/posts")
	public String read(Model model) {
		Page<Flex> flex = this.flexService.findAll();
		model.addAttribute("posts",flex);
		return "posts/list";
	}
	
	@GetMapping("/posts/new")
	public String create(PostDto postDto) {
		return "posts/new-form";
	}
	
	@PostMapping("/posts/new")
	public String create(PostDto postDto,
			BindingResult bindingResult,
			@RequestParam("file") MultipartFile file,
			FlexUser user) throws IllegalStateException, IOException {
		
		if(bindingResult.hasErrors()) {
			return "posts/new-form";
		}
		
		this.flexService.create(postDto, file, user);
		
		return "redirect:/";
	}
}
