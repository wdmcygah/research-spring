<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="Login" />
</jsp:include>
</head>
<body>
	<h1>Please Log In to Your Account</h1>
	<p>Please use the form below to log in to your account.</p>
	<form action="j_spring_security_check" method="post">
		<label for="j_username">Login</label>: <input id="j_username"
			name="j_username" size="20" maxlength="50" type="text" /> <br /> <label
			for="j_password">Password</label>: <input id="j_password"
			name="j_password" size="20" maxlength="50" type="password" /> <br />
		<input id="_spring_security_remember_me"
			name="_spring_security_
remember_me" type="checkbox" value="true" />
		<label for="_spring_security_remember_me">Remember Me?</label> <input
			type="submit" value="Login" />
	</form>
</body>
</html>
