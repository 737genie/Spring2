package com.example.demo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemoryForm {
	
	@NotEmpty(message="출처는 비워둘 수 없습니다.")
	@Size(max=200)
	private String source;

	@NotEmpty(message="맛은 필수 항목입니다.")
	private String taste;
	
	@NotNull
	private int rating;
}
