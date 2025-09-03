package com.example.demo.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@NotEmpty(message="이 항목은 비워둘 수 없습니다.")
	@Size(min=6, max=20)
    private String username;
	
	@NotEmpty(message="이 항목은 비워둘 수 없습니다.")
    private String password;
}
