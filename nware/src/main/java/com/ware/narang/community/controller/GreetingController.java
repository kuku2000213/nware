package com.ware.narang.community.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.ware.narang.community.entity.Chat;
import com.ware.narang.community.entity.Greeting;
import com.ware.narang.community.entity.HelloMessage;

import lombok.extern.java.Log;

@Log
@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message)throws Exception{
		
		log.info("[ LOG ] GreetingController/greeting  HelloMessage : " + message);
		
		Greeting grt = new Greeting();
		
		grt.setContent("안녕하세요, " + HtmlUtils.htmlEscape(message.getName()) + "님 !");
		
//		Thread.sleep(1000); 
		return grt;
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Chat chat(Chat chat) throws Exception {
		
		log.info("[ LOG ] GreetingController/Chat  Chat : " + chat);
		
		chat.getName();
		chat.getMessage();
		
		return chat;
	}
}
