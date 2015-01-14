<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/statics/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<form action="loginIn.htm" method="post" id="loginForm"
			class="form-horizontal" role="form">
			<fieldset class="text-center">
				<legend> 使用登录样例演示Cookie用法</legend>
				<c:choose>
					<c:when test="${status=='1'}">
							<div class="alert alert-success" role="alert">登录成功！
								<input type="hidden" id="userName" value="${userName}" />
							</div>
							<button type="button" class="btn btn-primary btn-large"
							id="loginOutButton">注销</button>
								<button type="button" class="btn btn-primary btn-large"
							onclick="window.location.href='show.htm' ">返回</button>
					</c:when>
					<c:when test="${status=='2'}">
							<div class="alert alert-success" role="alert">这次是因为记住用户登录成功的！
								<input type="hidden"  id="userName"  value="${userName}" />
							</div>
							<button type="button" class="btn btn-primary btn-large"
							id="loginOutButton">注销</button>
								<button type="button" class="btn btn-primary btn-large"
							onclick="window.location.href='show.htm' ">返回</button>
					</c:when>
					<c:when test="${status=='-1'}">
							<div class="alert alert-danger" role="alert">用户名或密码错误！</div>
								<button type="button" class="btn btn-primary btn-large"
							onclick="window.location.href='show.htm' ">返回</button>
					</c:when>
					<c:otherwise>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="name">用户名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="userName"
									id="userName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="password">密码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" name="password"
									id="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8">
								<input type="checkbox" name="remember"
									id="remember">
							<label class="control-label" for="remember">记住我</label>
							</div>
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
		src="<%=request.getContextPath()%>/statics/js/import/bootstrap.min.js"></script>
		<script
		src="<%=request.getContextPath()%>/statics/js/my/login.js"></script>
</body>
</html>
