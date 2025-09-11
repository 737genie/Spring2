package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PraiseController {
	@Autowired
	private PraiseService praiseService;
	
	@GetMapping("/praises")
	public String showPraises(Model model) {
		List<Praise> praises = praiseService.findAll();
		model.addAttribute("praises", praises);
		return "praiseList";
	}
	
	@GetMapping("/create")
	public String createPraise(Model model) {
		model.addAttribute("dto", new PraiseDto());
		return "new-praise-form";
	}
	
	@PostMapping("/create")
	public String createPraise(PraiseDto dto) {
		praiseService.save(dto);
		return "redirect:/praises";
	}
	
	@GetMapping("/praises/{id}/edit")
	public String modify(@PathVariable("id") Long id,
			Model model, PraiseDto dto) throws Exception {
		
		Praise p = this.praiseService.getPraise(id);
		model.addAttribute("praise", p);
		
		return "edit-form";
	}
	
	@PostMapping("/praises/{id}/edit")
	public String modify(@PathVariable("id") Long id,
			PraiseDto dto) throws Exception {
		
		Praise p = this.praiseService.getPraise(id);
		this.praiseService.update(p, dto);
		
		return "redirect:/praises";
	}
	
	@PostMapping("/praises/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		
		this.praiseService.delete(id);
		
		return "redirect:/praises";
	}
	
}
