package com.example.demo.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CodeCheckRequestDto {
	private String activationCode;

	public CodeCheckRequestDto(String activationCode) {
		this.activationCode = activationCode;
	}
}
