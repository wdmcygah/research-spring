package com.research.spring.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class FreemarkerMailServiceTest extends BaseTest{

  @Autowired
  private FreemarkerMailService fmService;
	
  @Test
  public void sendMail() {
	  String templateName = "hello.ftl";
	  Map<String,String> content = new HashMap<String, String>();
	  content.put("name", "wdmcygah");
	  String addr = "ToEmailAddress";
	  String subject = "测试";
	  fmService.sendMail(templateName, addr, subject, content);
  }
}
