package com.example.demo;

import com.example.demo.User.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Log {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(nullable = false)
	 private String activity;
	 
	 
	 @ManyToOne
	 private SiteUser author;
		
	    //이거 없이도 첨부파일 업로드 다운로드 가능하니까 얘는 참고용이에요
	    //굳이 안쓰셔도됩니다(도전과제에용)
	 @Lob // 이미지 같은 미디어 파일들 받을라고 쓰는 어노테이션 
	 private String thoughts;

	 private String originalFileName; // 사용자가 업로드한 원본 파일 이름
	 private String storedFileName;   // 서버에 저장될 때 사용될 고유한 파일 이름

	 
	 public Log() {}
	 public Log(String activity, String thoughts, String originalFileName, String storedFileName, SiteUser user) {
		 this.activity=activity;
		 this.thoughts=thoughts;
		 this.originalFileName=originalFileName;
		 this.storedFileName=storedFileName;
		 this.author=user;
		 
	 }
}
