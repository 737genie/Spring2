package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 이거 안 쓸거면 @Autowired
@Controller
public class ReviewController {
	
	private final QuestionRepository questionRepository;
	
	@GetMapping("/list")
	public String nureong(Model model) {
		
		List<Question> qlist = this.questionRepository.findAll();
		model.addAttribute("qlist", qlist); // model -> 템플릿에 객체의 정보를 보여주기 위해 사용함
		
		return "review/list";
	}
}
