package com.example.demo.Entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
}
