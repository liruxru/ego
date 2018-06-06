<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理中心</title>
<link href="${css}/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//setMenuHeight
	$('.menu').height($(window).height()-51-27-26);
	$('.sidebar').height($(window).height()-51-27-26);
	$('.page').height($(window).height()-51-27-26);
	$('.page iframe').width($(window).width()-15-168);
	
	//menu on and off
	$('.btn').click(function(){
		$('.menu').toggle();
		
		if($(".menu").is(":hidden")){
			$('.page iframe').width($(window).width()-15+5);
			}else{
			$('.page iframe').width($(window).width()-15-168);
				}
		});
		
	//
	$('.subMenu a[href="#"]').click(function(){
		$(this).next('ul').toggle();
		return false;
		});
})
</script>
<style type="text/css">
#header{ height:78px; background:url(${img}/images/index-header-bg.gif) repeat-x;}
.logo{ width:362px; height:51px; background:url(${img}/images/index-logo.gif);}
.logout{ width:23px; height:24px; background:url(${img}/images/index-logout.png); margin-top:14px;}
.nav li{ float:left; padding-top:27px; width:93px; height:51px; background:url(${img}/images/index-nav-bg.png); text-align:center;}
.nav-left{ width:34px; height:51px; background:url(${img}/images/index-nav-left.png);}
.nav-right{ width:34px; height:51px; background:url(${img}/images/index-nav-right.png);}
.subnav{ height:27px; background:url(${img}/images/index-subnav-bg.gif)}
.nav .first{ background:url(${img}/images/index-nav-firstbg.png);}
.subnav .subnavLeft{ width:17px; height:27px; background:url(${img}/images/index-subnav-left.gif)}
.subnav .subnavRight{ width:17px; height:27px; background:url(${img}/images/index-subnav-right.gif)}
.menu ul li{ width:168px; background:url(${img}/images/index-menu-bg.gif) no-repeat; text-align:center}
.menu .subMenuTitle{ background:url(${img}/images/index-menu-title.gif); text-align:center; height:27px; line-height:26px; color:#fff}
.menu ul li ul li{ background:url(${img}/images/index-submenu-bg.gif);}
.sidebar .btn{ width:5px; height:39px; background:url(${img}/images/sidebar-on.gif); margin-top:200px;}
#footer{ width:100%; height:26px; line-height:26px; background:url(${img}/images/index-footer-bg.gif);}
.pageTitle{ height:27px; line-height:27px; color:#075587; background:url(${img}/images/page-pageTitle-bg.gif); }
.pageColumn thead{ border:1px solid #bfd8e0; color:#075587; background:url(${img}/images/page-thead-bg.gif);}
.pageColumn{ background:url(${img}/images/page-bg.gif) repeat-x; padding:5px;}
</style>

</head>

<body>
<div id="wrap">
	<div id="header">
    <div class="logo fleft"></div>
    <div class="nav fleft">
    	<ul>
        	<div class="nav-left fleft"></div>
            <li class="first"><a href="${root}/admin/common/main" target="right">我的面板</li>
        	<li>设置</li>
            <li>模块</li>
            <li>内容</li>
            <li>用户</li>
            <li>扩展</li>
            <li>应用</li>
            <div class="nav-right fleft"></div>
        </ul>
    </div>
    <a class="logout fright" href="../logout.jsp"> </a>
    <div class="clear"></div>
    <div class="subnav">
    	<div class="subnavLeft fleft"></div>
        <div class="fleft"></div>
        <div class="subnavRight fright"></div>
    </div>
    </div><!--#header -->
    <div id="content">
    <div class="space"></div>
    <div class="menu fleft">
    	<ul>
        	<li class="subMenuTitle">菜单列表</li>           
            
		   <shiro:hasPermission name="admin:goods">
			     <li class="subMenu"><a href="#">商品管理</a>
				  <ul>
				    <li><a href="${root}/admin/goods/list" target="right"><span class="icon-caret-right"></span>在线商品</a></li>
				    <li><a href="${root}/admin/goodsStu/list" target="right"><span class="icon-caret-right"></span>下架商品</a></li>
				    <li><a href="${root}/admin/goods/edit" target="right"><span class="icon-caret-right"></span>添加商品</a></li>
				  </ul>
				  </li>
         </shiro:hasPermission>
				  
         <shiro:hasPermission name="admin:order"> 
            <li class="subMenu"><a href="#">订单管理</a>
              <ul>
			    <li><a href="${root}/admin/order/list1" target="right"><span class="icon-caret-right"></span>未支付</a></li>
			    <li><a href="${root}/admin/order/list2" target="right"><span class="icon-caret-right"></span>待发货</a></li>
			    <li><a href="${root}/admin/order/list3" target="right"><span class="icon-caret-right"></span>管理订单</a></li>
			  </ul>          
            </li>
        </shiro:hasPermission>
        <shiro:hasPermission name="admin:thorder"> 
            <li class="subMenu"><a href="#">退单管理</a>
              <ul>
			    <li><a href="${root}/admin/thorder/list1" target="right"><span class="icon-caret-right"></span>待审核</a></li>
			    <li><a href="${root}/admin/thorder/list2" target="right"><span class="icon-caret-right"></span>退货记录</a></li>
			    <%-- <li><a href="${root}/admin/order/list3" target="right"><span class="icon-caret-right"></span>管理订单</a></li> --%>
			  </ul>          
            </li>
        </shiro:hasPermission>
            
            
		   <shiro:hasPermission name="admin:user">
		     <li class="subMenu"><a href="#">留言管理</a>
				  <ul>
				    <li><a href="${root}/admin/message/answer" target="right"><span class="icon-caret-right"></span>回复留言</a></li>
				    <li><a href="${root}//admin/message/list" target="right"><span class="icon-caret-right"></span>查看留言</a></li>
				  </ul>
			</li>
		   </shiro:hasPermission>
        </ul>
    </div>
    <div class="sidebar fleft"><div class="btn"></div></div>
    <div class="page">
    <iframe width="100%" scrolling="auto" height="100%" frameborder="false" allowtransparency="true" style="border: medium none;" src="${root}/admin/common/main" id="rightMain" name="right"></iframe>
    </div>
    </div><!--#content -->
  <!--   <div class="clear"></div>
    <div id="footer"></div> --><!--#footer -->
</div><!--#wrap -->
<!-- <div style="text-align:center;">
</div> -->
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<!-- <ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="info.html" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div> -->
</body>
</html>
