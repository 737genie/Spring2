package com.example.demo.Entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeForm {
	@NotEmpty(message="이름은 비워둘 수 없습니다.")
	private String name;
	private String description;
}
