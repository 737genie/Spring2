package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class StoreController {
	
	private final StoreService storeService;
	
	@GetMapping("/stores")
	public String showStore(Model model) {
		List<Store> stores = this.storeService.getList();
		model.addAttribute("stores", stores);
		return "store/list";
	}
	
	@GetMapping("/stores/{id}")
	public String showDetail(@PathVariable("id") Long id,
			Model model) {
		Store detailReturn = storeService.detail(id);
		model.addAttribute("store", detailReturn);
		return "store/detail";
	}
	
}
