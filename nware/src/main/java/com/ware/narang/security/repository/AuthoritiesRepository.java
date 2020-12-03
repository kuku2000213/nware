package com.ware.narang.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ware.narang.security.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>{

}
