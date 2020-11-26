package com.ware.narang.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
public class MainController {

	@RequestMapping("/")
	public String main() {
		
		log.info("[ LOG ] MainController/main");
		
		return "common/home";
	}
	
	@RequestMapping("/security/admin")
	public String admin() {
		
		log.info("[ LOG ] MainController/admin");
		
		return "security/admin";
	}
	
	@RequestMapping("/login2")
	public String login2() {
		
		return "security/login2";
	}
}
