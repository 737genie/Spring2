package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnomalyController {
	
	@Autowired
	private AnomalyRepository anomalyRepository;
	
	@Autowired
	private AnomalyService anomalyService;
	
	@GetMapping("/anomalies")
	public String anomalyList(Model model) {
		List<Anomaly> anomaly = this.anomalyRepository.findAll();
		model.addAttribute("anomalies", anomaly);
		return "list";
	}
	
	@GetMapping("/anomalies/new")
	public String anomalyCreate(Model model) {
		model.addAttribute("anomaly", new AnomalyCreateDto());
		return "new-form";
	}
	
	@PostMapping("/anomalies/new")
	public String anomalyCreate(AnomalyCreateDto anomaly) {
		anomalyService.save(anomaly);
		return "redirect:/anomalies";
	}
	
	
	
}
