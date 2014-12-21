package com.research.spring.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemarker文件作为模板发送邮件
 * @author wdmcygah
 *
 */
@Service
public class FreemarkerMailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private FreeMarkerConfigurer fmConfigurer;
	
	/**
	 * 发送邮件
	 * @param templateName 模板文件名
	 * @param toEmailAddr  要发送到的邮箱地址
	 * @param subject	邮件主题名
	 * @param content	邮件内容
	 */
	public void sendMail(String templateName,String toEmailAddr, String subject, Map<String,String> content){
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper=new MimeMessageHelper(msg,false,"utf-8");
			//要发送到的邮箱地址
			helper.setTo(toEmailAddr);
			//从哪发送的邮箱地址,自己改成真实的
			helper.setFrom("fromEmailAddress");
			helper.setSubject(subject);
			String text = getMailContent(content,templateName);
			helper.setText(text,true);
			helper.setSentDate(new Date());
			mailSender.send(helper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("发送邮件异常");
		}
		
	}

	/**
	 * 根据模板获得解析后的内容
	 * @param content	邮件内容
	 * @param templateName	邮件模板名
	 * @return
	 */
	private String getMailContent(Map<String,String> content, String templateName) {
		String result = null;
		try {
			Template template = fmConfigurer.getConfiguration().getTemplate(templateName);
			result = FreeMarkerTemplateUtils.processTemplateIntoString(template, content);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("获得freemarker模板出错");
		} catch (TemplateException e) {
			e.printStackTrace();
			System.out.println("模板解析出错");
		}
		return result;
	}
}
