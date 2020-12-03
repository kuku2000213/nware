package com.ware.narang.security.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.ware.narang.security.encoder.CustomNoOpPasswordEncoder;

import lombok.extern.java.Log;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	// role 계층구조 설정
	@Bean
	public RoleHierarchy roleHierarchy() {
	  RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	  roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");
	  return roleHierarchy;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("[ LOG ] SecurityConfig/configure *HttpSecurity");

//		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MEMBER");

		// form 기반 인증 기능 사용
		http.formLogin().loginPage("/login").successHandler(new LoginSuccessHandler());

		http.authorizeRequests().antMatchers("/", "/login2").permitAll();

		http.authorizeRequests().antMatchers("/security/admin", "/board/register", "/foundUser", "/community/main", "/nMess/*").hasRole("ADMIN");

		http.authorizeRequests().antMatchers("/community/main", "/nMess/*").hasRole("MEMBER");

		http.exceptionHandling().accessDeniedPage("/accessError");

		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		
		//세션 관리
//		http.sessionManagement()
//	    .maximumSessions(1)
//	    .maxSessionsPreventsLogin(true)
//	    .expiredUrl("/duplicated-login")
//	    .sessionRegistry(sessionRegistry());

	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
	  return new SessionRegistryImpl();
	}// Register HttpSessionEventPublisher


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		log.info("[ LOG ] SecurityConfig/configure *AuthenticationManagerBuilder");

//		auth.inMemoryAuthentication()
//		.withUser("choi")
//		.password("{noop}choi")
////		.roles("ADMIN");
//		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(createPasswordEncoder());
//		.passwordEncoder(bcryptPasswordEncoder());

//		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder());

	}

	// Password Encoder 등록
//	@Bean
//	public PasswordEncoder bcryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	// 사용자가 정의한 비밀번호 암호화 처리기 좆까 bcrypit로쓸거임ㅗ
	@Bean
	public PasswordEncoder createPasswordEncoder() {

		return new CustomNoOpPasswordEncoder();
	}

}
