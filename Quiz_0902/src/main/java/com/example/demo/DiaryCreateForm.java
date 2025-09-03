package com.example.demo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryCreateForm {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;

}
