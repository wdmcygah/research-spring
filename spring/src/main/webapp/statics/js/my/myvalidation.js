$(function() {

	$('#myform').validate(
			{
				rules : {
					name : {
						required : true
					},
					email : {
						required : true,
						email : true
					},
					url:{
						required : true,
						url:true,
					},
					dateISO:{
						required : true,
						dateISO:true
					},
					number:{
						required : true,
						number:true
					},
					digits:{
						required : true,
						digits:true
					},
					password:{
						required : true,
						password:true
					},
					confPwd:{
						required : true,
						password:true,
						equalTo:"#password"
					},
					notEqualTo:{
						required : true,
						notEqualTo: "0"
					}
				},
				messages:{
					confPwd:{
						equalTo:"与上面输入的密码不一致"
					}
				},
				success : "valid",
				errorClass:"error",
				errorPlacement: function(error, element) {  
				    error.appendTo(element.parent().parent());  
				}
			});

});