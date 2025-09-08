package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;
	
	public List<Store> getList() {
		return this.storeRepository.findAll();
	}

	public Store detail(Long id) {
		Optional<Store> store = this.storeRepository.findById(id);
		return store.get();
	}

	public Long save(@Valid StoreForm storeForm) {
		
		Store store = new Store();
		store.setName(storeForm.getName());
		store.setLocation(storeForm.getLocation());
		
		return this.storeRepository.save(store).getId();
	}
	
}
