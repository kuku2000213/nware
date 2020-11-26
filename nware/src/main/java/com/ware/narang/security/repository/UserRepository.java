package com.ware.narang.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ware.narang.security.entity.Users;

public interface UserRepository extends JpaRepository<Users, String>{

}
