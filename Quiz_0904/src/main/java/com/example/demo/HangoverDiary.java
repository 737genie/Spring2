package com.example.demo;

import java.time.LocalDate;

import com.example.demo.User.Sufferer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HangoverDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String symptoms;

    private String imageFileName;

    private LocalDate sufferedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sufferer_id")
    private Sufferer sufferer;

    @PrePersist
    public void prePersist() {
        this.sufferedDate = LocalDate.now();
    }
    
    @Builder
    public HangoverDiary(String title, String symptoms, String imageFileName, Sufferer sufferer) {
        this.title = title;
        this.symptoms = symptoms;
        this.imageFileName = imageFileName;
        this.sufferer = sufferer;
    }

    public void update(String title, String symptoms) {
        this.title = title;
        this.symptoms = symptoms;
    }
}