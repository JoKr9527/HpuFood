<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/HpuFood/css/header.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HpuFood/js/jquery-3.3.1.js"></script>
<script>

$(function(){
	$("#login").click(function(){
	    $("#loginChoice").toggle();
	});
	$("#register").click(function(){
	    $("#registerChoice").toggle();
	});
});

</script>
<title> hpu 美食 </title>
</head>
<body style="font-family: Georgia, Times, TimesNR, 'New Century Schoolbook',
      'New York', serif;">
	<header>
		<div id="head_Content">
			<label>hpu 美食</label>
			<c:choose>
				<c:when test="${not empty admin or not empty user}">
					<nav>
						<c:if test="${not empty user }">
							<a href="/HpuFood/user/homepage.do?userId=${user.id }">${user.name} </a><span>|</span> <a href="/HpuFood/user/logout.do">注销</a>
						</c:if>
						<c:if test="${not empty admin }">
							<a href="/HpuFood/store/homepage.do?storeId=${admin.store.id}"> ${admin.name}</a><span>|</span> <a href="/HpuFood/store/logout.do">注销</a>
						</c:if>				
					</nav>
				</c:when>
				<c:otherwise>
					<nav>
				<table>
					<tr>
						<td><label id="login" style="font-weight:bold;"> 登录 </label></td>
					</tr>
					<tr>
						<td>
						<div  id="loginChoice" style="display:none">				
							<a href="/HpuFood/store/goLogin.do">商家登录 </a> <span>|</span>
							<a href="/HpuFood/user/goLogin.do">用户登录 </a>					
						</div>
						</td>
					</tr>
				</table>	
				<table>
					<tr>
						<td><label id="register" style="font-weight:bold;"> 注册 </label></td>
					</tr>
					<tr>
						<td>
							<div id="registerChoice" style="display:none">
							<a href="/HpuFood/store/goRegister.do"> 商家注册</a> <span>|</span>
							<a href="/HpuFood/user/goRegister.do">用户注册 </a> 
							</div>
						</td>
					</tr>
				</table>
			 </nav>
				</c:otherwise>
				</c:choose>
			<hr/>
		</div>
	</header>
	<div id="content" style="margin:20px auto;"></div>
	<footer></footer>
</body>
</html>