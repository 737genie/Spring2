package com.example.demo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryForm {
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String symptoms;
}
