package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class quizController {
	
	@Autowired
	public ParcelService parcelService;
	
	// 새 소포 접수
	@GetMapping("/create")
	public String createParcel(ParcelCreateDto dto) {
		return "new-parcel-form";
	}
	
	@PostMapping("/create")
	public String createParcel(ParcelCreateDto dto, ) {
		parcelService.save(dto);
		return "redirect:/parcels";
	}
	
	// 리스트 보여주기
	@GetMapping("/parcels")
	public String showParcel(Model model) {
			List<Parcel> parcels = parcelService.findAll();
			model.addAttribute("parcels", parcels);
			return "list";
	}
	
	// 소포 상태 변경
	@PostMapping(value="/parcels/{id}/edit")
	public String updateStatus(Model model, @PathVariable("id") Long id) {
		
	}

}
