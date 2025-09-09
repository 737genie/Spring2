package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {
	private final MissionRepository missionRepository;

	public void save(MissionForm missionForm) {
		Mission mission = new Mission();
		mission.setMissionName(missionForm.getMissionName());
		mission.setLocation(missionForm.getLocation());
		mission.setDescription(missionForm.getDescription());
		
		missionRepository.save(mission);
	}

	public List<Mission> getList() {
		return missionRepository.findAll();
	}
}
