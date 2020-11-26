package com.ware.narang.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

@Log
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {

		log.info("[ LOG ] CustomNoOpPasswordEncoder/encode  before encode : " + rawPassword);

		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info("[ LOG ] CustomNoOpPasswordEncoder/matches  matches : " + rawPassword + " : " + encodedPassword);
		
		return rawPassword.toString().equals(encodedPassword);
	}

}
