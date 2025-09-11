package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketService {
	private final MarketRepository marketRepository;

	public List<Market> getList(String keyword) {
		if(keyword == null || keyword.isBlank()) {
			return this.marketRepository.findAll();
		} else {
			return this.marketRepository.findKeyword(keyword);
		}
	}

	public void save(MarketForm marketForm) {
		Market market = new Market();
		market.setTitle(marketForm.getTitle());
		market.setDescription(marketForm.getDescription());
		market.setPrice(marketForm.getPrice());
		
		this.marketRepository.save(market);
	}

	public Market getItem(Long id) {
		Optional<Market> m = this.marketRepository.findById(id);
		return m.get();
	}

	public void modify(Market m, String title, String description, int price) {
		m.setTitle(title);
		m.setDescription(description);
		m.setPrice(price);
		this.marketRepository.save(m);
	}

	public void delete(Long id) {
		this.marketRepository.deleteById(id);
	}
	
	
}
