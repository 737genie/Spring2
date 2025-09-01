package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.User.SiteUser;
import com.example.demo.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	
	@Id // 기본 키 (not null + unique)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=200)
	private String subject;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private SiteUser author;
	
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE) // 관계설정은 양쪽 다 해줘야함
	private List<Answer> answerList;

	public void update(String subject, String content) {
		this.subject=subject;
		this.content=content;
		this.createDate=LocalDateTime.now();
	}
	
}
