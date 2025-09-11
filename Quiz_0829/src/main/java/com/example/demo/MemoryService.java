package com.example.demo;

import java.util.List;
import java.util.Optional;

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

	public void update(Memory m, MemoryForm memoryForm) {
		m.setRating(5);
		m.setSource("test1");
		m.setTaste("test11"); // html form이 구현되지 않아서 오류 방지를 위해 임시 데이터값 설정
		this.memoryRepository.save(m);
	}

	public Memory getMemory(Long id) throws Exception {
		Optional<Memory> m = this.memoryRepository.findById(id);
		if(m.isPresent()) {
			return m.get();
		} else {
			throw new Exception("");
		}
	}

	public void delete(Long id) {
		this.memoryRepository.deleteById(id);
	}
	
	

}
