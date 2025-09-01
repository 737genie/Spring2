package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LabService {

	private final LabRepository labRepository;
	
	public List<Recipe> getList() {
		return this.labRepository.findAll();
	}

	public void createRecipe(String name, String description) {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setDescription(description);
		this.labRepository.save(recipe);
	}

}
