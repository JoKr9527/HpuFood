<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商家首页</title>

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
	var foodShow = true;
	var coverShow = true;
	
	function callStore(id,s_id,r_id){
		
		
		var value = $("#area"+id.substring(6,19)).val();
		if($("#area"+id.substring(6,19)).is(":hidden")){
			$("#area"+id.substring(6,19)).show();
		}else{
			var postData = {
					"to_id":s_id,
					 "content":value,
					"relation_id":r_id
			}
			if($("#area"+id.substring(6,19)).val()!=""){
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
			}else
				alert("请输入内容");
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

	function formatDate(misec){
		var time = new Date(misec);
		return time.getFullYear()+"年 "+time.getMonth()+"月 "+time.getDate()+"日 "+time.getHours()+" ："+time.getMinutes();
	}
	
	$(function(){
		$("#info").click(function(){
			
	    	$("#store_info").toggle();
	    	$("#show_info").show();
	    	$("#update_form").hide();
	    	$("#update_store").show();
	    	$("#store_food").hide();
	    	$("#food_update").hide();
	    	$("#cover_upload").hide();
	    	if(infoShow){
		    	$.getJSON("http://localhost:8080/HpuFood/store/info.do?s_id=${storeId}",function(data){
		    		$("#store_name").append(data.store.restaurant.name+" &nbsp;&nbsp; | &nbsp;&nbsp; "+data.store.name+"");
					var html = prefix+"<td>地址：</td>"+"<td>"+data.address+"</td>"+suffix;				
					html = html+prefix+"<td>评分：</td>"+"<td>"+data.getscore+"</td>"+suffix;				
					html = html+prefix+"<td>评价人数：</td>"+"<td>"+data.numberOfPeople+"</td>"+suffix;			
					html = html+prefix+"<td>主营：</td>"+"<td>"+data.themain+"</td>"+suffix;
					$("#show_info").append(html);
				});
		    	var picturesHtml ;
		    	$.getJSON("http://localhost:8080/HpuFood/store/pictures.do?s_id=${storeId}",function(data){
		    		var url = "http://localhost:8080/HpuFood/store/delPicture.do?sp_id=";
					$.each(data,function(n,item){
						url = url+item.id;
						if("${admin}" != "")
							picturesHtml = picturesHtml+"<td><img src='"+item.url+"'  height='120px' width='160px'><a href='"+url+"'>删除</a></td>";	
						else 
							picturesHtml = picturesHtml+"<td><img src='"+item.url+"'  height='120px' width='160px'></td>";	
					});
					$("#show_info").prepend(prefix+picturesHtml+suffix);
				});
		    	showEvaluateList();
	    	}
	    	infoShow = false;
		});
		$("#update_store").click(function(){
	   	 	$("#show_info").hide();
	   	 	$("#update_store").hide();
	   	 	$("#update_form").show();
		});
		$("#food").click(function(){
	   	 	$("#store_food").toggle();
	   	 	$("#store_info").hide();
	    	$("#food_update").hide();
	    	$("#cover_upload").hide();
	   	 	if(foodShow){
		   	 	$.getJSON("http://localhost:8080/HpuFood/store/showAllFood.do?s_id=${storeId}",function(data){
		    		var html ;
		    		var url = "http://localhost:8080/HpuFood/store/delFood.do?f_id=";
					$.each(data,function(n,item){
						if("${admin}" != "")
							html = html+prefix+"<td>"+"<a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.id+"'>"+item.name+"</a>"+"</td><td>" + item.introduce+"</td><td>"+item.price+"</td>"
									+"<td><a href='"+url+item.id+"'>删除</a></td><td><a href='http://localhost:8080/HpuFood/store/goUpdateFood.do?f_id="+item.id+"'>修改</a>"+suffix;
						else
							html = html+prefix+"<td><a href='http://localhost:8080/HpuFood/store/showFood.do?f_id="+item.id+"'>"+item.name+"</a>"+"</td><td>" + item.introduce+"</td><td>"+item.price+" 元</td>"+suffix;
					});
					$("#store_food_info").append(html);
				});
		   	 	foodShow = false;
	   	 	}
		});
		$("#update").click(function(){
	   	 	$("#food_update").toggle();
	   	 	$("#store_food").hide();
	   	 	$("#store_info").hide();
	    	$("#cover_upload").hide();
		});
		$("#cover").click(function(){
	   	 	$("#cover_upload").toggle();
	   	 	$("#store_info").hide();
	    	$("#store_food").hide();
	    	$("#food_update").hide();
	    	var picturesHtml ;
	    	if(coverShow){
		    	$.getJSON("http://localhost:8080/HpuFood/store/pictures.do?s_id=${storeId}",function(data){
		    		var url = "http://localhost:8080/HpuFood/store/delPicture.do?sp_id=";
					$.each(data,function(n,item){
						var nurl = url+item.id;
						picturesHtml = picturesHtml+"<td><img src='"+item.url+"'  height='120px' width='160px' style='display:block'><a href='"+nurl+"'>删除</a></td>";
					});
					$("#cover_show").prepend(prefix+picturesHtml+suffix);
				});
		    	coverShow = false;
	    	}
		});

		
		var pageNum = 0;
		var dataOver = false;
		$("#showMore").click(function(){
			if(!dataOver)
				showEvaluateList();
		});
		function showEvaluateList(){
			
			var ulPre = "<li style='width:80%;margin:30px auto;text-align:left;border-left: 1px #1C86EE groove'><div style='margin-left:10px;' >";
			var ulSuf = "</div></li>";
			if(!dataOver){
				$.getJSON("http://localhost:8080/HpuFood/discuss/storeEvaluates.do?store_id=${storeId}&pageNum="+pageNum,function(data){
					var ht = "";
					var appendPre = "<div style='margin-left:70%'>";
					var appendSuf = "</div>";
					var moreButton;
					var moreEvaluate;
					var show = "";
					$.each(data,function(n,item){
						ht = ulPre+item.user.name +" <div style='color: #9AC0CD;display:inline'>说：</div><br><br>&nbsp;&nbsp;&nbsp;&nbsp;" + item.describe + "<div style='margin-top:2%;margin-left:3%;padding-bottom:1%;color:#838B8B'>"+ item.score+"&nbsp;&nbsp;"+formatDate(item.publishTime) +"</div>"; 
						moreButton = "<textarea id='area"+item.publishTime+"'  style='height:90px;width:500px;margin-left:60%;display:none'></textarea><button id='replay"+item.publishTime+"' onclick='callStore(this.id,\""+item.storeInfo.store.id+"\",\""+item.id+"\")' style='margin-left:60%'>回复</button>";
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
			$.getJSON("http://localhost:8080/HpuFood/discuss/storeCollect.do?s_id=${storeId}",function(data){
				alert(data.message);
				if(data.message == "收藏成功,请在个人中心查看"){
					$("#collect").text("已收藏");
					$("#collect").attr("disabled", true)
				}
			});
		});
		function collect(){
			$.getJSON("http://localhost:8080/HpuFood/discuss/isCollectStore.do?s_id=${storeId}",function(data){
				$("#collect").text(data.message);
				if(data.message == "已收藏")
					$("#collect").attr("disabled", true)
			});
		}
		collect();
		$("#info").click();
	});
</script>
</head>

<body >
	<header class="cd-auto-hide-header" style="background-color: #EDEDED">
		<div class="logo">
			<a href="#0">hpu 美食</a>
		</div>
		<nav class="cd-primary-nav">
			<ul class="cd-navigation">
				<li><a id="info" href="javascript:void(0);" >店铺简介</a></li>		
			</ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<ul class="cd-navigation">
				<li><a id="food" href="javascript:void(0);" >店铺美食</a></li>		
			</ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${not empty admin }">
				<ul class="cd-navigation">
					<li><a id="update" href="javascript:void(0);" >食品上传</a></li>		
				</ul>
			</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${not empty admin }">
				<ul class="cd-navigation">
					<li><a id="cover" href="javascript:void(0);" >封面上传</a></li>		
				</ul>
			</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${ empty admin }">
				<ul class="cd-navigation">
					<li>
						<div class="btn-group" role="group" aria-label="..."
							>
							<button type="button" class="btn btn-default"></button>
							<button type="button" class="btn btn-default" id="collect"></button>
							<button type="button" class="btn btn-default"></button>
						</div>
					</li>
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
	<div id="store_info" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset>
			<legend id="store_name"></legend>
			<table id="show_info" style="width:60%;margin:0 auto;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr>
				<td></td>
			</tr>	
			</table>
			
			<c:if test="${not empty admin }">
				<form id="update_form" action="/HpuFood/store/update.do" method="post" style="display:none">
					<table style="width:80%;margin:30px auto;border-collapse:separate; border-spacing:0px 40px;"  >
						<tr>
							<td><span>餐厅名/校外  </span></td>
							<td><input name="restaurantName" type="text" value="${restaurant.name}"></td>
						</tr>
						<tr>
							<td><span>商家名称  </span></td>
							<td><input name="storeName" type="text" value="${store.name }"></td>
						</tr>
						<tr>
							<td><span>主营  </span></td>
							<td><input name="themain" type="text" height="5px" value="${storeInfo.themain }"></td>
						</tr>
						<tr>
							<td><span>地址  </span></td>
							<td><input name="address" type="text" value="${storeInfo.address }"></td>
						</tr>
						<tr>
							<td><span>  </span></td>
							<td align="right"><input value="确认修改" type="submit"></td>
						</tr>
					</table>
				</form>
				<div><button id="update_store">修改</button></div>
			</c:if>
			<c:if test="${empty admin }">
				<form id="evaluateStore" action="/HpuFood/discuss/evaluateStore.do" method="post">
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
							<td><span><input name="storeId" type="hidden" value="${storeId}">  </span></td>
							<td align="right"><input value="评价" type="submit"></td>
						</tr>
					</table>
				</form>
			</c:if>
			<br></br>
			<h2 style="color:red"> 评论列表 </h2>
			<hr/>
			<ul id="show_evaluate_info">
				<li style="width:80%;margin:20px auto;text-align:center">
						<button id="showMore">加载更多</button>
				</li>
			</ul>
		</fieldset>
	</div>
	<div id="store_food" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset>
			<legend> 店铺美食 </legend>
			<table id="store_food_info" style="width:60%;margin:0 auto;text-align:center;border-collapse:separate; border-spacing:0px 40px;">
			<tr style="letter-spacing: 10px;font-weight:bold;">
				<td>美食名称</td><td>简介</td><td>价格</td>
			</tr>	
			</table>
		</fieldset>
	</div>
	<div id="food_update" style="display:none;width:80%;margin:0 auto;text-align:center">
		<fieldset style="width:40%;margin:0 auto;margin-top:10%;text-align:center">
		<legend> 上传食品到店铺 </legend>
		<form action="/HpuFood/store/food.do" method="post" enctype="multipart/form-data">
			<table style="width:80%;margin:30px auto;border-collapse:separate; border-spacing:0px 40px;">
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
	</div>
	<div id="cover_upload" style="display:none">
		<fieldset style="width:40%;margin:0 auto;margin-top:10%;text-align:center">
		<legend> 店铺封面 </legend>
		<strong> 店铺已有封面：</strong>
	
		
		<form action="/HpuFood/store/images.do" method="post" enctype="multipart/form-data">
			<table id="cover_show" style="width:80%;margin:30px auto;border-collapse:separate; border-spacing:0px 40px;">
				<tr>
					<td>封面照片：</td>
					<td><input name="file" type="file"></td>
				</tr>
				<tr>
					<td></td>
					<td><input value="上传" type="submit"></td>
				</tr>
			</table>	
		</form>
	</fieldset>
	</div>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>