<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/HpuFood/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/HpuFood/js/jquery-3.3.1.js"></script>
<script src="/HpuFood/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title> 美食大全 </title>
<script type="text/javascript">

	var colPre = "<div class='col-sm-6 col-md-4' style='display:inline'><div class='thumbnail'>";
	var colSuf = "</div></div>";
	var hasMore = true;
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
	$(function(){
		var pageNum = 0;
		function showHotFood(pageCount){
			$.getJSON("http://localhost:8080/HpuFood/dataDisplay/foodDisplay.do?sortRule=${sortRule}&pageNum="+pageNum+"&pageCount="+pageCount,function(data){
				var ht = "";
				$.each(data,function(n,item){
					if(data.length<pageCount-1){
						hasMore = false;
						$("#showMoreFoods").text("没有更多的数据了");
						$("#showMoreFoods").attr("disabled",true);
					}
					ht =ht+colPre+"<img src='"+item.url+"' alt='"+item.food.name+"'><div class='caption'><h3>"+item.food.name+"</h3><p>"+item.food.introduce
					+"</p><p><a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.food.id
							+"' class='btn btn-primary' role='button'>详情</a> "
							+"<a href='javascript:return false;' class='btn btn-default' role='button' id='collectFood"+n+"' onclick='collectFood(\"collectFood"+n+"\",\""+item.food.id+"\")'>收藏</a></p></div>"+colSuf;
					collect("collectFood"+n,item.food.id);
					if((n+1)%3==0 || n == (data.length-1)){
						ht = "<div class='row'>"+ht+"</div>";
						$("#foods").append(ht);
						ht = "";
					}
				});
				pageNum = pageNum + 1 ;
			});
		}
		showHotFood(18);
		$("#Desc2Asc").click(function(){
			var val = $("#Desc2Asc").text();
			if(val == "降序")
				$("#Desc2Asc").text("升序");
			if(val == "升序")
				$("#Desc2Asc").text("降序");
		});
		$("#scoreSort").click(function(){
			appendAndGo("score");
			
		});
		$("#numberSort").click(function(){
			appendAndGo("numberOfPeople");
			
		});
		$("#dateSort").click(function(){
			appendAndGo("time");
			
		});
		
		function appendAndGo(sort){
			var val = $("#Desc2Asc").text();
			if(val == "降序")
				val =  sort+"/Desc";
			if(val == "升序")
				val = sort+"/Asc";
			$(location).prop('href', 'http://localhost:8080/HpuFood/dataDisplay/goFoods.do?sortRule='+val);
		}
	});
	function showMoreFoods(){
		if(hasMore)
			showHotFood(18);
	}
</script>
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
			<!-- Single button -->
			<div class="btn-group" style="float:right">
				<button type="button" class="btn btn-info dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					排序 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li style="margin-left:20px"><button id="scoreSort" style="background:none;border:none;">分数</button></li>
					<li style="margin-left:20px"><button id="numberSort" style="background:none;border:none;">评价人数</button></li>
					<li style="margin-left:20px"><button id="dateSort" style="background:none;border:none;">日期</button></li>
					<li role="separator" class="divider"></li>
					<li>
					<span class="label label-default" style="margin-left:20px;background-color:#663366"><button id="Desc2Asc" style="background:none;border:none;width:60px">${isDeAs}</button></span>
					</li>
				</ul>
			</div>
			<div id="foods">
			</div>
			<div class="btn-group" role="group" aria-label="..." style="float:right">		
			<button type="button" class="btn btn-default" id="showMoreFoods" onclick="showMoreFoods()">加载更多</button>		
			</div>
		</div>
	</div>
</body>
</html>