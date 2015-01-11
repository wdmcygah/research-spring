package com.research.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.research.spring.model.RegisterInfo;
import com.research.spring.model.ValidationResult;
import com.research.spring.utils.ValidationUtils;

/**
 * 注册控制器
 * @author wdmcygah
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	/**
	 * 注册页面展示
	 */
	@RequestMapping("/show")
	public String register(){
		return "/register/show";
	}
	
	/**
	 * 注册方法（主要为了演示使用Hibernate validation进行校验）
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String doRegister(RegisterInfo info,Model model){
		//1、进行参数校验
		ValidationResult result = ValidationUtils.validateEntity(info);
		if( result.isHasErrors() ){
			model.addAttribute("errorMsg", result.getErrorMsg());
			return "/register/show";
		}
		
		//2、注册逻辑——省略
		model.addAttribute("successFlag", "1");
		return "/register/show";
	}
	
	/**
	 * 判断用户名是否存在
	 * 详细描述：
	 * 1、这里只作简单的演示，若名称以y开头则返回存在。实际运用中可能是查询数据库
	 * 2、注意这里用到@ResponseBody注解，表示返回值不是视图名，直接将返回值绑定到response body中
	 * @param name
	 * @return
	 */
	@RequestMapping(value="isNameExists",method=RequestMethod.POST)
	@ResponseBody
	public String isNameExists( String name ){
		if( StringUtils.isEmpty(name) ){
			return "false";
		}
		//只有以'y'开头的名字才是不存在的
		if( name.startsWith("y") ){
			return "true";
		}
		return "false";
	}
}
