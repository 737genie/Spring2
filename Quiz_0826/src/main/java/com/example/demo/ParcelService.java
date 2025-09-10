package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
	private final ParcelRepository parcelRepository;
	
	@Autowired
	public ParcelService(ParcelRepository parcelRepository) {
		this.parcelRepository=parcelRepository;
	}
	
	public Parcel save(ParcelCreateDto dto) {
		Parcel parcel = new Parcel(dto.getSender(), dto.getReceiver(), dto.getDestination(), dto.getContent(), Status.PENDING);
		return parcelRepository.save(parcel);
	}
	
	public List<Parcel> findAll() {
		return parcelRepository.findAll();
	}

	public Parcel getParcel(Long id) throws Exception {
		Optional<Parcel> p = this.parcelRepository.findById(id);
		if(p.isPresent() ) {
			return p.get();
		}else {
			throw new Exception("No DATA");
		}
	}

	public void update(Parcel p, Status status) {
		p.setStatus(status);
		this.parcelRepository.save(p);
	}
	
}
