package com.example.demo;

public class AnomalyCreateDto {
	
	private String name; // 변칙 현상 이름

    private String location; // 발생 위치

    private String riskLevel; // 위험 등급 (예: 낮음, 중간, 높음, 재앙)

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
    
    
	
}
