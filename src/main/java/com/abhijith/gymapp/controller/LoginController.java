package com.abhijith.gymapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String index() {
		return "security/home";
	}

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {

		return "security/login";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "security/access-denied";

	}
	
}