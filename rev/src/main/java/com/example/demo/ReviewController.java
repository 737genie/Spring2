package com.example.demo;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.question.QuestionForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 이거 안 쓸거면 @Autowired
@Controller
public class ReviewController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	
	@GetMapping("/list")
	public String nureong(Model model) {
		
		//List<Question> qlist = this.questionRepository.findAll();
		List<Question> qlist = this.questionService.getList();
		model.addAttribute("qlist", qlist); // model -> 템플릿에 객체의 정보를 보여주기 위해 사용함
		
		return "review/list";
	}

	@GetMapping("/create")
	public String create(QuestionForm questionForm) {
		return "review/create";
	}
	
	@PostMapping("/create")
	public String create(
			@Valid QuestionForm questionForm, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "review/create";
		}
		
		this.questionService.createQuestion(
				questionForm.getSubject(), 
				questionForm.getContent());
		
		return "redirect:/list"; // redirect는 서버 주소 따라감
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Question q = this.questionService.getQuestion(id);
		model.addAttribute("question", q);
		return "review/detail";
	}
	
	
	
	
	
}
