package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.RecipeForm;
import com.example.demo.Entity.SiteUser;
import com.example.demo.Service.LabService;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LabController {
	
	private final LabService labService;
	private final UserService userService;
	
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
	public String createRecipe(RecipeForm recipeForm, Model model,
			Principal principal,
			SiteUser user) {
		
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		this.labService.createRecipe(
				recipeForm.getName(),
				recipeForm.getDescription(),
				siteUser
				);		
		return "redirect:/recipes";
	}
	
	@GetMapping("/recipes/{id}/edit")
	public String modify(@PathVariable("id") Long id,
			Model model) throws Exception {
		
		Recipe r = this.labService.getRecipe(id);
		model.addAttribute("recipe", r);
		return "edit-form";
	}
	
	@PostMapping("/recipes/{id}/edit")
	public String modify(@PathVariable("id") Long id,
			RecipeForm recipeForm) throws Exception {
		
		Recipe r = this.labService.getRecipe(id);
		this.labService.update(r, recipeForm);
		
		return "redirect:/recipes";
	}
	
	@PostMapping("/recipes/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		this.labService.delete(id);
		return "redirect:/recipes";
	}
}
