package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RevApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
		
		Optional<Question> q = this.questionRepository.findById(1);
		System.out.println(q.get().getSubject());
		System.out.println(q.get().getContent());
		
//		Question q = new Question();
//		q.setContent("주말은 언제 오나");
//		q.setSubject("졸립다");
//		q.setCreateDate(LocalDateTime.now());
//		
//		this.questionRepository.save(q);
		
	}

}
