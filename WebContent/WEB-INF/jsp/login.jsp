<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	fieldset
{
  border: 1px solid #C7C7C7;
  margin-top: 16px;
  padding: 8px;
}

legend
{
  font: bold 12px Arial, Helvetica, sans-serif;
  color: #00008B;
  background-color: #FFFFFF;
}
</style>
<link rel="stylesheet" href="/HpuFood/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/HpuFood/js/jquery-3.3.1.js"></script>
<script src="/HpuFood/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title> 商家登录 </title>
</head>
<body >
	<fieldset style="width:40%;margin:0 auto;margin-top:10%">
	<legend>商家登录</legend>
		<br><br>
		<form action="/HpuFood/store/login.do" method="post">
			<div class="form-group form-group-lg">
				<label for="name">店长名 </label> <input type="text" name="name" id="name"
					class="form-control" placeholder="登录名">
			</div>
			<div class="form-group form-group-lg">
				<label for="password">Password</label> <input name="password" id="password"
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password">
			</div>
			<br>
			<button type="submit" class="btn btn-default">登录</button>
		</form>
	</fieldset>
</body>
</html>