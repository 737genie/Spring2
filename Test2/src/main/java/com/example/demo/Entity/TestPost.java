package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=200)
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String content;
	private LocalDateTime createAt;
	
	@Builder
	public TestPost(String title, String content) {
		this.title=title;
		this.content=content;
		this.createAt=LocalDateTime.now();
	}
	
}
