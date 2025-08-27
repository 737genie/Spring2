package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseService {
	
	@Autowired
	private PraiseRepository praiseRepository;
	
	
	public List<Praise> findAll() {
		return praiseRepository.findAll();
	}
	
	public Long save(PraiseDto dto) {
		Praise praise = new Praise(dto.getPraiser(), dto.getContent());
		return praiseRepository.save(praise).getId();
	}
}
