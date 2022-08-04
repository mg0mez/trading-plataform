package com.tradingplataform.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	
	
	@GetMapping("/login")
	public String login() {
		
		return "/auth/login";
	}
	
	@GetMapping("/register")
	public String register() {
			
	
		return "/auth/register";
	}
	
}
