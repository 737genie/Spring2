package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CommentDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexComment;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Repository.FlexCommentRepository;
import com.example.demo.Repository.FlexRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlexCommentService {
	
	private final FlexCommentRepository flexCommentRepository;
	private final FlexRepository flexRepository;
	
	public List<CommentDto> getCommentById(Flex flex) {
		List<FlexComment> comments = flexCommentRepository.findByFlex(flex);
		return comments.stream()
				.map(c -> new CommentDto(c.getId(), c.getContent(), c.getFlexUser().getUsername(), c.getCreatedAt()))
	            .toList();
	}
	
	public FlexComment createComment(Flex flex, CommentDto commentDto, FlexUser flexUser) {
		
		FlexComment flexComment = new FlexComment(
				commentDto.getContent(),
				flex,
				flexUser);
		this.flexCommentRepository.save(flexComment);
		
		return flexComment;
	}
	
	
	

}
