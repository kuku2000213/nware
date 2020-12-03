package com.ware.narang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/community")
public class CommunityController {

	@RequestMapping("/main")
	public String message() {
		
		
		return "community/main";
	}
	
	@RequestMapping("/nMess")
	public String nMess() {
		
		return "community/nMess";
	}
}
