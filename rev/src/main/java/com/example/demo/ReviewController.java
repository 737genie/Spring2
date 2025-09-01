package com.example.demo;


import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.User.SiteUser;
import com.example.demo.User.UserService;
import com.example.demo.answer.AnswerForm;
import com.example.demo.question.QuestionForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 이거 안 쓸거면 @Autowired
@Controller
public class ReviewController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	private final UserService userService;
	
	
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
			BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "review/create";
		}
		
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		this.questionService.createQuestion(
				questionForm.getSubject(), 
				questionForm.getContent(),
				siteUser);
		
		return "redirect:/list"; // redirect는 서버 주소 따라감
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model,
			AnswerForm answerForm) {
		Question q = this.questionService.getQuestion(id);
		model.addAttribute("question", q);
		return "review/detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm,
			@PathVariable("id") Integer id, Principal principal) {
		
		Question q = this.questionService.getQuestion(id);
		
		if(!q.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		questionForm.setSubject(q.getSubject());
		questionForm.setContent(q.getContent());
		
		return "review/create";
	}	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm,
			@PathVariable("id") Integer id, Principal principal,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "create";
		}
		
		Question q = this.questionService.getQuestion(id);
		
		if(!q.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		this.questionService.modify(q,
				questionForm.getSubject(),
				questionForm.getContent()
				);
		
		return String.format("redirect:/detail/%s", id);
	}	
	
}
