package com.research.spring.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 注册信息
 * @author wdmcygah
 *
 */
public class RegisterInfo {

	@NotBlank(message="名字不能为空或者空串")
	private String name;
	
	@NotBlank(message="密码不能为空或者空串")
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}",message="密码必须是5~10位数字和字母的组合")
	private String password;
	
	@NotBlank(message="邮箱不能为空或者空串")
	@Email(message="邮箱格式不正确")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
