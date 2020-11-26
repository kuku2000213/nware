package com.ware.narang.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ware.narang.security.entity.Users;
import com.ware.narang.security.repository.UserRepository;
import com.ware.narang.security.service.UsersService;

import lombok.extern.java.Log;

@Log
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository uRep;

	@Override
	public void signUp(Users users) throws Exception {
		log.info("[ LOG ] BoardServiceImpl/register  users : " + users);
		
		uRep.save(users);
		
	}
	
	
}
