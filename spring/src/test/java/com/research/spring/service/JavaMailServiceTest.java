package com.research.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class JavaMailServiceTest extends BaseTest{

	@Autowired
	private JavaMailService mailService;
	
	@Test
	public void send(){
		mailService.sendSimpleMail();
	}
	
	@Test
	public void sendMultiMail(){
		mailService.sendMultiMail();
	}
	
}
