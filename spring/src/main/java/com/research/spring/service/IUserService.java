package com.research.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{

	void changePassword(String username, String password);
}
