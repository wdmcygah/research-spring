<html>
<head>        
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head>
<body>
<h3>测试Cookie功能</h3>
<form action="login/show.htm" method="post">
	<input type="submit" value="测试Cookie功能"></input>	
</form>

<h3>测试下载Excel功能</h3>
<form action="file/download.htm" enctype="multipart/form-data" method="post">
	<input type="submit" value="下载Excel"></input>	
</form>

<h3>测试上传Excel功能</h3>
<form action="file/upload.htm" enctype="multipart/form-data" method="post">
	<input type="file" name="file"></input>
	<input type="submit" value="上传Excel"></input>	
</form>

<h3>测试Excel的打包下载功能</h3>
<form action="zip/download.htm" enctype="multipart/form-data" method="post">
	<input type="submit" value="下载Excel打包文件"></input>	
</form>
</body>
</html>
