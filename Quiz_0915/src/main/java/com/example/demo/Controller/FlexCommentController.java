package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CommentDto;
import com.example.demo.Entity.Flex;
import com.example.demo.Entity.FlexComment;
import com.example.demo.Entity.FlexUser;
import com.example.demo.Service.FlexCommentService;
import com.example.demo.Service.FlexService;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class FlexCommentController {

	private final FlexCommentService flexCommentService;
	private final FlexService flexService;
	private final UserService userService;
	
	
	
	@GetMapping("/{id}/comments")
	public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
		Flex flex = flexService.detail(id);
		List<CommentDto> comment = this.flexCommentService.getCommentById(flex);
		return ResponseEntity.status(HttpStatus.OK).body(comment);
	}
	
	@PostMapping("/{id}/comments")
	public ResponseEntity<?> create(@PathVariable("id") Long id,
			Principal principal,
			@RequestBody CommentDto commentDto) {
		
		Flex flex = this.flexService.detail(id);
		FlexUser flexUser = this.userService.getUser(principal.getName());
		FlexComment comment = flexCommentService.createComment(flex, commentDto, flexUser);
		
		CommentDto responseDto = new CommentDto(comment.getId(), comment.getContent(), comment.getFlexUser().getUsername(), comment.getCreatedAt());
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
