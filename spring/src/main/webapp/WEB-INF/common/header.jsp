<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
	<c:url value="/logout" var="logoutUrl" />
	<c:url value="/password.htm" var="passwordUrl" />
	<ul>
		<li><a href="${logoutUrl}">Log Out</a></li>
		<li><a href="${passwordUrl}">Change Password</a></li>
	</ul>
</body>
</html>
