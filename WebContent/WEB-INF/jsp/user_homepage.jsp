<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 用户个人主页  </title>
<link rel="stylesheet" href="css/reset.css">
<!-- CSS reset -->
<link rel="stylesheet" type="text/css" href="css/zzsc-demo.css">
<link rel="stylesheet" href="css/style_userManage.css">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
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
<script src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var prefix = "<tr>";
	var suffix ="</tr>";
	var infoShow = true;
	var evaluatedStore = true;
	var evaluatedFood = true;
	
	$(function(){
		$("#info").click(function(){
			$("#user_info").toggle();
			$("#update_form").hide();
			$("#update_user").show();
			$("#show_info").show();
			$("#evaluated_store").hide();
			$("#evaluated_food").hide();
			$("#collect_list").hide();
			if(infoShow){
				$.getJSON("http://localhost:8080/HpuFood/user/info.do?u_id=${userId}",function(data){
		    		$("#user_name").append(data.name);
					var html = prefix+"<td>邮箱：</td>"+"<td>"+data.email+"</td>"+suffix;				
					html = html+prefix+"<td>个人简介：</td>"+"<td>"+data.introduce+"</td>"+suffix;				
					$("#show_info").append(html);
				});
				infoShow = false;
			}
		});
		$("#update_user").click(function(){
			$("#update_user").hide();
			$("#show_info").hide();
			$("#update_form").show();
		});
		$("#evaluatedStore").click(function(){
			$("#evaluated_store").toggle();
			$("#user_info").hide();
			$("#evaluated_food").hide();
			$("#collect_list").hide();
			if(evaluatedStore){
		   	 	$.getJSON("http://localhost:8080/HpuFood/user/goEvaluatedStore.do?u_id=${userId}",function(data){
		   	 		var html ;
		   	 		var time;
		   	 		var formatTime;
					$.each(data,function(n,item){
						time = new Date(item.publishTime);
						formatTime = time.getFullYear()+"年 "+time.getMonth()+"月 "+time.getDate()+"日 "+time.getHours()+" ："+time.getMinutes();
						html = html+prefix+"<td>"+formatTime+"</td><td>" + item.describe+"</td><td>"+item.score+"</td><td>"
							+"<a href='http://localhost:8080/HpuFood/store/homepage.do?storeId="+item.storeInfo.store.id+"'>"+item.storeInfo.store.name+"</a>"+suffix;
					});
					$("#evaluated_store_info").append(html);
				});
		   	 evaluatedStore = false;
	   	 	}
		});
		$("#evaluatedFood").click(function(){
			$("#evaluated_food").toggle();
			$("#user_info").hide();
			$("#evaluated_store").hide();
			$("#collect_list").hide();
			if(evaluatedFood){
		   	 	$.getJSON("http://localhost:8080/HpuFood/user/goEvaluatedFood.do?u_id=${userId}",function(data){
		   	 		var html="" ;
		   	 		var time;
		   	 		var formatTime;
					$.each(data,function(n,item){
						
						html = html+prefix+"<td>"+formatDate(item.publishTime)+"</td><td>" + item.describe+"</td><td>"+item.score+"</td><td>"
							+"<a href='http://localhost:8080/HpuFood/store/homepage.do?storeId="+item.foodInfo.food.id+"'>"
							+item.foodInfo.food.store.name+"</a></td><td>"
							+"<a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.foodInfo.food.id+"'>"+item.foodInfo.food.name+"</a></td>"
							+suffix;
					});
					$("#evaluated_food_info").append(html);
				});
		   	 	evaluatedFood = false;
	   	 	}
		});
		$("#collectList").click(function(){
			$("#collect_list").toggle();
			$("#user_info").hide();
			$("#evaluated_store").hide();
			$("#evaluated_food").hide();
			var prefix ="<tr>";
			var suffix="</tr>"; 
			$.getJSON("http://localhost:8080/HpuFood/discuss/storeCollectList.do",function(data){
				var html = "";
				$.each(data,function(n,item){
					html=html+prefix+"<td><a href='http://localhost:8080/HpuFood/store/homepage.do?storeId="+item.store.id+"'>"+item.store.name+"</a></td>"
							+"<td>"+item.store.restaurant.name+"</td>"+suffix;
				});
				$("#storeCollect").append(html);
			});
			$.getJSON("http://localhost:8080/HpuFood/discuss/foodCollectList.do",function(data){
				var html = "";
				$.each(data,function(n,item){
					html=html+prefix+"<td><a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.food.id+"'>"+item.food.name+"</a></td>"
							+"<td>"+item.food.price+" 元</td><td>"+item.food.introduce+"</td><td>"+item.food.store.name+"</td>"+suffix;
				});
				$("#foodCollect").append(html);
			});
		});
		function formatDate(misec){
			var time = new Date(misec);
			return time.getFullYear()+"年 "+time.getMonth()+"月 "+time.getDate()+"日 "+time.getHours()+" ："+time.getMinutes();
		}
		$("#info").click();
	});
</script>
</head>
<body>
	<header class="cd-auto-hide-header" style="background-color: #EDEDED">
		<div class="logo">
			<a href="#0">hpu 美食</a>
		</div>
		<nav class="cd-primary-nav">
			<ul class="cd-navigation">
				<li><a id="info" href="javascript:void(0);" > 个人信息 </a></li>		
			</ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<ul class="cd-navigation">
				<li><a id="evaluatedStore" href="javascript:void(0);" > 已评论店铺 </a></li>		
			</ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<ul class="cd-navigation">
				<li><a id="evaluatedFood" href="javascript:void(0);" > 已评论美食 </a></li>		
			</ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${not empty user }">
				<ul class="cd-navigation">
					<li><a id="collectList" href="javascript:void(0);" > 收藏列表 </a></li>		
				</ul>
			</c:if>
		</nav>
		<!-- .cd-primary-nav -->
	</header>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="user_info" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset>
			<legend id="user_name"></legend>
			<table id="show_info" style="width:60%;margin:0 auto;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr>
				<td></td>
			</tr>	
			</table>
			<c:if test="${not empty user }">
				<form id="update_form" action="/HpuFood/user/update.do" method="post" style="display:none">
					<table style="width:80%;margin:30px auto;border-collapse:separate; border-spacing:0px 40px;"  >
						<tr>
							<td><span>用户名  </span></td>
							<td><input name="name" type="text" value="${user.name}"></td>
						</tr>
						<tr>
							<td><span>密码  </span></td>
							<td><input name="password" type="text" value=""></td>
						</tr>
						<tr>
							<td><span>邮箱  </span></td>
							<td><input name="email" type="text" height="5px" value="${user.email }"></td>
						</tr>
						<tr>
							<td><span>介绍  </span></td>
							<td><input name="introduce" type="text" value="${user.introduce }"></td>
						</tr>
						<tr>
							<td><span> <input type="hidden" name="id" value="${user.id}"></span></td>
							<td align="right"><input value="确认修改" type="submit"></td>
						</tr>
					</table>
				</form>
				<div><button id="update_user">修改</button></div>
			</c:if>
		</fieldset>
	</div>
	<div id="evaluated_store" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset>
			<legend> 店铺 </legend>
			<table id="evaluated_store_info" style="width:60%;margin:0 auto;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr style="letter-spacing: 10px;font-weight:bold;">
				<th style="text-align:center">评论时间</th><th style="text-align:center">内容</th><th style="text-align:center">分数</th><th style="text-align:center">店铺</th>
			</tr>
			<tr style="letter-spacing: 10px;font-weight:bold;">
				
			</tr>	
			</table>
		</fieldset>
	</div>
	<div id="evaluated_food" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset>
			<legend> 美食 </legend>
			<table id="evaluated_food_info" style="width:60%;margin:0 auto;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr style="letter-spacing: 10px;font-weight:bold">
				<th style="text-align:center">评论时间</th><th style="text-align:center">内容</th><th style="text-align:center">分数</th><th style="text-align:center">店铺</th><th style="text-align:center">美食</th>
			</tr>	
			</table>
		</fieldset>
	</div>
	<div id="collect_list"  style="display:none;width:80%;margin:0 auto;text-align:center;float:left;">
		<div class="panel panel-default" style="width:40%;margin:0 20%;text-align:center;float:left">
			<!-- Default panel contents -->
			<div class="panel-heading">店铺收藏</div>
			<div class="panel-body">
				<p> 你可以在这里查看最近收藏的店铺 </p>
			</div>
			<table class="table" id="storeCollect">
				<tr>
					<td>店铺名</td><td>所属区域</td>
				</tr>
			</table>
		</div>
		<div class="panel panel-default" style="width:50%;margin:5% 20%;text-align:center;float:left">
			<!-- Default panel contents -->
			<div class="panel-heading">美食收藏</div>
			<div class="panel-body">
				<p> 你可以在这里查看最近收藏的美食 </p>
			</div>
			<table class="table" id="foodCollect">
				<tr>
					<td>美食名</td><td>价格</td><td>介绍</td><td>所属店铺</td>
				</tr>
			</table>
		</div>
	</div>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>