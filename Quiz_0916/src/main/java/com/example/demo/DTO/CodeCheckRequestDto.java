package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeCheckRequestDto {
	private String activationCode;

	public CodeCheckRequestDto(String activationCode) {
		this.activationCode = activationCode;
	}
}
