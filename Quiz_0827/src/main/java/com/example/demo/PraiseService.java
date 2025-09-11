package com.example.demo;

import java.util.List;
import java.util.Optional;

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

	public Praise getPraise(Long id) throws Exception {
		Optional<Praise> p = this.praiseRepository.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			throw new Exception("");
		}
	}

	public void update(Praise p, PraiseDto dto) {
		p.setContent(dto.getContent());
		p.setPraiser(dto.getPraiser());
		p.setPraisedAt(dto.getPraisedAt());
		this.praiseRepository.save(p);
	}

	public void delete(Long id) {
		this.praiseRepository.deleteById(id);
	}
}
