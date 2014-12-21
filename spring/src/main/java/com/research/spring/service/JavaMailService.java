package com.research.spring.service;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * 邮件发送处理类
 * @author wdmcygah
 *
 */
@Service
public class JavaMailService {

	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 发送简单文本
	 */
	public void sendSimpleMail(){
		MimeMessage javaMailMessage = mailSender.createMimeMessage();
		MimeMailMessage msgWrapper = new MimeMailMessage(javaMailMessage);
		msgWrapper.setTo("toEmailAddress");
		msgWrapper.setFrom("fromEmailAddress");
		msgWrapper.setSubject("测试");
		msgWrapper.setText("测试的内容.");
		msgWrapper.setSentDate(new Date());
		mailSender.send(msgWrapper.getMimeMessage());
		System.out.println("发送成功........");
	}
	
	/**
	 * 发送更多内容邮件
	 */
	public void sendMultiMail(){
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
				helper.setTo("toEmailAddress");
				helper.setFrom("fromEmailAddress");
				helper.setSubject("测试");
				StringBuilder sb = new StringBuilder("<html><body>hello..</body></html>");
				helper.setText(sb.toString(),true);
				helper.addInline("inlinePng", new ClassPathResource("/multisource/test.jpg"));
				helper.addAttachment("test.png", new ClassPathResource("/multisource/test.png"));
			}
		});
	}
	
}
