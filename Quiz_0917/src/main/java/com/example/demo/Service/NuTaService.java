package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.BookSpecification;
import com.example.demo.Domain.NuTaBook;
import com.example.demo.Repository.NuTaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NuTaService {
	private final NuTaRepository nutaRepository;

	public Page<NuTaBook> findAll(int page, String keyword) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createdAt"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		Specification<NuTaBook> spec = BookSpecification.search(keyword);
		
		return nutaRepository.findAll(spec, pageable);
	}
	
	
}
