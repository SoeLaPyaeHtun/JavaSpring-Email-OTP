package me.nothing.login_.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/home")
	public String listUsers(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/admin1")
	public String adminOne(Model model) {
		return "admin1";
	}


	@GetMapping("/admin2")
	public String adminTwo(Model model) {
		return "admin2";
	}

	@GetMapping("/manager1")
	public String managerOne(Model model) {
		return "manager1";
	}

	@GetMapping("/manager2")
	public String managerTwo(Model model) {
		return "manager2";
	}

	@GetMapping("/staff1")
	public String staffOne(Model model) {
		return "staff1";
	}

	@GetMapping("/staff2")
	public String staffTwo(Model model) {
		return "staff2";
	}

}
