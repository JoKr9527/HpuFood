<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 更改美食信息 </title>
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
		<legend> 更改美食信息 </legend>
		<form action="/HpuFood/store/updateFood.do" method="post" enctype="multipart/form-data">
			<input name="id" type="hidden" value="${food.id }">
			<div class="form-group form-group-lg">
				<label for="name">食物名称 </label> <input type="text" name="name" id="name"
					class="form-control" value="${food.name }">
			</div>
			<div class="form-group">
				<label for="introduce">详细介绍</label> <input
					name="introduce" type="text" class="form-control"
					id="introduce" value="${food.introduce }">
			</div>
			<div class="form-group">
				<label class="sr-only" for="exampleInputAmount">价格</label>
				<div class="input-group">
					<div class="input-group-addon">￥</div>
					<input type="text" class="form-control" id="exampleInputAmount" name="price"
						value="${food.price}">
					<div class="input-group-addon">.00</div>
				</div>
			</div>
			<div class="row">

				<c:forEach items="${picturesList }" var="picture">
					<div class="col-xs-6 col-md-3">
						<a
							href="http://localhost:8080/HpuFood/store/delFoodPictures.do?fp_id=${picture.id }"
							style="text-decoration: none; color: #336699;" class="thumbnail"><img
							src="${picture.url}">点击图片即可删除</a>
					</div>
				</c:forEach>

			</div>
			<div class="form-group">
				<label for="exampleInputFile">上传照片</label> <input type="file" name="picture"
					id="exampleInputFile">
				<p class="help-block">推荐上传至少一张照片</p>
			</div>
			<button type="submit" class="btn btn-default">上传</button>
		</form>
	</fieldset>
</body>
</html>