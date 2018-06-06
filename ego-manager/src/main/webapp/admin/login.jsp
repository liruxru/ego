<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<shiro:authenticated>
<%
response.sendRedirect(request.getContextPath()+"/admin/common/home");
%>
</shiro:authenticated>
<%
String loginError = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(loginError!=null){
		if(loginError.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")){
			pageContext.setAttribute("loginError", "验证码错误");
		}
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- <link rel="stylesheet" href="${root}/resources/css/pintuer.css">
    <link rel="stylesheet" href="${root}/resources/css/admin.css">
    <script src="${root}/resources/js/jquery.js"></script>
    <script src="${root}/resourcesjs/pintuer.js"></script> --%> 
    <link rel="stylesheet" type="text/css" href="${css}/adminLogin.css">
	<style type="text/css">
	html body {
				background:url(${img}/login/bg.jpg);
				width: 100%;
				height:100%;
			 }
		.field{
		    width: 420px;
			height: 60px;
			color: #fff;
			border-radius: 6px;
			background: rgba(45,45,45,0.15);
			margin-bottom: 15px;
			border: 1px solid rgba(255,255,255,0.15);
			box-shadow: 0 2px 3px rgba(0,0,0,0.1) inset;
		   /*  text-align: center; */
		    margin-left: 116px
		}
		.field input{
		    width: 310px;
			height: 46px;
			color: #fff;
			border-radius: 6px;
			background: rgba(45,45,45,0.15);
			margin-top:10px;
			border: 1px solid rgba(255,255,255,0.15);
			box-shadow: 0 2px 3px rgba(0,0,0,0.1) inset;
		    text-align: center;
		     margin-left: -7px
		}
	</style> 
<title>管理员登录</title>
</head>

<body>
<div class="box">
		<div id="logo">
			<a href="#"><img src="${img}/login/Snipaste_2017-11-23_14-11-46.jpg" /></a>
		</div>
		<div id="content_box">
			<div id="login">
				<h3 class="titile">管理员登录</h3>
				 <form action="login.jsp" method="post">
					<input id=""  class="txt" name="username" type="text" value="${adminLoginname}" placeholder="用户名" /><br />
					<input id="" class="txt" name="password" type="password" placeholder="密码" /><br />
					<!-- <div class="form-group"> -->
                        <div class="field">
                            <input type="text" class="input-big" name="code" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                           <img src="${root}/CaptServlet" alt="" width="100px" height="60px" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">                             
                        </div>
                    <!-- </div> -->
                    <span style="color: red;font-size: 12px;">${loginError}</span>
					<input id="" class="but" name="" type="submit" value="登录" /><br />
				</form>
			</div>
		</div>
	</div>
</body>
</html>