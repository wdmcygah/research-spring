<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/statics/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 这里引入的是校验提示的样式 -->
<link href="<%=request.getContextPath()%>/statics/css/valid.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">

		<form action="create.htm" method="post" id="registerForm"
			class="form-horizontal" role="form">
			<fieldset class="text-center">
				<legend> 前后台校验功能测试页面 </legend>
				<c:choose>
					<c:when test="${successFlag=='1'}">
							注册成功！
								<button type="button" class="btn btn-primary btn-large"
							onclick="window.location.href='show.htm' ">返回</button>
					</c:when>
					<c:otherwise>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="name">姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="name" id="name">
							</div>
							<label class="error" for="name">${errorMsg.name}</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="password">密码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" name="password"
									id="password">
							</div>
							<label class="error" for="password">${errorMsg.password}</label>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="email">电子邮箱</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="email" id="email">
							</div>
							<label class="error" for="email">${errorMsg.email}</label>
						</div>
						<div class="form-actions ">
							<button type="submit" class="btn btn-primary btn-large">提交</button>
							<button type="reset" class="btn">取消</button>
						</div>
					</c:otherwise>
				</c:choose>
			</fieldset>
		</form>
	</div>

	<!-- js -->
	<script
		src="<%=request.getContextPath()%>/statics/js/import/jquery-1.11.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/statics/js/import/jquery.validate.js"></script>
	<script
		src="<%=request.getContextPath()%>/statics/js/import/bootstrap.min.js"></script>
	<!-- 这里引入的是校验提示信息的JS文件 -->
	<script
		src="<%=request.getContextPath()%>/statics/js/import/messages_zh.js"></script>
	<!-- 这里引入的是表单校验的JS文件 -->
	<script
		src="<%=request.getContextPath()%>/statics/js/my/register_validation.js"></script>
	<!-- 这里引入的是扩展Jquery validation的自定义JS文件 -->
	<script
		src="<%=request.getContextPath()%>/statics/js/my/additional-methods.js"></script>
</body>
</html>
