package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
	private final ParcelRepository parcelRepository;
	
	@Autowired
	public ParcelService(ParcelRepository parcelRepository) {
		this.parcelRepository=parcelRepository;
	}
	
	public Long save(ParcelCreateDto dto) {
		Parcel parcel = new Parcel(dto.getTitle(), dto.getContent());
		return parcelRepository.save(parcel).getId();
	}
	
	public List<Parcel> findAll() {
		return parcelRepository.findAll();
	}
	
	public 
}
