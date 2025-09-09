package com.example.demo;

import com.example.demo.User.Agent;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String missionName;
    private String location;
    @Lob
    private String description;
    
    @Enumerated(EnumType.STRING)
    private MissionStatus status;
    
    private String evidencePhotoName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Builder
    public Mission(String missionName, String location, String description, Agent agent) {
        this.missionName = missionName;
        this.location = location;
        this.description = description;
        this.agent = agent;
        this.status = MissionStatus.ASSIGNED;
    }
    
    public void complete(String evidencePhotoName) {
        if (this.status == MissionStatus.COMPLETED) {
            throw new IllegalStateException("이미 완수된 임무입니다.");
        }
        this.status = MissionStatus.COMPLETED;
        this.evidencePhotoName = evidencePhotoName;
    }
}