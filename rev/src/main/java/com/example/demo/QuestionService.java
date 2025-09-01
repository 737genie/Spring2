package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.User.SiteUser;
import com.example.demo.exceptions.NoneDataException;
import com.example.demo.question.QuestionForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> q = this.questionRepository.findById(id);
		if(q.isPresent()) {
			return q.get();
		}else {
			throw new NoneDataException("해당 질문을 찾을 수 없습니다.");
		}
		
	}

	public void createQuestion(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
	}

	public void modify(Question q, String subject, String content) {
		q.setSubject(subject);
		q.setContent(content);
		q.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(q);
	}

	
}
