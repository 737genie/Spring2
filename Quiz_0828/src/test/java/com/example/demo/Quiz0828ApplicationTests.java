package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Quiz0828ApplicationTests {

	@Autowired
	private AnomalyRepository anomalyRepository;
	
	@Test
	void contextLoads() {
		
//		Anomaly a = new Anomaly();
//		
//		a.setLocation("인천");
//		a.setName("바보");
//		a.setRiskLevel("높음");
//		
//		this.anomalyRepository.save(a);
		
	}

}
