package me.nothing.login_.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import me.nothing.login_.model.Staff;
import me.nothing.login_.model._StaffDetails;
import me.nothing.login_.service.StaffService;

@Controller
public class LoginController {

	@RequestMapping("")
	public String index() {
		return "redirect:login";
	}

	@RequestMapping("/login")
	public String login(String username) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}

		_StaffDetails staffDetails = (_StaffDetails) authentication.getPrincipal();
		System.out.println("this is " + authentication);
		System.out.println("this is " + staffDetails.hasRole("admin"));

		if (staffDetails.hasRole("staff")) {
			return "redirect:/staff";
		} else if (staffDetails.hasRole("manager")) {
			return "redirect:/manager";
		} else if (staffDetails.hasRole("admin")) {
			return "redirect:/admin";
		} else {
			return "redirect:/staff";
		}
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
