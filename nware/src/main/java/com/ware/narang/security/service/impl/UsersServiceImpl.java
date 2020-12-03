package com.ware.narang.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ware.narang.security.entity.Authorities;
import com.ware.narang.security.entity.Users;
import com.ware.narang.security.repository.AuthoritiesRepository;
import com.ware.narang.security.repository.UsersRepository;
import com.ware.narang.security.service.UsersService;

import lombok.extern.java.Log;

@Log
@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository uRepo;
	
	@Autowired
	private AuthoritiesRepository aRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> uList() throws Exception {
		
		log.info("[ LOG ] UsersService/uList");
		
		return uRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Authorities> aList() throws Exception {
		log.info("[ LOG ] UsersService/aList");
		
		return aRepo.findAll();
	}

	@Override
	public void uSignUp(Users users) throws Exception {
		
		log.info("[ LOG ] UsersService/uSignUp  users : " + users);
		
		uRepo.save(users);
	}

	@Override
	public void aSignup(Authorities auth) throws Exception {
		
		log.info("[ LOG ] UsersService/aSignUp  auth : " + auth);
		
		aRepo.save(auth);
		
	}

	@Override
	public Users selectAllFromUsername(String username) throws Exception {
		log.info("[ LOG ] UsersService/selectUserId  username : " + username);
		return uRepo.findAllByUsername(username);
	}

//	@Override
//	public Long selectUserId(String username) throws Exception {
//		log.info("[ LOG ] UsersService/selectUserId  username : " + username);
//		return uRepo.findUser_idByUsername(username);
//	}

//	@Override
//	public Users selectUserId(String username) {
//		log.info("[ LOG ] UsersService/selectUserId  username : " + username);
//		return uRepo.findIdByName(username);
//	}

//	@Override
//	public Users selectUserId(String username) throws Exception {
//
//		log.info("[ LOG ] UsersService/selectUserId  username : " + username);
//		return uRepo.findIdByName(username);
//	}

}
