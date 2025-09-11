package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.RecipeForm;
import com.example.demo.Entity.SiteUser;
import com.example.demo.Repository.LabRepository;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LabService {

    private final UserRepository userRepository;

	private final LabRepository labRepository;

	
	public List<Recipe> getList() {
	    return labRepository.findAllWithUsers();
	}

	public void createRecipe(String name, String description, SiteUser user) {
		Recipe recipe = new Recipe(name, description, user);
		this.labRepository.save(recipe);
	}

	public Recipe getRecipe(Long id) throws Exception {
		Optional<Recipe> r = this.labRepository.findById(id);
		if(r.isPresent()) {
			return r.get();
		} else {
			throw new Exception("");
		}
	}
	
	@Transactional
	public void update(Recipe r, RecipeForm recipeForm) {
		r.setDescription(recipeForm.getDescription());
		r.setName(recipeForm.getName());
//		this.labRepository.save(r);   @Transactional 쓰면 save 필요없음
	}

	public void delete(Long id) {
		this.labRepository.deleteById(id);
	}

}
