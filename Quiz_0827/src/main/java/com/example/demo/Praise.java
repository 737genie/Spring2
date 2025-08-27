package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Praise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String praiser; // 칭찬해 준 사람

    @Column(nullable = false)
    private String content; // 칭찬 내용

    private LocalDate praisedAt; // 칭찬받은 날짜
    
    
    public Praise() {}
    
    public Praise(String praiser, String content) {
    	this.praiser=praiser;
    	this.content=content;
    	this.praisedAt=LocalDate.now();
    }
    
    
    

	public Long getId() {
		return id;
	}

	public String getPraiser() {
		return praiser;
	}

	public String getContent() {
		return content;
	}

	public LocalDate getPraisedAt() {
		return praisedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPraiser(String praiser) {
		this.praiser = praiser;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPraisedAt(LocalDate praisedAt) {
		this.praisedAt = praisedAt;
	}
    
    
}
