$(function() {

	$('#registerForm').validate({
		rules : {
			name : {
				required : true,
				remote : {
					url : "isNameExists.htm", //后台处理程序
					type : "post", //数据发送方式
					data : { //要传递的数据
						name : function() {
							return $("#name").val();
						}
					}
				}
			},
			password : {
				required : true,
			},
			email : {
				required : true,
				email : true
			}
		},
		messages:{
			name:{
				remote:"名字已经存在"
			}
		},
		success : "valid",
		errorClass : "error",
		errorPlacement : function(error, element) {
			error.appendTo(element.parent().parent());
		}
	});
});