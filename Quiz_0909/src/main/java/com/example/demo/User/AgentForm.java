package com.example.demo.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentForm {
	@NotEmpty
    private String username;
	@NotEmpty
    private String password;
    private AgentRole role;
}
