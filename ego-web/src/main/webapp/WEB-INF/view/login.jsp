<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${resources}/js/jquery.js"></script>
<script type="text/javascript" src="${resources}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${resources}/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${resources}/css/userLogin.css">
<style type="text/css">
b {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	width: 18px;
	height: 14px;
	margin: 0 5px;
	background: url(${pageContext.request.contextPath}/img/q-icon.png)
		no-repeat;
	overflow: hidden;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#zhanghudenglu").click(function(){
			$(this).css("color","#f00");   
			$("#shoujidenglu").css("color","#666");   
			$(".form2").css("z-index","9");
			$(".form2 .item-fore2").css("z-index","9");
			$(".form1").css("z-index","7");
			$(".form1 .item-fore1").css("z-index","7");
			$("#spanerr1").empty();
			$("#spanerr2").empty();
			$("#phone").val("");
			$("#yanzhengma").val("");
		});
		
		$("#shoujidenglu").click(function(){
			var phone=$("#phone").val;
			$(this).css("color","#f00");    
			$("#zhanghudenglu").css("color","#666");    
			$(".form1").css("z-index","9");
			$(".form1 .item-fore1").css("z-index","9");
			$(".form2").css("z-index","7");
			$(".form2 .item-fore2").css("z-index","7");
			$("#spanerr1").empty();
			$("#spanerr2").empty();
			$("#loginname").val("");
			$("#loginpwd").val("");
		});
		//账户登录
		$("#but1").click(function(){
			var	loginname=$("#loginname").val();
			var loginpwd=$("#loginpwd").val();
			if(loginname!="" && loginpwd!=""){
				var url="/ego-web/member/login";
				$.post(url,{"loginname":loginname,"loginpwd":loginpwd},function(data){
					if(data.code==0){
						window.location.href="/ego-web/member/home";
						return ;
					}else if(data.code==1){
						$("#spanerr2").empty();
						$("#spanerr1").empty();
						$("#spanerr1").append("&nbsp;&nbsp;&nbsp;登录名或密码错误！");
						return ;      
					}else if(data.code==2){
						window.location.href="/ego-web/member/goXiangqing?id="+data.id;
						return ;
					}
				},'json');
				return true;
			}else{
				$("#spanerr1").empty();
				$("#spanerr1").append("&nbsp;&nbsp;&nbsp;账户或密码为空！");
				return false;
			}
		});
		//  验证码
		$("#but2").click(function(){
			var phone=$("#phone").val();
			var yanzhengma=$("#yanzhengma").val();
			if(phone!="" && yanzhengma!=""){
// 				document.getElementById("but2").setAttribute("type", "submit");
				var url="/ego-web/member/login";
				$.post(url,{"phone":phone,"yanzhengma":yanzhengma},function(data){
					if(data.code==0){
						window.location.href="/ego-web/member/home";
						return ;
					}else if(data.code==1){
						$("#spanerr1").empty();
						$("#spanerr1").empty();
						$("#spanerr2").append("&nbsp;&nbsp;&nbsp;登录名或密码错误！");
						return ;      
					}else if(data.code==2){
						window.location.href="/ego-web/member/goXiangqing?id="+data.id;
						return ;
					}
				},'json');
				return true;
			}else{
				$("#spanerr2").empty();
				$("#spanerr2").append("&nbsp;&nbsp;&nbsp;手机号或验证码为空！");
				return false;
			}
		});
		
		$("#phone").on({blur:function(){	
			var regs1 = /^1([3|5|8][0-9]{9})$/;
			var regs2 = /^1(7[0|6|7|8][0-9]{8})$/;
			var value=$(this).val().trim();
			if(regs1.test(value) || regs2.test(value)){
				$("#spanerr2").empty();
				$("#yanzheng").removeAttr("disabled"); 
				var url="/ego-web/member/checkLoginPhone?phone="+ value;
				$.post(url,function(data){
					if(data==1){
						$("#spanerr2").empty();
						$("#yanzheng").removeAttr("disabled"); 
						return true;
					}else{
						$("#yanzheng").attr("disabled",true); 
						$("#spanerr2").empty();
						$("#spanerr2").append("&nbsp;&nbsp;&nbsp;手机号未注册，请前去注册！");
						return false;
					}
				},'json');
			}else{
				$("#yanzheng").attr("disabled",true); 
				$("#spanerr2").empty();
				$("#spanerr2").append("&nbsp;&nbsp;&nbsp;手机号错误！");
				return false;
			}
			},keyup:function(){
				var regs1 = /^1([3|5|8][0-9]{9})$/;
				var regs2 = /^1(7[0|6|7|8][0-9]{8})$/;
				var value=$(this).val().trim();
				if(regs1.test(value) || regs2.test(value)){
					var url="/ego-web/member/checkLoginPhone?phone="+ value;
					$.post(url,function(data){
					if(data==1){
						$("#spanerr2").empty();
						$("#yanzheng").removeAttr("disabled"); 
						return true;
					}else{
						$("#yanzheng").attr("disabled",true); 
						$("#spanerr2").empty();
						$("#spanerr2").append("&nbsp;&nbsp;&nbsp;手机号未注册，请前去注册！");
						return false;
					}
				},'json');
					return true;
				}else{
					$("#yanzheng").attr("disabled",true); 
					$("#spanerr2").empty();
					$("#spanerr2").append("&nbsp;&nbsp;&nbsp;手机号格式不正确！");
					return false;
				}
			} 
		});
		
		var wait = 60;
		$("#yanzheng").on("click",function (){
			if(wait==60){
				sendMessage();
				time(this);
			}
		});
		
		function sendMessage() {
			var request = new XMLHttpRequest();
			var method = "POST";
			phone=document.getElementById("phone").value;
// 			var url = "/ego-web/member/userRegYanZheng?phone="+ phone;
			var url = "/ego-web/member/userRegYanZheng";
			$.post(url,{"phone":phone},function(data){
				if(data==1){
					$("#spanerr2").empty();
					$("#spanerr2").append("&nbsp;&nbsp;&nbsp;验证码发送失败，请重试！");
				}
			},'json');
		}  
		function time(o) {
			if(wait == 0) {
				$("#yanzheng").removeAttr("disabled"); 
				$("#yanzheng").val("免费获取验证码");
				wait = 60;
			} else {
				$("#yanzheng").attr("disabled",true); 
				yanzheng.value = "重新发送(" + wait + ")";
				wait--;
				setTimeout(function() {
						time(o)
					},1000)
			}
		} 
	})

</script>
</head>

<body>
	<div class="w">
		<!-- 头部，logo部分 -->
		<div class="logo">
			<img alt="易购" src="${resources}/img/logo.png" width="140px"
				height="70px"></img> <font size="5px" class="welcome-login">欢迎登录</font>
		</div>
		<a class="questions" onmouseover="color:red;" target="_blank" href="#"><b></b>跳转到首页</a>
	</div>
	<!-- body -->
	<div id="content">
		<div class=login-wrap>
			<div class="w">
				<!-- 登录框 -->
				<div class="login-form">
					<div class="login-tab login-tab-l">
						<a class="ss" id="shoujidenglu" href="javascript:void(0)" style="color: #f00">账户登录</a>
					</div>
					<div class="login-tab login-tab-r">   <!-- style="color: #f00" -->
						<a class="ss" id="zhanghudenglu" href="javascript:void(0)" style="color: #666">手机登录</a>
					</div>
					<!-- 主体 -->
					<div class="login-box1">
						<div class="mt tab-h"></div>
						<div class="msg-wrap">
							<div class="msg-warn">
								<label>公共场所不建议自动登录，以防账号丢失</label>
							</div>
						</div>
						<!-- 输入框 -->
						<div class="mc">
							<div class="form1">
<%-- 								<form id="formlogin"action="${pageContext.request.contextPath}/member/login"method="post"> --%>
									<div class="item item-fore1">
										<label for="loginanme" class="login-label name-label ">账号</label>
										<input class="itxt" type="text" id="loginname" name="loginname" value="${user.loginname}" placeholder="输入用户名">
									</div>
									<div class="item item-fore2">
										<label for="loginanme" class="login-label name-label">密码</label>
										<input type="password" class="itxt" id="loginpwd" name="loginpwd" placeholder="请输入密码">
									</div>
									<span id="spanerr1" style="color: red">${loginErr}</span>
									<div class="item item-fore4">
										<div class="safe">
											<input class="jdcheckbox" type="checkbox" name="remember">自动登录
											<a href="${root}/member/goreg" class="forgetPassword" style="text-align: right;margin-right: 35px;">立即注册</a>
										</div>
									</div>
									<!-- 登录按钮 -->
									<div class="item item-fore5">
										<div class="login-btn">
											<input id="but1" class="but" name="" type="button" value="登    录" />
										</div>
									</div>
<!-- 								</form> -->
							</div>
							
							<div class="form2">
<%-- 								<form id="formlogin"action="${pageContext.request.contextPath}/member/login"method="post"> --%>
									<div class="item item-fore1">
										<label for="loginanme" class="login-label name-label ">手机号</label>
										<input class="itxt" type="text" name="phone" id="phone" value="${phone}" placeholder="输入手机号">
									</div>
									<div class="item item-fore2">
										<label for="loginanme" class="login-label name-label">验证码</label>
										<input type="text" class="itxt" id="yanzhengma" name="yanzhengma" value="" placeholder="请输入验证码">
										<input id="yanzheng" type="button" disabled="disabled"  value="免费获取验证码">
									</div>
									<span id="spanerr2" style="color: red">${loginErr}</span>
									<div class="item item-fore4">
										<div class="safe">
											<input class="jdcheckbox" type="checkbox" name="remember">自动登录
											<a href="${root}/member/goreg" class="forgetPassword" style="text-align: right;margin-right: 35px;">立即注册</a>
										</div>
									</div>
									<!-- 登录按钮 -->
									<div class="item item-fore5">
										<div class="login-btn">
											<input id="but2" class="but" name="" type="button" value="登    录" />
										</div>
									</div>
<!-- 								</form> -->
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="login-banner">
				<!-- style="background-color:#041422" -->
				<div class="w">
					<img class="main-img" height="480px" width="1050px"
						src="${resources}/img/login/TB.png">
					<!-- <div id="banner-bg"  class="i-inner"   
                        style="background: url(//img11.360buyimg.com/da/jfs/t3154/258/5179306513/128208/9d5b12bd/5864cf6eN542ab244.jpg) "></div> -->
				</div>
			</div>
		</div>
	</div>

	<div class="w">
		<div id="footer">
			<div class="links">
				<a rel="nofollow" target="_blank"
					href="//www.jd.com/intro/about.aspx"> 关于我们 </a> | <a rel="nofollow"
					target="_blank" href="//www.jd.com/contact/"> 联系我们 </a> | <a
					rel="nofollow" target="_blank" href="//zhaopin.jd.com/"> 人才招聘 </a>
				| <a rel="nofollow" target="_blank"
					href="//www.jd.com/contact/joinin.aspx"> 商家入驻 </a> | <a
					rel="nofollow" target="_blank"
					href="//www.jd.com/intro/service.aspx"> 广告服务 </a> | <a
					rel="nofollow" target="_blank" href="//app.jd.com/"> 手机EGO </a> | <a
					target="_blank" href="/links.vm/club.jd.com/links.aspx"> 友情链接 </a>
				| <a target="_blank" href="//media.jd.com/"> 销售联盟 </a> | <a
					href="//club.jd.com/" target="_blank"> EGO社区 </a>
			</div>
			<div class="copyright">Copyright &copy; 2004-2017 EGO 版权所有</div>
		</div>
	</div>
</body>
</html>
