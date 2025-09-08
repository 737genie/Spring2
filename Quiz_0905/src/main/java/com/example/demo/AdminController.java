package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final StoreService storeService;
	
	@GetMapping("")
	public String root(Model model) {
		List<Store> stores = this.storeService.getList();
		model.addAttribute("stores", stores);
		return "admin/dashboard";
	}
	
	@GetMapping("/stores/new")
	public String createStore(StoreForm storeForm) {
		return "admin/store-form";
	}
	
	@PostMapping("/stores/new")
	public String createStore(StoreForm storeForm,
			BindingResult bindingResult
			) {
		
		this.storeService.save(storeForm);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/stores/{id}/items/new") 
	public String addItem(@PathVariable("id") Long id,
			ItemForm itemForm,
			@RequestParam("file") MultipartFile file,
			Model model
			) {
		
		Store store = this.storeService.getStore(id);
		
		
		
		return "admin/item-form";
	}
}
