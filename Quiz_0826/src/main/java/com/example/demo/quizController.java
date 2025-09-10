package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class quizController {
	
	@Autowired
	public ParcelService parcelService;
	
	// 새 소포 접수
	@GetMapping("/parcels/new")
	public String createParcel(ParcelCreateDto dto, Model model) {
		model.addAttribute("parcel", dto);
		return "new-parcel-form";
	}
	
	@PostMapping("/parcels/new")
	public String createParcel(
			ParcelCreateDto dto) {
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
	@GetMapping("/parcels/{id}/edit")
	public String updateStatus(Model model, 
			ParcelCreateDto dto,
			@PathVariable("id") Long id) throws Exception {
		
		Parcel p = this.parcelService.getParcel(id);
		model.addAttribute("parcel", p);
		model.addAttribute("statuses", Status.values());
		return "edit-form";
	}
	
	@PostMapping("/parcels/{id}/edit")
	public String updateStatus(Model model,
			ParcelCreateDto dto,
			@PathVariable("id") Long id,
			BindingResult bindingResult) throws Exception{
		
		Parcel p = this.parcelService.getParcel(id);
		
		this.parcelService.update(p, dto.getStatus());
		
		return "redirect:/parcels";
	}

}
