package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClubRegistrationController {
	
	@GetMapping("/club/register")
	public String signup(Model model) {
		model.addAttribute("user", new ClubUserRegistrationDto());
		return "register";
	}
	
    @PostMapping("/club/register")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid ClubUserRegistrationDto registrationDto,
            BindingResult result,
            Model model) {
        
        // 비밀번호 확인
    	// rejectValue : 특정 필드와 관련된 오류를 등록하는 메서드
    	// rejectValue(해당 필드/속성 html할당값, 오류 식별코드, 메세지)
    	// -> 폼 데이터 특정 필드에 문제가 있을 경우 그에 관련된 오류 설정
        if (!registrationDto.isPasswordMatching()) {
            result.rejectValue("confirmPassword", "error.user", "비밀번호가 일치하지 않습니다.");
        }
        
        // 유효성 검사 실패
        if (result.hasErrors()) {
            return "register";
        }
        
        try {
            userService.registerNewUser(registrationDto);
            model.addAttribute("success", true); // view단에 성공상태를 우선 저장
            return "redirect:/club/login?success";
            
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
