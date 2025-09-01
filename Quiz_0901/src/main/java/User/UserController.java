package User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/signup")
	public String signup() {
		return "signup-form";
	}
	
	@PostMapping("/signup")
	public String signup(SiteUser siteUser) {
		
		
		return "redirect:/recipes";
	}
}
