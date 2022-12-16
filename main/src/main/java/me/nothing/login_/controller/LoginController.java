package me.nothing.login_.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import me.nothing.login_.model.Staff;

@Controller
public class LoginController {

	@RequestMapping("")
	public String index(){
		return "redirect:login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value = "invalid-session", defaultValue = "false")boolean invalidSession, 
	String username,Model model){
		// if(invalidSession){
		// 	model.addAttribute("invalidSession", "session out of time");
			
		
		// }

		System.out.println(username);
		model.addAttribute("username", username);
		//model.addAttribute("username", username);
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
