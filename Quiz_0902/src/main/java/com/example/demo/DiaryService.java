package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
	
	private final DiaryRepository diaryRepository;
	
	public List<Diary> getList() {
		return this.diaryRepository.findAll();
	}

	public void save(DiaryCreateForm diaryCreateForm) {
		Diary diary = new Diary();
		diary.setTitle(diaryCreateForm.getTitle());
		diary.setContent(diaryCreateForm.getContent());
		diary.setCreatedAt(LocalDateTime.now());
		this.diaryRepository.save(diary);
	}

}
