<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 美食展示 </title>
<link rel="stylesheet" href="css/food.css">
<style type="text/css">
</style>
<link rel="stylesheet" href="/HpuFood/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/HpuFood/js/jquery-3.3.1.js"></script>
<script src="/HpuFood/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/HpuFood/js/foodPictures.js"></script>
<script type="text/javascript">
function formatDate(misec){
	var time = new Date(misec);
	return time.getFullYear()+"年 "+time.getMonth()+"月 "+time.getDate()+"日 "+time.getHours()+" ："+time.getMinutes();
}

function callFood(id,u_id,r_id){
	var value = $("#area"+id.substring(6,19)).val();
	if($("#area"+id.substring(6,19)).is(":hidden")){
		$("#area"+id.substring(6,19)).show();
	}else{
		var postData = {
				"to_id":u_id,
				 "content":value,
				"relation_id":r_id
		}
		 $.ajax({
	            async : false,
	            cache : false,
	            type : 'POST',
	            scriptCharset: 'utf-8',
	            url : '/HpuFood/discuss/chat.do',
	            dataType : "json",
	            data : postData,  	          
	            error : function() {
	                alert("回复失败，稍后重试");
	            },
	            success : function(data) {
	            	$("#area"+id.substring(6,19)).hide();
	            	showMoreReplay = true;
	                alert(data.message);
	            }
	        });
	}
}

function getChatList(relation_id,id){
	
	if($("#showMoreReplay"+id).css("display")=='none'){
		getChatFromChat("showChatFromUU.do",relation_id,id);
		getChatFromChat("showChatFromSU.do",relation_id,id);
		getChatFromChat("showChatFromUS.do",relation_id,id);
		$("#showMoreReplay"+id).show();
	}
}
function getChatFromChat(uri,relation_id,id){
	$.getJSON("http://localhost:8080/HpuFood/discuss/"+uri+"?relation_id="+relation_id,function(data){
		var ht = "";
		var pre = "<div style='margin-left:15%;text-align:left;border-left: 1px #1C86EE groove'>";
		var suf = "</div>";
		$.each(data,function(n,item){
			ht = ht+pre+item.from.name +" <div style='color: #9AC0CD;display:inline'>说：</div><br><br>&nbsp;&nbsp;&nbsp;&nbsp;" + item.content + "<div style='margin-left:60%;padding-bottom:1%;color:#838B8B'>"+formatDate(item.time) +"</div>"+ suf; 
		});
		$("#showMoreReplay"+id).append(ht);
	});
}
$(function(){
	var pageNum = 0;
	var dataOver = false;
	$("#showMore").click(function(){
		if(!dataOver){
			showEvaluateList();
		}
	});
	function showEvaluateList(){
		
		var ulPre = "<li style='width:80%;margin:30px auto;text-align:left;border-left: 1px #1C86EE groove'><div style='margin-left:10px;' >";
		var ulSuf = "</div></li>";
		if(!dataOver){
			$.getJSON("http://localhost:8080/HpuFood/discuss/foodEvaluates.do?f_id=${foodInfo.food.id}&pageNum="+pageNum,function(data){
				var ht = "";
				var appendPre = "<div style='margin-left:70%'>";
				var appendSuf = "</div>";
				var moreButton;
				var moreEvaluate;
				var show = "";
				$.each(data,function(n,item){
					ht = ulPre+item.user.name +" <div style='color: #9AC0CD;display:inline'>说：</div><br><br>&nbsp;&nbsp;&nbsp;&nbsp;" + item.describe + "<div style='margin-top:2%;margin-left:3%;padding-bottom:1%;color:#838B8B'>"+ item.score+"&nbsp;&nbsp;"+formatDate(item.publishTime) +"</div>"; 
					moreButton = "<textarea id='area"+item.publishTime+"'  style='height:90px;width:500px;margin-left:60%;display:none'></textarea><button id='replay"+item.publishTime+"' onclick='callFood(this.id,\""+item.user.id+"\",\""+item.id+"\")' style='margin-left:60%'>回复</button>";
					// 写到这儿了
					moreEvaluate =  "<button id='showMore"+item.publishTime+"' onclick=\"getChatList('"+item.id+"','"+item.publishTime+"')\" style='margin-left:30px;display:inline'>显示更多回复</button><div id='showMoreReplay"+item.publishTime+"' style='display:none'></div>"+ ulSuf;
					show = show + ht+moreButton+moreEvaluate;
					if(n==8)
						pageNum=pageNum+1;
					else{
						dataOver = true;
						$("#showMore").attr("disabled",true);
						$("#showMore").text("没有更多数据了");
					}
				});
				$("#show_evaluate_info").prepend(show);
			});
		}
	}
	$("#collect").click(function(){
		$.getJSON("http://localhost:8080/HpuFood/discuss/foodCollect.do?f_id=${foodInfo.food.id}",function(data){
			alert(data.message);
			if(data.message == "收藏成功,请在个人中心查看"){
				$("#collect").text("已收藏");
				$("#collect").attr("disabled", true)
			}
		});
	});
	function collect(){
		$.getJSON("http://localhost:8080/HpuFood/discuss/isCollectFood.do?f_id=${foodInfo.food.id}",function(data){
			$("#collect").text(data.message);
			if(data.message == "已收藏")
				$("#collect").attr("disabled", true)
		});
	}
	collect();
});

</script>
</head>
<body style="background-color: #FFFFFF">
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
	<div id="showBgImg" >
	<div class="slideShow" >
		<!--图片布局开始-->
		<ul>
			<c:forEach items="${foodPicturesList }" var="pictures" varStatus="status">
				
				<c:choose>
					<c:when test="${status.first }">
						<li><img src="${pictures.url}"
							style="width: 100%; height: 100%" class="${pictures.id }"/></li>
					</c:when>
					<c:otherwise>
						<li><img src="${pictures.url}"
					style="width: 100%; height: 100%;display:none" class="${pictures.id }"/></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<!--图片布局结束-->

		<!--按钮布局开始-->
		<div class="showNav">
			<c:forEach items="${foodPicturesList }" var="pictures" varStatus="status">
				<c:choose>
					<c:when test="${status.first }">
						<span class="active" id="${pictures.id }"></span>
					</c:when>
					<c:otherwise>
						<span id="${pictures.id }"></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<!--按钮布局结束-->
	</div>
	<div id="content" class="floatShow" style="width:50%; display: inline;border: 1px solid #C7C7C7">
		<div style="background-image: url(/HpuFood/images/fdbg.jpg)">
		<table style="width:50%;margin:10px 10%;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr>
				<td>食物名称：</td><td>${foodInfo.food.name }</td><td>分数：</td><td>${foodInfo.score }</td>
			</tr>
			<tr>
				<td>评价人数：</td><td>${foodInfo.numberOfPeople }</td><td>价格：</td><td>${foodInfo.food.price }</td>
			</tr>
			<tr>
				<td>店铺：</td><td><a href="http://localhost:8080/HpuFood/store/homepage.do?storeId=${foodInfo.food.store.id }">${foodInfo.food.store.name }</a></td>
			</tr>
			<tr>
				<td>介绍：</td><td>${foodInfo.food.introduce }</td>
			</tr>
		</table>
		<div class="btn-group" role="group" aria-label="..." style="margin-left:50%">
			<button type="button" class="btn btn-default"></button>
			<button type="button" class="btn btn-default" id="collect"></button>
			<button type="button" class="btn btn-default"></button>
		</div>
		<br><br>
		<c:if test="${empty admin }">
				<form id="evaluateFood" action="/HpuFood/discuss/evaluateFood.do" method="post">
					<table style="width:40%;margin:30px auto;border-collapse:separate; border-spacing:0px 20px;border-top: 1px #CCCCCC solid;"  >
						<tr>
							<td><span>评分  </span></td>
							<td><input name="score" type="text" value=""></td>
						</tr>
						<tr>
							<td><span>评语  </span></td>
							<td><input name="describe" type="text" value=""></td>
						</tr>
						<tr>
							<td><span><input name="f_id" type="hidden" value="${foodInfo.food.id}">  </span></td>
							<td align="right"><input value="评价" type="submit"></td>
						</tr>
					</table>
				</form>
			</c:if>
		</div>
		<div style="width:100%; border: 1px solid #C7C7C7">
		<h2 style="color:#663366"> 评论列表 </h2>
			<hr/>
			<ul id="show_evaluate_info">
				<li style="width:80%;margin:20px auto;text-align:center">
						<button id="showMore">加载更多</button>
				</li>
			</ul>
		</div>
		<!-- <div id="background" style="position:absolute;z-index:-1;width:100%;height:100%;top:0px;left:0px;"><img src="/HpuFood/images/foodBackground.png" width="100%" height="100%"/></div>	 -->
	</div>
	</div>
</body>
</html>