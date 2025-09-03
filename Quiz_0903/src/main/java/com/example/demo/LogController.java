package com.example.demo;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.User.SiteUser;
import com.example.demo.User.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LogController {
	
	private final UserService userService;
	
	private final LogService logService;
	
	@GetMapping("/list")
	public String showLog(Model model) {
		List<Log> logs = this.logService.getList();
		model.addAttribute("logs", logs);
		return "list";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/list";
	}
	
	@GetMapping("/new")
	public String createLog(LogForm logForm) {
		return "new-form";
	}
	
	@PostMapping("/new")
	public String createLog(
			@Valid LogForm logForm,
			BindingResult bindingResult,
			Principal principal,
			@RequestParam("file") MultipartFile file) {
		
		if(bindingResult.hasErrors()) {
			return "create";
		}
		
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		try {
			logService.save(logForm, file, siteUser);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{id}/edit")
	public String modifyLog(LogForm logForm,
			@PathVariable("id") Long id, Model model, Principal principal) throws Exception {
		
		Log log = this.logService.getLog(id);
		
		model.addAttribute("log", log);
		
		if(!log.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
			return "redirect:/";
		}
		
		logForm.setActivity(log.getActivity());
		logForm.setThoughts(log.getThoughts());
		
		return "edit-form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/{id}/edit")
	public String modifyLog(@Valid LogForm logForm,
			@PathVariable("id") Long id, 
			Model model,
			Principal principal,
			BindingResult bindingResult) throws Exception {
		
		try {
			
			if(bindingResult.hasErrors()) {
				return "create";
			}
			
			Log log = this.logService.getLog(id);
			model.addAttribute("log", log);
			
			if(!log.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
			}
			
			this.logService.modify(log,
					logForm.getActivity(),
					logForm.getThoughts()
					);
			
			return "redirect:/";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
	}
	
	@PostMapping("/{id}/delete")
	public String deleteLog(@PathVariable("id") Long id) {
		
		logService.delete(id);
		return "redirect:/";
	}
	
}
