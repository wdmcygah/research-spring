$(function() {
	
	//自定义密码校验规则
	$.validator.addMethod("password", function(value, element, params) {
		//5~10位数字和字母的组合
		var pwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}$/;
		return  this.optional(element) || pwd.test(value); 
	}, $.validator.format("密码必须是5~10位数字和字母的组合"));

	//自定义不等于校验规则
	$.validator.addMethod("notEqualTo", function(value, element, params) {
		return value!=params; 
	}, $.validator.format("输入必须不为0"));
	
});