package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
	
	private String fileDir = "C:\\project0904\\";
	
	private final DiaryRepository diaryRepository;
	
	public List<HangoverDiary> getList() {
		return this.diaryRepository.findAll();
	}

	public Long save(@Valid DiaryForm diaryForm, MultipartFile file) throws IllegalStateException, IOException {
		HangoverDiary diary = new HangoverDiary();
		
		String originalFileName=null;
		String storedFileName=null;
		
		if(file!=null && !file.isEmpty()) {
			originalFileName=file.getOriginalFilename();
			storedFileName=UUID.randomUUID().toString()+"_"+originalFileName;
			
			file.transferTo(new File(fileDir + storedFileName));
		}
		
		diary.setTitle(diaryForm.getTitle());
		diary.setSymptoms(diaryForm.getSymptoms());
		diary.setSufferedDate(LocalDate.now());
		
		diary.setImageFileName(storedFileName);
		
		return this.diaryRepository.save(diary).getId();
		
	}

	public HangoverDiary getDiary(Long id) throws Exception {
		Optional<HangoverDiary> diary = this.diaryRepository.findById(id);
		if(diary.isPresent()) {
			return diary.get();
		} else {
			throw new Exception("해당 게시글을 찾을 수 없습니다.");
		}
	}

	public void edit(HangoverDiary diary, String title, String symptoms) {
		diary.setTitle(title);
		diary.setSymptoms(symptoms);
		this.diaryRepository.save(diary);
	}

	public void delete(Long id) {
		Optional<HangoverDiary> diary = diaryRepository.findById(id);
		HangoverDiary diary1 = diary.get();
		diaryRepository.delete(diary1);
	}
	
	

}
