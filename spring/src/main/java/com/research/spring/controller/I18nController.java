package com.research.spring.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 国际化demo控制器
 * @author wdmcygah
 */
@Controller
public class I18nController {

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("i18n")
	public String i18n(Model model){
		//获取本地化信息，从LocaleContext中得到
		Locale locale = LocaleContextHolder.getLocale();
		
		//初始化参数，这里简便演示，真实参数可能是从数据库查询处理的。这里的参数是与i18n目录下的配置文件需要替换的内容对应的
		Object [] objArr = new Object[4];
		objArr[0] = new Date();
		objArr[1] = messageSource.getMessage("goods", null, locale);//这个具体商品从配置中读取
		objArr[2] = "taobao";
		objArr[3] = new BigDecimal("39.20");
		
		
		//获取格式化后的内容
		String content = messageSource.getMessage("template", objArr, locale);
		model.addAttribute("content", content);
		return "/i18n/show";
	}
	
}
