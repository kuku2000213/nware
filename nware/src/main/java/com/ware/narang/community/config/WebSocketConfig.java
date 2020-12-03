package com.ware.narang.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	
		log.info("[ LOG ] WebSocketConfig/configureMessageBroker  registry : " + registry);
		
		registry.enableSimpleBroker("/nmess");
		registry.setApplicationDestinationPrefixes("/");
//		registry.setApplicationDestinationPrefixes("/app");
//		registry.setApplicationDestinationPrefixes("/nmesschat");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		log.info("[ LOG ] WebSocketConfig/registerStompEndpoints  registry : " + registry);
		
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}

}
