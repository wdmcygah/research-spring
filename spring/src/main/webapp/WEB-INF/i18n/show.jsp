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
<script>
	 function changeLang(lang){
		if (lang!=null && lang!=""){
			document.cookie = "clientlanguage=" + lang;
		}
		window.location.reload();
	} 
</script>
<body>
	<div class="container">
		<div class="text-center">
			<h3>下面是国际化简单示例，点击按钮进行切换！！</h3>
		</div>
		<div class="text-center">
			<blockquote>
				${content}
				<input class="btn btn-default" type="button" value="中文"  onclick="changeLang('zh_CN')" />
				<input class="btn btn-default" type="button" value="English"  onclick="changeLang('en_US')" />
			</blockquote>
		</div>
	</div>
</body>
</html>
