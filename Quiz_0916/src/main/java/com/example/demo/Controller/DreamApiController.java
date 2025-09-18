package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CodeCheckRequestDto;
import com.example.demo.DTO.CodeCheckResponseDto;
import com.example.demo.Service.DreamProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DreamApiController {
	
	private final DreamProjectService dreamProjectService;
	
	
	@GetMapping("/projects/check-code")
	public ResponseEntity<?> checkCode(CodeCheckRequestDto codeCheckRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(codeCheckRequestDto);
	}
	@PostMapping("/projects/check-code")
	public ResponseEntity<?> checkCode(CodeCheckRequestDto codeReqDto,
			CodeCheckResponseDto codeResDto) {
		
		boolean checkCode = this.dreamProjectService.checkActivationCodeExists(codeReqDto.getActivationCode());
		
		codeResDto.setExists(checkCode);
		
		return ResponseEntity.status(HttpStatus.OK).body(codeResDto);
	}
}
