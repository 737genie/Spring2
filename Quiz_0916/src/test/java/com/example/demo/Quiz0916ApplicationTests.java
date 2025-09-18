package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.DreamFragment;
import com.example.demo.Entity.DreamProject;
import com.example.demo.Repository.DreamFragmentRepository;
import com.example.demo.Repository.DreamProjectRepository;

@SpringBootTest
class Quiz0916ApplicationTests {

	@Autowired
	private DreamProjectRepository dreamProjectRepository;
	@Autowired
	private DreamFragmentRepository dreamFragmentRepository;
	
	@Test
	void contextLoads() {
	
	
	DreamProject dp1 = new DreamProject();
	dp1.setActivationCode("abc");
	dp1.setProjectName("abc");
	this.dreamProjectRepository.save(dp1);
	
	DreamFragment df1 = new DreamFragment();
	df1.setContent("dreamfragment");
	this.dreamFragmentRepository.save(df1);
	}
	
}
