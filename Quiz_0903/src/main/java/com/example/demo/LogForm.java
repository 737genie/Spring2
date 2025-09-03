package com.example.demo;

import com.example.demo.User.SiteUser;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogForm {
	@NotEmpty
	private String activity;
	
	@NotEmpty
	private String thoughts;
	
	private SiteUser author;
}
