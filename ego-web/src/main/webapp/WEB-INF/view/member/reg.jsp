<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="${resources}/css/userLogin.css">
<link rel="stylesheet" type="text/css" href="${resources}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${resources}/css/reg.css">
<script type="text/javascript" src="${resources}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${resources}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${resources}/js/reg.js"></script>
<!--<script type="text/javascript">
 jQuery(function(){
	jQuery("#yanzheng").click(function (){
		alert(1)
	})
})
</script> -->
</head>
<body>
	<center>
		<!--头部-->
		<div class="header">
			<a class="logo" href="#"></a>
			<div class="desc">欢迎注册</div>
			<div class="fanhui">
				<a href="#">返回首页</a>
			</div>
			<div class="reg">
				<a href="${root}/member/gologinView">已有账号?请登陆</a>
			</div>
			<hr />
		</div>
		<div class="container">
			<form action="${root}/member/useradd" method="post"
				submit="return false">
				<div class="register">
					<!--用户名-->
					<div class="register-box1">
						<div class="box default">
							<label for="userName">登 陆 名 称 </label> <input type="text"
								id="userLoginname" name="loginname" placeholder="您的账户名和登录名" />
							<i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<!--设置密码-->
					<div class="register-box1">
						<div class="box default">
							<label for="pwd">设 置 密 码 </label> <input type="password"
								id="userPassword" name="loginpwd" placeholder="建议至少两种字符组合" /> <i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<!--确认密码-->
					<div class="register-box1">
						<div class="box default">
							<label for="pwd2">确 认 密 码 </label> <input type="password"
								id="pwd2" placeholder="请再次输入密码" /> <i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<!--真实姓名-->
					<!-- <div class="register-box1">
					<div class="box default">
						<label for="rightName">真 实 姓 名 </label> <input type="text"
							id="userName" name="userName" placeholder="请输入真实姓名" /> <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div> -->
					<!--性别-->
					<!-- <div class="register-box1">
					<div class="box default">
						<label for="sex">性 &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;别</label>
						男<input class="sex" type="radio" name="sex" value="0" checked="checked" />
						女<input class="sex" type="radio" name="sex" value="1"/>

					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div> -->
					<!-- 邮箱 -->
					<div class="register-box1">
						<div class="box default">
							<label for="email">邮 箱 信 息 </label> <input type="text"
								id="userMail" name="userMail" placeholder="请输入邮箱" /> <i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<!--手机-->
					<div class="register-box1">
						<div class="box default">
							<label for="mobile">手机号信息</label> <input type="text"
								id="userPhone" name="userPhone" placeholder="输入您的手机号" /> <i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<!--验证码-->
					<div class="register-box1 ">
						<div class="box default">
							<label for="vCode">验证码</label> <input type="text" name="vCode"
								id="vCode" placeholder="输入验证码" /> <input id="yanzheng"
								type="button" disabled="true" value="免费获取验证码"> <i></i>
						</div>
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<%-- <!--验证码-->
				<div class="register-box1 ">
					<div class="box default">
						<label for="vCode">验证码</label> 
						<input type="text" name="vCode" id="vCode" placeholder="输入验证码" />							
						<img  style="width:100px;height: 30px;text-align: center; " tilte="点击图片换一张"  src="${root }/CaptServlet"  onclick="this.src=this.src+'?'">
					    <i></i>
					</div>
					<div class="tip">
						<i></i> <span></span>
					</div>
				</div> --%>
					<!--地址-->
					<%-- <div class="register-box1 ">
					<div class="box default">
					<label>地址信息:</label>
					<select id="sheng" style="margin-top: 20px" name="province" >
				 			<option value="0">请选择省</option>
				 			<c:forEach items="${listProvince}" var="she">
				 				<option  value="${she.name}|${she.code}">${she.name}</option>
				 			</c:forEach>
				 	</select>
				 	&nbsp;&nbsp;
				   	<select id="shi" style="margin-top: 20px" name="city">
				   		<option>请选择市</option>
					</select>
					&nbsp;&nbsp;
				   	<select id="qu" style="margin-top: 20px" name="area">
				   		<option>请选择区</option>
				   	</select>
				</div>
					<div class="tip">
						<i></i> <span></span>
					</div> 
				</div> --%>

					<!--注册协议-->
					<div class="register-box1 xieyi">
						<!--表单项-->
						<div class="box default">
							<input type="checkbox" id="ck" /> <span>我已阅读并同意 <a
								href="javascript:void(0)" data-toggle="modal"
								data-target="#myModal">《EGO用户注册协议》</a>
							</span>
						</div>
						<!--提示信息-->
						<div class="tip">
							<i></i> <span></span>
						</div>
					</div>
					<input type="button" id="btn" value="立即注册" />
				</div>
			</form>

			<!-- 模态框 -->
			<div class="modal fade" aria-hidden="true" data-backdrop="static"
				id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 1000px;">
					<div class="modal-content" style="width: 1000px;">
						<div class="modal-header"
							style="width: 1000px; position: relative; float: left;">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h2 class="modal-title" id="myModalLabel"
								style="text-align: center;">《EGO用户注册协议》</h2>
						</div>
						<div class="modal-body"
							style="width: 1000px; position: relative; float: left;">
							<div style="height: 800px; width: 900px; text-align: left;">
								<span style="color: gray; text-align: left;"> 一、总则 <br>1.1
									保宝网的所有权和运营权归深圳市永兴元科技有限公司所有。 <br>1.2
									用户在注册之前，应当仔细阅读本协议，并同意遵守本协议后方可成为注册用户。一旦注册成功，则用户与保宝网之间自动形成协议关系，用户应当受本协议的约束。用户在使用特殊的服务或产品时，应当同意接受相关协议后方能使用。
									<br>二、服务内容 <br>2.1 保宝网的具体内容由本站根据实际情况提供。 <br>2.2
									本站仅提供相关的网络服务，除此之外与相关网络服务有关的设备(如个人电脑、手机、及其他与接入互联网或移动网有关的装置)及所需的费用(如为接入互联网而支付的电话费及上网费、为使用移动网而支付的手机费)均应由用户自行负担。
									<br>三、用户帐号 <br>3.1
									经本站注册系统完成注册程序并通过身份认证的用户即成为正式用户，可以获得本站规定用户所应享有的一切权限；未经认证仅享有本站规定的部分会员权限。保宝网有权对会员的权限设计进行变更。
									<br>3.2
									用户只能按照注册要求使用真实姓名，及身份证号注册。用户有义务保证密码和帐号的安全，用户利用该密码和帐号所进行的一切活动引起的任何损失或损害，由用户自行承担全部责任，本站不承担任何责任。如用户发现帐号遭到未授权的使用或发生其他任何安全问题，应立即修改帐号密码并妥善保管，如有必要，请通知本站。因黑客行为或用户的保管疏忽导致帐号非法使用，本站不承担任何责任。
									<br>四、使用规则 <br>4.1
									遵守中华人民共和国相关法律法规，包括但不限于《中华人民共和国计算机信息系统安全保护条例》、《计算机软件保护条例》、《最高人民法院关于审理涉及计算机网络著作权纠纷案件适用法律若干问题的解释(法释[2004]1号)》、《全国人大常委会关于维护互联网安全的决定》、《互联网电子公告服务管理规定》、《互联网新闻信息服务管理规定》、《互联网著作权行政保护办法》和《信息网络传播权保护条例》等有关计算机互联网规定和知识产权的法律和法规、实施办法。
									<br>4.2
									用户对其自行发表、上传或传送的内容负全部责任，所有用户不得在本站任何页面发布、转载、传送含有下列内容之一的信息，否则本站有权自行处理并不通知用户：
									<br>五、隐私保护 <br>5.1
									本站不对外公开或向第三方提供单个用户的注册资料及用户在使用网络服务时存储在本站的非公开内容，但下列情况除外： <br>
									(1)事先获得用户的明确授权； <br>(2)根据有关的法律法规要求； <br>(3)按照相关政府主管部门的要求；
									<br>六、版权声明 <br>6.1
									本站的文字、图片、音频、视频等版权均归永兴元科技有限公司享有或与作者共同享有，未经本站许可，不得任意转载。 <br>6.2
									本站特有的标识、版面设计、编排方式等版权均属永兴元科技有限公司享有，未经本站许可，不得任意复制或转载。 <br>6.3
									使用本站的任何内容均应注明“来源于保宝网”及署上作者姓名，按法律规定需要支付稿酬的，应当通知本站及作者及支付稿酬，并独立承担一切法律责任。
									<br>七、责任声明 <br>7.1
									用户明确同意其使用本站网络服务所存在的风险及一切后果将完全由用户本人承担，保宝网对此不承担任何责任。 <br>7.2
									本站无法保证网络服务一定能满足用户的要求，也不保证网络服务的及时性、安全性、准确性。 <br>7.3
									本站不保证为方便用户而设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由本站实际控制的任何网页上的内容，本站不承担任何责任。
									<br>八、附则 <br>8.1 本协议的订立、执行和解释及争议的解决均应适用中华人民共和国法律。 <br>8.2
									如本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，本协议的其余条款仍应有效并且有约束力。
								</span>
							</div>
						</div>
						<div class="modal-footer" align="center">
							<button style="margin-right: 80px;" type="button"
								class="btn btn-primary" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
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
			<div class="copyright">
				Copyright &copy; 2004-2017 EGO 版权所有
			</div>
		</div>
	</center>
</body>
</html>

