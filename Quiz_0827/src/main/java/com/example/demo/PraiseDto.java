package com.example.demo;
import java.time.LocalDate;



public class PraiseDto {

	private String praiser;
	private String content;
	private LocalDate praisedAt;

	public String getPraiser() {
		return praiser;
	}

	public String getContent() {
		return content;
	}

	public void setPraiser(String praiser) {
		this.praiser = praiser;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Praise toEntity() {
		return new Praise(praiser, content);
	}

	public LocalDate getPraisedAt() {
		return praisedAt;
	}

	public void setPraisedAt(LocalDate praisedAt) {
		this.praisedAt = praisedAt;
	}

 
}
