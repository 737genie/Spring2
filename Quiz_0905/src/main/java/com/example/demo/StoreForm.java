package com.example.demo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreForm {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String location;
	
}
