package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.User.DiaryKeeper;
import com.example.demo.User.KeeperRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
	
	private final DiaryRepository diaryRepository;
	private final KeeperRepository keeperRepository;
	
	public List<Diary> getList(String username) {
		return this.diaryRepository.findByAuthorUsername(username);
	}

	public void save(DiaryCreateForm diaryCreateForm, DiaryKeeper diaryKeeper) {
		Diary diary = new Diary();
		diary.setTitle(diaryCreateForm.getTitle());
		diary.setContent(diaryCreateForm.getContent());
		diary.setCreatedAt(LocalDateTime.now());
		diary.setAuthor(diaryKeeper);
		this.diaryRepository.save(diary);
	}

	public Diary getDiary(Long id) {
		Optional<Diary> d = this.diaryRepository.findById(id);
		return d.get();
	}
	
	@Transactional
	public void update(Diary d, DiaryCreateForm diaryCreateForm) {
		d.setContent(diaryCreateForm.getContent());
		d.setTitle(diaryCreateForm.getTitle());
	}

	public void delete(Long id) {
		this.diaryRepository.deleteById(id);
	}

}
