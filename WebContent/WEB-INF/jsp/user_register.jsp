<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 普通用户注册 </title>
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
</head>
<body>
	<fieldset style="width:40%;margin:0 auto;margin-top:10%">
		<legend>用户注册</legend>
		<form action="/HpuFood/user/register.do" method="post">
			<div class="form-group form-group-lg">
				<label for="name">用户名 </label> <input type="text" name="name" id="name"
					class="form-control" placeholder="用户名">
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input
					name="password" type="password" class="form-control"
					id="password" placeholder="Password">
			</div>
			<div class="form-group">
				<label for="email">邮箱</label> <input name="email"
					type="email" class="form-control" id="email"
					placeholder="Email">
			</div>
			<div class="form-group form-group-lg">
				<label for="introduce">介绍 </label> <textarea class="form-control" rows="3" name="introduce" id="introduce"></textarea>
			</div>
			<button type="submit" class="btn btn-default">注册</button>
		</form>
	</fieldset>
</body>
</html>