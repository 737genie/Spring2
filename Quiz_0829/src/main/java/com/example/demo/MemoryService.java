package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoryService {

	private final MemoryRepository memoryRepository;
	
	public List<Memory> getList() {
		return this.memoryRepository.findAll();
	}
	
	public void createMemory(String source, String taste, int rating) {
		Memory memory = new Memory();
		memory.setSource(source);
		memory.setTaste(taste);
		memory.setRating(rating);
		this.memoryRepository.save(memory);
	}

	public void update(Long id, MemoryForm memoryForm) {
		
	}

}
