package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.User.SiteUser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LogService {
	
	private String fileDir = "C:\\project0903\\";

	private final LogRepository logRepository;
	
	public List<Log> getList() {
		return this.logRepository.findAll();
	}

	public Long save(@Valid LogForm logForm, MultipartFile file, SiteUser user) throws IllegalStateException, IOException{
		
		String originalFileName = null;
		String storedFileName = null;
		
		if(file != null && !file.isEmpty()) {
			originalFileName = file.getOriginalFilename();
			storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
			
			file.transferTo(new File(fileDir + storedFileName));
			
		}
			Log logs = new Log(logForm.getActivity(), logForm.getThoughts(),
					originalFileName, storedFileName, user);
			
			return logRepository.save(logs).getId();
			
	}

	public Log getLog(Long id) throws Exception {
		Optional<Log> log = this.logRepository.findById(id);
		if(log.isPresent()) {
			return log.get();
		} else {
			throw new Exception("해당 로그를 찾을 수 없습니다.");
		}
	
	}

	public void modify(Log log, String activity, String thoughts) {
		log.setActivity(activity);
		log.setThoughts(thoughts);
		this.logRepository.save(log);
	}

	public void delete(Long id) {
		Optional<Log> log = logRepository.findById(id);
		Log log1 = log.get();
		logRepository.delete(log1);
	}

}
