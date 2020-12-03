package com.ware.narang.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication )throws IOException,
	ServletException{
	
		log.info("[ LOG ] LoginSuccessHandler/onAuthenticationSuccess   " );
		log.info("[ LOG ] -----> request : " + request );
		log.info("[ LOG ] -----> response : " + response );
		log.info("[ LOG ] -----> authentication : " + authentication );
		HttpSession session = request.getSession();
		
		// view 에 session 주기
//		session.setAttribute("loginUser", authentication.getName());
		session.setAttribute("loginUser", authentication.getName());
//		session.setAttribute("loginUser", authentication);
		
		response.sendRedirect("/");
	}
}
