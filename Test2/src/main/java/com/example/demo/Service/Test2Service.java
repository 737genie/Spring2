package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.TestPost;
import com.example.demo.Entity.TestPostDto;
import com.example.demo.Repository.Test2Repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Test2Service {
	private final Test2Repository test2Repository;

	public Page<TestPost> findAll(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createAt"));
		Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));
		return this.test2Repository.findAll(pageable);
	}

	public void save(TestPostDto testPostDto) {
		TestPost testPost = new TestPost();
		System.out.println(testPostDto.getContent());
		
		testPost.setContent(testPostDto.getContent());
		testPost.setTitle(testPostDto.getTitle());
		testPost.setCreateAt(LocalDateTime.now());
		
		this.test2Repository.save(testPost);
	}

	public TestPost findById(Long id) {
		return this.test2Repository.findById(id).get();
	}

	@Transactional
	public void update(Long id, TestPostDto testPostDto) {
		TestPost testPost = this.test2Repository.findById(id).get();
		testPost.setContent(testPostDto.getContent());
		testPost.setTitle(testPostDto.getTitle());
	}

	public void delete(Long id) {
		this.test2Repository.deleteById(id);
	}
	
	
	
}
