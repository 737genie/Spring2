package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MarketController {
	private final MarketService marketService;
	
	@GetMapping("/")
	public String root(Model model) {
		List<Market> list = this.marketService.getList();
		model.addAttribute("items", list);
		return "items/list";
	}
	
	@GetMapping("/items/new")
	public String create(MarketForm marketForm) {
		return "items/new-form";
	}
	
	@PostMapping("/items/new")
	public String create(MarketForm marketForm,
			BindingResult bindingResult) {
		
		this.marketService.save(marketForm);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/items/{id}/edit")
	public String modify(@PathVariable("id") Long id, 
			MarketForm marketForm,
			Model model) {
		
		Market m = this.marketService.getItem(id);
		
		model.addAttribute("item", m);
		
		marketForm.setTitle(m.getTitle());
		marketForm.setDescription(m.getDescription());
		marketForm.setPrice(m.getPrice());
		
		return "items/edit-form";
	}
	
	@PostMapping("/items/{id}/edit")
	public String modity(@PathVariable("id") Long id,
			MarketForm marketForm,
			BindingResult bindingResult) {
		
		Market m = this.marketService.getItem(id);
		this.marketService.modify(m, marketForm.getTitle(),
				marketForm.getDescription(),
				marketForm.getPrice());
		
		return "redirect:/";
	}
	
	@PostMapping("/items/{id}/delete")
	public String delete(@PathVariable("id") Long id,
			MarketForm marketForm, Model model) {
		
//		Market m = this.marketService.getItem(id);
//		model.addAttribute("item", m);
		
		this.marketService.delete(id);
		
		return "redirect:/";
	}
}
