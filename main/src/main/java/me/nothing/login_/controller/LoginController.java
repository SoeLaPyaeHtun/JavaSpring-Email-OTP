package me.nothing.login_.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("/home")
	public String listUsers(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "invalid-session", defaultValue = "false")boolean invalidSession, Model model){
		if(invalidSession){
			model.addAttribute("invalidSession", "session out of time");
		}
		return "login";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin/home";
	}

	@GetMapping("/staff")
	public String staff() {
		return "staff/home";
	}

	@GetMapping("/manager")
	public String manager() {
		return "manager/home";
	}
}
