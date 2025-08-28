package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class AnomalyService {
	
	private AnomalyRepository anomalyRepository;
	
	public Long save(AnomalyCreateDto anomaly) {
		Anomaly anomalyNew = new Anomaly(anomaly.getName(), anomaly.getLocation(), anomaly.getRiskLevel());
		return anomalyRepository.save(anomalyNew).getId();
		
	}
}
