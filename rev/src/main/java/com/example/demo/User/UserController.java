package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "sign_up";
	}
	
//	@PostMapping("/signup")
//	public void signup(@RequestParam(value="username") String username) {
//		this.userService.create(username);
//	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "sign_up";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"비밀번호가 일치하지 않습니다."); // rejectValue - bindingResult의 커스텀예외
			return "sign_up";
		}
		userService.create(
				userCreateForm.getUsername(),
				userCreateForm.getPassword1(),
				userCreateForm.getEmail()
				);
		return "redirect:/";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "log_in";
	}
	
}
