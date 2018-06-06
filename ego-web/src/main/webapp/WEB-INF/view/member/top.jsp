<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <style>
		* {
			margin: 0px auto;
			padding: 0px;
		}

		ul {
			/*取消序号*/
			list-style: none;
		}
		a {
			/*链接下划线*/
			text-decoration: none;
		}
		/*top*/
		
		#top {
			position: relative;
			float: left;
			background-color: #E3E4E5;
			width: 1200px;
			height: 30px;
			padding-top: 5px;
		}
		
		#top a {
			color: #9999AC;
			font-size: 12px;
		}
		
		#top #top_left {
			position: relative;
			float: left;
			width: 200px;
			height: 30px;
		}
		
		#top #top_left ul {
			position: relative;
			float: left;
			width: 200px;
			height: 30px;
		}
		
		#top #top_left ul li {
			position: relative;
			float: left;
			height: 30px;
			margin-left: 6px;
		}
		
		#top #top_center {
			position: relative;
			float: left;
			height: 30px;
			width: 500px;
		}
		
		#top #top_center a {
			position: relative;
			float: right;
			padding-top: 2px;
			margin-right: 10px;
		}
		
		#top #top_center span {
			position: relative;
			float: right;
			padding-top: 1px;
			margin-right: 20px;
		}
		
		#top #top_right {
			position: relative;
			float: left;
			height: 30px;
			width: 500px;
		}
		
		#top #top_right ul {
			position: relative;
			float: left;
			width: 500px;
			height: 30px;
		}
		
		#top #top_right ul li {
			position: relative;
			float: left;
			height: 30px;
			margin-left: 10px;
		}
		
		#top a:hover {
			color: #9999AC;
			color: red;
			border: solid 1px;
		}
	</style>

	<body>
			<div id="top">
				<div id="top_left">
					<ul>
						<li><img src="${resources}/img/top/tophome.jpg" /></li>
						<li>
							<a href="${root}/member/home">Ego首页</a>
						</li>
						<li><img src="${resources}/img/top/topposition.png"></li>
						<li>
							<a href="#">位置</a>
						</li>
					</ul>
				</div>
				<div id="top_center">
					<c:if test="${not empty user}">
						<a href="${root}/member/outlogin">退出</a>
						<span>欢迎[${user.loginname}]进入商城</span>
					</c:if>
					<c:if test="${empty user}">
						<a href="${root}/member/gologinView">你好,请登录</a>&nbsp;
						<a href="${root}/member/goreg">免费注册</a><br>
					</c:if>
				</div>
				<div id="top_right">
					<ul>
						<li>
							<a href="${root}/user/order/listMyOrder">我的订单</a>
						</li>
						<li>
							<a href="${root}/user/person/golist?loginname=${user.loginname}">我的Ego</a>
						</li>
						<li>
							<a href="${root}/user/gomyCollect">收藏夹</a>
						</li>
						<li>
							<a href="${root}/user/liuLan/findLiuLan">我的足迹</a>
						</li>
						<li>
							<a href="${root}/user/cart/gomyCart">我的购物车</a>
						</li>
						<li>
							<a href="#">网站导航</a>
						</li>
						<li>
							<a href="#">手机Ego</a>
						</li>
					</ul>
				</div>
			</div>
		
	</body>
</html>
