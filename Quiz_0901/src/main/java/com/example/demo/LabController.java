package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LabController {
	
	private final LabService labService;
	
	@GetMapping("/recipes")
	public String list(Model model) {
		List<Recipe> recipes = this.labService.getList();
		model.addAttribute("recipes", recipes);
		return "list";
	}
	
	@GetMapping("/recipes/new")
	public String createRecipe(RecipeForm recipeForm) {
		return "new-form";
	}
	
	@PostMapping("/recipes/new")
	public String createRecipe(RecipeForm recipeForm, Model model) {
		
		this.labService.createRecipe(
				recipeForm.getName(),
				recipeForm.getDescription()
				);		
		return "redirect:/recipes";
	}
	
	@PostMapping("/")

}
