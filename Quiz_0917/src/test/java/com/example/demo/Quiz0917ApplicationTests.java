package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Domain.NuTaBook;
import com.example.demo.Repository.NuTaRepository;

@SpringBootTest
class Quiz0917ApplicationTests {

	@Autowired
	private NuTaRepository nutaRepository;
	@Test
	void contextLoads() {
		
		NuTaBook book = new NuTaBook(
				"코스모스", "칼 세이건"
				);
		
		this.nutaRepository.save(book);
		
	}

}
