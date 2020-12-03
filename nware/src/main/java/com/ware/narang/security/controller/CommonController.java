package com.ware.narang.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ware.narang.security.entity.Authorities;
import com.ware.narang.security.entity.Users;
import com.ware.narang.security.service.UsersService;

import lombok.extern.java.Log;

@Controller
@Log
public class CommonController {

	@Autowired
	private UsersService uService;

	
	@RequestMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		
		log.info("[ LOG ] CommonController/accessDenied  access Denied : " + auth);
		
		String fMsg = "accessDenied : " + auth;
		
		model.addAttribute("msg", fMsg);
		
		return "security/accessError";
	}
	
	@RequestMapping("/login")
	public String login(String error, String logout, Model model) {
		
		log.info("[ LOG ] CommonController/login  error : " + error);
		
		log.info("[ LOG ] CommonController/accessDenied  logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error");
		}
		
		if(logout != null) {

			model.addAttribute("logout", "Logout");
			
		}
		
		return "security/login2"; 
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/logout")
	public String logout() {
		
		log.info("[ LOG ] CommonController/logout  " );
		
		return "security/logout";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp() {
		
		return "security/signup";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp( Model model, Users users)throws Exception{
		
		log.info("[ LOG ] CommonController/signUp  POST  users : " + users);
		
		uService.uSignUp(users);
		
		long id = users.getUser_id();
		String name = users.getUsername();
		
		Authorities auth = new Authorities();
		auth.setUser_id(id);
		auth.setUsername(name);
		auth.setAuthority("ROLE_MEMBER");
//		auth.setAuthority("ROLE_ADMIN");
		uService.aSignup(auth);
		
		return "common/home";
	}
	
	@RequestMapping("foundUser")
	public String list(Model model, Users users)throws Exception{
		
		log.info("[ LOG ] CommonController/foundUser  " );
		
		
		
		model.addAttribute("userList", uService.uList());
		model.addAttribute("authList", uService.aList());
		
		return "security/list";
		
	}
	
}
