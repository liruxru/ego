<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-

scalable=0">
<title>个人资料</title>
<%-- <link href="${resources}css/person/admin.css" rel="stylesheet" type="text/css"> --%>
<link href="${resources}css/person/amazeui.css" rel="stylesheet" type="text/css">
<link href="${resources}css/person/personal.css" rel="stylesheet" type="text/css">
<link href="${resources}css/person/infstyle.css" rel="stylesheet" type="text/css">
<link href="${resources}css/person/foot.css" rel="stylesheet" type="text/css">
<script src="${resources}js/jquery.min.js"></script>
<%-- <script src="${resources}js/person/amazeui.js"></script> --%>
<script type="text/javascript">
$(function(){
	
	$("#baocunxiugai").click(function(){
		$("#baocunxiugai").attr("type","submit")
	});
	
	$("#user-newLoginPwd2").blur(function(){
		var newLoginPwd=$("#user-newLoginPwd1").val().trim();
		var qnewLoginPwd=$("#user-qnewLoginPwd2").val().trim();
		if(newLoginPwd==qnewLoginPwd){
			$("#userqnewloginpwd2").empty();
			$("#userqnewloginpwd2").append("两次输入密码不一致！请重新填写！");
			$("#baocunxiugai").attr("type","button")
			return false;
		}else{
			$("#userqnewloginpwd2").empty();
			return true;
		}
	});
	
	$("#user-newLoginPwd1").on({blur:function(){
		var newLoginPwd=$("#user-newLoginPwd1").val().trim();
		var pwdYan = /^[a-zA-Z0-9_]{6,20}$/;
		if(!pwdYan.test(newLoginPwd)){
			$("#useroldloginpwd1").empty();
			$("#useroldloginpwd1").append("密码格式不正确！请重新填写！");
			$("#baocunxiugai").attr("type","button")
			return false;
		}else{
			$("#useroldloginpwd1").empty();
			return true;
		}
	},keyup:function(){
		var newLoginPwd=$("#user-newLoginPwd1").val().trim();
		var pwdYan = /^[a-zA-Z0-9_]{6,20}$/;
		if(!pwdYan.test(newLoginPwd)){
			$("#useroldloginpwd1").empty();
			$("#useroldloginpwd1").append("密码格式不正确！请重新填写！");
			$("#baocunxiugai").attr("type","button")
			return false;
		}else{
			$("#useroldloginpwd1").empty();
			return true;
		}
	}});
	
	$("#user-oldLoginPwd").blur(function(){
		var userloginname=$("#userloginname").text();
		var userloginpwd=$("#user-oldLoginPwd").val().trim();
		var url="/EGO_web/member/getUserByLoginName";
		$.post(url,{"loginname":userloginname,"loginpwd":userloginpwd},function(data){
			if(data==0){
				$("#user-newLoginPwd1").removeAttr("readonly");
				$("#user-qnewLoginPwd2").removeAttr("readonly");
				return true;
			}else{
				$("#user-newLoginPwd1").attr("readonly","readonly");      
				$("#user-qnewLoginPwd2").attr("readonly","readonly");      
				return false;
			}
		},'json');
	});
	
	document.getElementById("user-email").onkeyup=function() {
		checkuser_email();
	}
	
 	function checkuser_email(){
		var emailYan = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var mail=$("#user-email").val().trim();
		if(!emailYan.test(mail)){
			$("#usermailsmall").empty();
			$("#usermailsmall").append("邮箱格式不正确！请重新填写！");
			$("#baocunxiugai").attr("type","button")
			return false;
		}else{
			$("#usermailsmall").empty();
			return true;
		}
	}
	/* $("#user-phone").on({blur:function(){
			var phone=$("#user-phone").val().trim();
			if(phone!=){
				var url="/EGO_web/member/checkLoginPhone?phone="+ phone;
				$.post(url,function(data){
					if(data==1){
						$("#userphonesmall").empty();
						$("#userphonesmall").append("该手机号已被注册！");
						return false;
					}else{
						$("#userphonesmall").empty();
					}
				},'json');
			}
			
		},keyup:function(){
			var phone=$("#user-phone").val().trim();
			if(phone==""){
				$("#userphonesmall").empty();
				$("#userphonesmall").append("手机号不能为空！");
				return false;
			}else{
				if(mobileYan1.test(phone) || mobileYan1.test(phone)){
					$("#userphonesmall").empty();
				}else{
					$("#userphonesmall").empty();
					$("#userphonesmall").append("手机号格式错误！请重新输入！");
					return false;
				}
			}
		} });*/
	
});
</script>
</head>
<body>
	<!--头 -->
	<header> 
	<article>
		<div class="mt-logo">
			<!--顶部导航条 -->
			<div class="am-container header">
				<jsp:include page="../../member/top.jsp"></jsp:include>
			</div>
			<!--悬浮搜索框-->
			<div class="nav white">
				<div class="logoBig">
					<li>
						<img width="120px" height="80px" src="${resources}img/logo.png" />
					</li>
				</div>
				<div class="search-bar pr">
					<a name="index_none_header_sysc" href="#"></a>
					<form>
						<input id="searchInput" name="index_none_header_sysc"  type="text" placeholder="&nbsp;搜索" autocomplete="off"> 
						<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</article> 
	</header>
	<div class="nav-table">
		<div class="long-title">
			<span class="all-goods">全部分类</span>
		</div>
		<div class="nav-cont">
			<ul>
				<li class="index"><a href="${root}/member/home">首页</a></li>
				<li class="qc"><a href="javascript:void(0)">闪购</a></li>
				<li class="qc"><a href="javascript:void(0)">限时抢</a></li>
				<li class="qc"><a href="javascript:void(0)">团购</a></li>
				<li class="qc last"><a href="javascript:void(0)">大包装</a></li>
			</ul>
			<div class="nav-extra">
				<i class="am-icon-user-secret am-icon-md nav-user"></i>
				<b></b>我的福利 
				<i class="am-icon-angle-right" style="padding-left: 10px;"></i>
			</div>
		</div>
	</div>
	<b class="line"></b>
	<div class="center">
		<div class="col-main">
			<div class="main-wrap">
				<div class="user-info">
					<!--标题 -->
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">个人资料</strong> / <small>Personal&nbsp;information</small>
						</div>
					</div>
					<hr />
					<!--头像 -->
					<div class="user-infoPic">
						<div class="filePic">
							<input type="file" class="inputPic" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*"> 
							<img class="am-circle am-img-thumbnail" src="${resources}/img/person/getAvatar.do.jpg" alt="" />
						</div>
						<p class="am-form-help">头像</p>
						<div class="info-m">
							<div style="margin-top: 10px">
								<b>登录名：<i id="userloginname">${user.loginname}</i></b>
							</div>
						</div>
					</div>
					<!--个人信息 -->
					<div class="info-main">
						<form class="am-form am-form-horizontal" action="${root}/member/updateUser" method="post">
							<div class="am-form-group">
								<label for="user-name2" class="am-form-label">姓名</label>
								<div class="am-form-content">
									<input type="text" id="username" name="username" value="${user.username}" placeholder="请输入真实姓名"> <small>请填写真实姓名</small>
								</div>
							</div>
							<div class="am-form-group">
								<label class="am-form-label">性别</label>
								<div class="am-form-content sex">
									<label class="am-radio-inline"> 
										<input class="sex" type="radio" name="sex" value="0" <c:if test="${user.sex eq 0}">checked="checked"</c:if>/>男
									</label> 
									<label class="am-radio-inline"> 
										<input class="sex" type="radio" name="sex" value="1" <c:if test="${user.sex eq 1}">checked="checked"</c:if>/>女
									</label> 
									<label class="am-radio-inline"> 
										<input class="sex" type="radio" name="sex" value="2" <c:if test="${user.sex eq 2}">checked="checked"</c:if> checked="checked"/> 保密
									</label>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-phone" class="am-form-label">电话</label>
								<div class="am-form-content">
									<input id="user-phone" name="user-phone" value="${user.phone}" placeholder="请输入11位正确的手机号：例 13285414943" type="tel"><small id="userphonesmall" style="color: red;"></small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-email" class="am-form-label">电子邮件</label>
								<div class="am-form-content">
									<input id="user-email" name="user-email" value="${user.mail}"  placeholder="请输入正确的邮箱：例  sdyongcun@163.com" type="email"><small id="usermailsmall" style="color: red;"></small>
								</div>
							</div>
							<!-- <div class="am-form-group address">
								<label for="user-address" class="am-form-label">收货地址</label>
								<div class="am-form-content address">
									<a href="address.html">
										<p class="new-mu_l2cw">
											<span class="province">湖北</span>省 <span class="city">武汉</span>市
											<span class="dist">洪山</span>区 <span class="street">雄楚大道666号(中南财经政法大学)</span>
											<span class="am-icon-angle-right"></span>
										</p>
									</a>
								</div>
							</div> -->
							<div class="am-form-group">
								<label for="user-oldLoginPwd" class="am-form-label">原密码</label>
								<div class="am-form-content">
									<input id="user-oldLoginPwd"  placeholder="若不修改，请忽略此项" type="text"><small style="color: red;" id="useroldloginpwd"></small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-newLoginPwd" class="am-form-label">新密码</label>
								<div class="am-form-content">
									<input id="user-newLoginPwd1" readonly="readonly" namne="user-newLoginPwd1" value="" placeholder="若不修改，请忽略此项"  type="password"><small style="color: red;" id="usernewloginpwd1">建议使用字母、数字和下划线组合的形式,6-10个字符</small>
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-qnewLoginPwd" class="am-form-label">确认密码</label>
								<div class="am-form-content">
									<input id="user-qnewLoginPwd2" readonly="readonly" value="" placeholder="若不修改，请忽略此项" type="password"><small style="color: red;" id="usernewloginpwd2"></small>
								</div>
							</div>
							<!-- <div class="am-form-group safety">
								<label for="user-safety" class="am-form-label">账号安全</label>
								<div class="am-form-content safety">
									<a href="safety.html"> 
										<span class="am-icon-angle-right"></span>
									</a>
								</div>
							</div> -->
							<div class="info-btn">
								<div class="am-btn am-btn-danger" >
									<input id="baocunxiugai" style="background-color: #DD514C; border: 0px;" type="button" value="保存修改" >    
								</div> 
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--底部-->
			<div id="footer">
			<div class="links">
				<a rel="nofollow" target="_blank" href="//www.jd.com/intro/about.aspx"> 关于我们 </a> | 
				<a rel="nofollow" target="_blank" href="//www.jd.com/contact/"> 联系我们 </a> | 
				<a rel="nofollow" target="_blank" href="//zhaopin.jd.com/"> 人才招聘 </a> | 
				<a rel="nofollow" target="_blank" href="//www.jd.com/contact/joinin.aspx"> 商家入驻 </a> | 
				<a rel="nofollow" target="_blank" href="//www.jd.com/intro/service.aspx"> 广告服务 </a> | 
				<a rel="nofollow" target="_blank" href="//app.jd.com/"> 手机EGO </a> | 
				<a target="_blank" href="/links.vm/club.jd.com/links.aspx"> 友情链接 </a> | 
				<a target="_blank" href="//media.jd.com/"> 销售联盟 </a> | 
				<a href="//club.jd.com/" target="_blank"> EGO社区 </a>
			</div>
			<div class="copyright">
				Copyright &copy; 2004-2017 EGO 版权所有
			</div>
		</div>
		</div>
		<aside class="menu">
		<ul>
			<li class="person active"><a href="index.html"><i
					class="am-icon-user"></i>个人中心</a></li>
			<li class="person">
				<p>
					<i class="am-icon-newspaper-o"></i>个人资料
				</p>
				<ul>
					<li><a href="information.html">个人信息</a></li>
					<li><a href="safety.html">安全设置</a></li>
					<li><a href="address.html">地址管理</a></li>
					<li><a href="cardlist.html">快捷支付</a></li>
				</ul>
			</li>
			<li class="person">
				<p>
					<i class="am-icon-balance-scale"></i>我的交易
				</p>
				<ul>
					<li><a href="${root}/user/order/listMyOrder">订单管理</a></li>
					<li><a href="change.html">退款售后</a></li>
					<li><a href="comment.html">评价商品</a></li>
				</ul>
			</li>
			<li class="person">
				<p>
					<i class="am-icon-dollar"></i>我的资产
				</p>
				<ul>
					<li><a href="points.html">我的积分</a></li>
					<li><a href="coupon.html">优惠券 </a></li>
					<li><a href="bonus.html">红包</a></li>
					<li><a href="walletlist.html">账户余额</a></li>
					<li><a href="bill.html">账单明细</a></li>
				</ul>
			</li>
			<li class="person">
				<p>
					<i class="am-icon-tags"></i>我的收藏
				</p>
				<ul>
					<li><a href="${root}/user/gomyCollect">收藏</a></li>
					<li><a href="${root}/user/liuLan/findLiuLan">足迹</a></li>
				</ul>
			</li>
			<li class="person">
				<p>
					<i class="am-icon-qq"></i>在线客服
				</p>
				<ul>
					<li><a href="consultation.html">商品咨询</a></li>
					<li><a href="suggest.html">意见反馈</a></li>

					<li><a href="news.html">我的消息</a></li>
				</ul>
			</li>
		</ul>
		<br><br><br><br><br><br><br>
		</aside>
	</div>
</body>
</html>