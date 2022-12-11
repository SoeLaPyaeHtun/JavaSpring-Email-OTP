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
}
