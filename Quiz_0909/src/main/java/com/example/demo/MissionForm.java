package com.example.demo;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionForm {
	private String missionName;
    private String location;
    private String description;
    
}
