package com.ware.narang.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ware.narang.security.encoder.CustomNoOpPasswordEncoder;

import lombok.extern.java.Log;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		log.info("[ LOG ] SecurityConfig/configure *HttpSecurity");
		
		//form 기반 인증 기능 사용
		http.formLogin()
		.loginPage("/login");
		
		http.authorizeRequests()
		.antMatchers("/", "/login2")
		.permitAll();
		
		http.authorizeRequests()
		.antMatchers("/security/admin" , "/board/register")
		.hasRole("ADMIN");
		
		http.exceptionHandling()
		.accessDeniedPage("/accessError");
		
		http.logout()
		.logoutUrl("/logout")
		.invalidateHttpSession(true);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		
		log.info("[ LOG ] SecurityConfig/configure *AuthenticationManagerBuilder");
		
//		auth.inMemoryAuthentication()
//		.withUser("choi")
//		.password("{noop}choi")
//		.roles("ADMIN");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(createPasswordEncoder());
		
//		auth.userDetailsService(userService).passwordEncoder(createPasswordEncoder());
		
	}
	// 사용자가 정의한 비밀번호 암호화 처리기
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		
		
		return new CustomNoOpPasswordEncoder();
	}
	
}
