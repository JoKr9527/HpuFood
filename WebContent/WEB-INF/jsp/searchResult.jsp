<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/HpuFood/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/HpuFood/js/jquery-3.3.1.js"></script>
<script src="/HpuFood/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function collectFood(id,foodId){
		if($("#"+id).text()=="已收藏"){
			alert("已收藏，可在个人中心查看");
		}else{
			$.getJSON("http://localhost:8080/HpuFood/discuss/foodCollect.do?f_id="+foodId,function(data){
				alert(data.message);
				if(data.message == "收藏成功,请在个人中心查看"){
					$("#"+id).text("已收藏");
					$("#"+id).attr("disabled", true)
				}
			});
		}
	}
	function collect(id,foodId){
		$.getJSON("http://localhost:8080/HpuFood/discuss/isCollectFood.do?f_id="+foodId,function(data){
			$("#"+id).text(data.message);
			if(data.message == "已收藏")
				$("#"+id).attr("disabled", true)
		});
	}
	
	function collectStore(id,storeId){
		if($("#"+id).text()=="已收藏"){
			alert("已收藏，可在个人中心查看");
		}else{
			$.getJSON("http://localhost:8080/HpuFood/discuss/storeCollect.do?s_id="+storeId,function(data){
				alert(data.message);
				if(data.message == "收藏成功,请在个人中心查看"){
					$("#"+id).text("已收藏");
					$("#"+id).attr("disabled", true)
				}
			});
		}
	}
	function storecollect(id,storeId){
		$.getJSON("http://localhost:8080/HpuFood/discuss/isCollectStore.do?s_id="+storeId,function(data){
			$("#"+id).text(data.message);
			if(data.message == "已收藏")
				$("#"+id).attr("disabled", true)
		});
	}
</script>
<title>首页</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/HpuFood/"><img alt="河南理工大学美食网站" src="/HpuFood/images/log.png" height="30px" width="50px" style="margin-top:-6px"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form class="navbar-form navbar-left" action="/HpuFood/dataDisplay/searchDisplay.do" method="post">
					<div class="form-group">
						<input type="text" name="name" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
				<c:choose>
					<c:when test="${not empty admin or not empty user}">
						<ul class="nav navbar-nav navbar-right">
							<c:if test="${not empty user}">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">${user.name} <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a
											href="/HpuFood/user/homepage.do?userId=${user.id }">个人中心</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="/HpuFood/user/logout.do">注销</a></li>
									</ul></li>
							</c:if>
							<c:if test="${not empty admin }">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">${admin.name} <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a
											href="/HpuFood/store/homepage.do?storeId=${admin.store.id}">个人中心</a></li>
										<li role="separator" class="divider"></li>
										<li><a href="/HpuFood/store/logout.do">注销</a></li>
									</ul></li>
							</c:if>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">登录 <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/HpuFood/store/goLogin.do">商家登录</a></li>
									<li><a href="/HpuFood/user/goLogin.do">用户登录</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">注册 <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/HpuFood/store/goRegister.do">商家注册</a></li>
									<li><a href="/HpuFood/user/goRegister.do">用户注册</a></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="content" style="width: 80%; margin: 5% auto">
		<div id="food">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<button class="btn btn-primary" type="button">
						美食 <span class="badge">${fn:length(foodList)}</span>
					</button>
				</div>
				<!-- Table -->
				<table class="table" style="width:65%;text-content:left">
					<tr>
						<th>美食名称</th>
						<th>介绍</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach items="${foodList}" var="food" varStatus="status">
						<tr>
							<td>${food.name}</td>
							<td>${food.introduce}</td>
							<td><a
								href="http://localhost:8080/HpuFood/store/showFood.do?f_id=${food.id }"
								class="btn btn-primary" role="button">详情</a></td>
							<td><a href="javascript:return false;"
								class="btn btn-default" role="button" id="collectFood${status.index }"
								onclick='collectFood("collectFood${status.index}","${food.id }")'>收藏</a></td>
						</tr>
						<script type="text/javascript">
							collect("collectFood${status.index }","${food.id}");
						</script>
					</c:forEach>
				</table>
			</div>
		</div>
		<br>
		<hr>
		<br>
		<div id="store">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<button class="btn btn-primary" type="button">
						店铺 <span class="badge">${fn:length(storeList)}</span>
					</button>
				</div>
				<!-- Table -->
				<table class="table" style="width:65%;text-content:left">
					<tr>
						<th>店铺名称</th>
						<th>所属区域</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach items="${storeList}" var="store" varStatus="status">
						<tr>
							<td>${store.name}</td>
							<td>${store.restaurant.name}</td>
							<td><a
								href="http://localhost:8080/HpuFood/store/homepage.do?storeId=${store.id}"
								class="btn btn-primary" role="button">主页</a></td>
							<td><a href="javascript:return false;"
								class="btn btn-default" role="button"
								id="collectStore${status.index }"
								onclick='collectStore("collectStore${status.index}","${store.id }")'>收藏</a></td>
						</tr>
						<script type="text/javascript">
							storecollect("collectStore${status.index }", "${store.id}");
						</script>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>