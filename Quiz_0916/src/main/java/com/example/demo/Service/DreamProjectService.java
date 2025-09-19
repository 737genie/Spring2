package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CodeCheckResponseDto;
import com.example.demo.Entity.DreamFragment;
import com.example.demo.Repository.DreamFragmentRepository;
import com.example.demo.Repository.DreamProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DreamProjectService {
	private final DreamFragmentRepository dreamFragmentRepository;
	private final DreamProjectRepository dreamProjectRepository;

	public List<DreamFragment> findNullFrag() {
		 List<DreamFragment> frag = this.dreamFragmentRepository.findByDreamProjectIsNull();
		return frag;
	}


	public Boolean checkActivationCodeExists(String activationCode) {
		if(this.dreamProjectRepository.existsByActivationCode(activationCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
