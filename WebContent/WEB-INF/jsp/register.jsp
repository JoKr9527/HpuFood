<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 商家注册 </title>
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
		<legend>商家注册</legend>
		<form action="/HpuFood/store/register.do" method="post">
			<div class="form-group .form-group-sm">
				<label for="restaurantName">餐厅名/校外</label> <input
					name="restaurantName" id="restaurantName" type="text"
					class="form-control" placeholder="输入文本">
			</div>
			<div class="form-group .form-group-lg">
				<label for="storeName">店铺名称</label> <input name="storeName"
					id="storeName" type="text" class="form-control" placeholder="输入文本">
			</div>
			<div class="form-group">
				<label for="adminName">店长名</label> <input name="adminName"
					id="adminName" type="text" class="form-control" placeholder="输入文本">
			</div>
			<div class="form-group">
				<label for="password">登录密码</label> <input name="password"
					id="password" type="password" class="form-control"
					id="exampleInputPassword1" placeholder="密码">
			</div>
			<div class="form-group">
				<label for="themaim">主营</label> <input name="themain" id="themain"
					type="text" class="form-control" placeholder="输入文本">
			</div>
			<div class="form-group">
				<label for="address">地址</label> <input name="address" id="address"
					type="text" class="form-control" placeholder="输入文本">
			</div>
			<button type="submit" class="btn btn-default">注册</button>
		</form>
	</fieldset>
</body>
</html>