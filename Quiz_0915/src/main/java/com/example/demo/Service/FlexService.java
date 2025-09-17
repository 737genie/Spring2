package com.example.demo.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.PostDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Repository.FlexRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlexService {
	private final FlexRepository flexRepository;
	private String fileDir = "C:\\uploads\\";

	@Transactional
	public void create(PostDto postDto, MultipartFile file, FlexUser flexUser) throws IllegalStateException, IOException {
		
		String originalFileName = null;
		String storedFileName = null;

		if(file != null && !file.isEmpty()) {
			// 원 사용자가 저장한 파일명
			originalFileName = file.getOriginalFilename();
			// 고유한 파일명 생성
			storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
			
			file.transferTo(new File(fileDir + storedFileName));
			
		}
		
		Flex flex = Flex.builder()
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.flexUser(flexUser)
				.imageFileName(storedFileName)
				.build();
		
		this.flexRepository.save(flex);
	}

	public Page<Flex> findAll(String keyword, int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createdAt"));
		Pageable pageable = PageRequest.of(page, 3, Sort.by(sorts));
		
		Specification<Flex> spec = FlexSpecification.search(keyword);

		return flexRepository.findAll(spec, pageable);
	}

	public Flex detail(Long id) {
		return this.flexRepository.findById(id).get();
	}

	@Transactional
	public void edit(PostDto postDto, Flex flex) {
		flex.update(postDto.getTitle(), postDto.getContent());
	}

	public void delete(Long id) {
		this.flexRepository.deleteById(id);
	}

	
	
}
