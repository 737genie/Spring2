package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Setter
@Entity
public class DreamFragment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dream_project_id") // 외래키
    private DreamProject dreamProject;
    
    // Getters and Setters...
    public String getContent() {
        return content;
    }
    
    public DreamProject getDreamProject() {
    	return dreamProject;
    }
}
