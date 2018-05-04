<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 食品信息 </title>
</head>
<body>
	<fieldset>
		<legend> 上传食品到店铺 </legend>
		<form action="/HpuFood/store/food.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>食品名称：</td>
					<td><input name="name" type="text"></td>
				</tr>
				<tr>
					<td>详细介绍：</td>
					<td><input name="introduce" type="text"></td>
				</tr>
				<tr>
					<td>价格：</td>
					<td><input name="price" type="text"></td>
				</tr>
				<tr>
					<td>照片：</td>
					<td><input name="picture" type="file"></td>
				</tr>
				<tr>
					<td> </td>
					<td><input  type="submit" value="上传"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>