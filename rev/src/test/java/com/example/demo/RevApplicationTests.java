package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RevApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
		
		Question q = new Question();
		q.setContent("오웅");
		q.setSubject("오예스");
		q.setCreateDate(LocalDateTime.now());
		
		this.questionRepository.save(q);
		
	}

}
