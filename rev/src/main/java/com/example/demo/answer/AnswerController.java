package com.example.demo.answer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Question;
import com.example.demo.QuestionService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	
	@PostMapping("/create")
	public void answerCreate(@PathVariable("id") Integer id,
			@RequestParam(value="content") String content) {
		Question q = this.questionService.getQuestion(id);
	}
	
}
