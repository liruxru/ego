<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的ego</title>
<link rel="stylesheet" href="${css}/perlist.css" /> 
<script type="text/javascript" src="${js}/jquery.js"></script>
<script type="text/javascript" src="${js}/perlist.js"></script>
</head>
<body>
	<div id="main">
		<div id="include-title">
			<jsp:include page="../../member/top.jsp"></jsp:include>
		</div>
		<div class="header">
			<div class="header-left">
				<!-- <h1 style="line-height: 80px;margin-left: 50px;color: white;">我的Ego</h1> -->
				<img alt="logo" src="${resources}/img/logo.png" width="110px" height="100px" style="margin-left: 40px"> 
			</div>
			<div class="menu">
				<ul>
					<li><a href="#">首页</a></li>
					<li><a href="#">我的购物车</a></li>
					<li><a href="#">我的订单</a></li>
				</ul>
				<div id="searchdiv">
					<input id="search" type="text" placeholder="请输入检索内容"  class="text">
					<button class="button" >搜索</button>
				</div>
			</div>
		</div>
		<div id="center">
			<div class="center-left">
				<div id="all">
					<h4 >全部功能</h4>
				</div>
				<ul>
	               	<li class="tab-item">基本信息</li>  
	               	<li class="tab-item">修改密码</li>  
	               	<li class="tab-item">评价管理</li> 
	               	<li class="tab-item">我的收藏</li> 	
	               	<li class="tab-item">退款维权</li>
                </ul>
			</div>
			<div>
				<div class="center-right" style="display: block;">
					
				</div>
				<div class="center-right" style="display: none">
					<h2>第二快</h2>
				</div>
				<div class="center-right" style="display: none">
					第三块
				</div>
				<div class="center-right" style="display: none">
					第四块
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>