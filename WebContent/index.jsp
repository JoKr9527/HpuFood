<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">

	var colPre = "<div class='col-sm-6 col-md-4' style='display:inline'><div class='thumbnail'>";
	var colSuf = "</div></div>";
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
	function goFoods(){
		$(location).prop('href', 'http://localhost:8080/HpuFood/dataDisplay/goFoods.do?sortRule=score/Desc');
	}
	function goStores(){
		$(location).prop('href', 'http://localhost:8080/HpuFood/dataDisplay/goStores.do?sortRule=getscore/Desc');
	}
	
	$(function(){
		var pageNum = 0;
		function showHotFood(){
			$.getJSON("http://localhost:8080/HpuFood/dataDisplay/foodDisplay.do?sortRule=score/Desc&pageNum="+pageNum+"&pageCount=9",function(data){
				$.each(data,function(n,item){
					var ht = "";
					ht =colPre+"<img src='"+item.url+"' alt='"+item.food.name+"'><div class='caption'><h3>"+item.food.name+"</h3><p>"+item.food.introduce
					+"</p><p><a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.food.id
							+"' class='btn btn-primary' role='button'>详情</a> "
							+"<a href='javascript:return false;' class='btn btn-default' role='button' id='collectFood"+n+"' onclick='collectFood(\"collectFood"+n+"\",\""+item.food.id+"\")'>收藏</a></p></div>"+colSuf;
					collect("collectFood"+n,item.food.id);
					if(n<=2){
						$("#hotfood_row1").append(ht);						
					}else if(n<=5){
						$("#hotfood_row2").append(ht);	
					}else if(n<=8)
						$("#hotfood_row3").append(ht);	
				});
			});
		}
		function showHotStore(){
			$.getJSON("http://localhost:8080/HpuFood/dataDisplay/storeDisplay.do?sortRule=getscore/Desc&pageNum="+pageNum+"&pageCount=9",function(data){
				$.each(data,function(n,item){
					var ht = "";
					ht =colPre+"<img src='"+item.url+"' alt='"+item.store.name+"'><div class='caption'><h3>"+item.store.name+"</h3><p>"+item.store.restaurant.name
					+"</p><p><a href='http://localhost:8080/HpuFood/store/homepage.do?storeId="+item.store.id
							+"' class='btn btn-primary' role='button'>详情</a> "
							+"<a href='javascript:return false;' class='btn btn-default' role='button' id='collectStore"+n+"' onclick='collectStore(\"collectStore"+n+"\",\""+item.store.id+"\")'>收藏</a></p></div>"+colSuf;
					storecollect("collectFood"+n,item.store.id);
					if(n<=2){
						$("#hotstore_row1").append(ht);						
					}else if(n<=5){
						$("#hotstore_row2").append(ht);	
					}else if(n<=8)
						$("#hotstore_row3").append(ht);	
				});
			});
		}
		showHotFood();
		showHotStore();
	});
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
		<div id="hotfood">
			<button type="button" class="btn btn-default btn-lg">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				最热美食
			</button>
			<div class="btn-group" role="group" aria-label="..." style="float:right">
			<button type="button" class="btn btn-default"></button>
			<button type="button" class="btn btn-default"  onclick="goFoods()">加载更多</button>
			<button type="button" class="btn btn-default"></button>
			</div>
			<div class='row' id='hotfood_row1'></div>
			<div class='row' id='hotfood_row2'></div>
			<div class='row' id='hotfood_row3'></div>
		</div>
		<br>
		<hr>
		<br>
		<div id="hotstore">
			<button type="button" class="btn btn-default btn-lg">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
				最热店铺
			</button>
			<div class="btn-group" role="group" aria-label="..." style="float:right">
			<button type="button" class="btn btn-default"></button>
			<button type="button" class="btn btn-default" onclick="goStores()">加载更多</button>
			<button type="button" class="btn btn-default"></button>
			</div>
			<div class='row' id='hotstore_row1'>
			</div>
			<div class='row' id='hotstore_row2'>
			</div>
			<div class='row' id='hotstore_row3'>
			</div>
		</div>
	</div>
</body>
</html>