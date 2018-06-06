<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>searchGoods</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link rel="stylesheet" href="${resources}/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="${resources}/css/searchgoods.css">
	
	<script type="text/javascript" src="${resources}/js/jquery.min.js" ></script>
	<script type="text/javascript" src="${resources}/js/bootstrap.min.js" ></script>
	
	<script type="text/javascript">
		//分页 name  fullName  typeId   storeId  minNum  maxNum minPrice   maxPrice  souSuo
		var url="";
		<%-- $(function(){
			$("#sub").click(function(){
				var name=$("#name").val();
				var fullName=$("#fullName").val();
				var storeId=$("#storeId").val();
				var minNum=$("#minNum").val();
				var maxNum=$("#maxNum").val();
				var minPrice=$("#minPrice").val();
				var maxPrice=$("#maxPrice").val();
				var txt=$("#txt").val();
				var url="<%=path%>/account/listGoodSrevlet";
				$.post(url,{"name":name,"fullName":fullName,"storeId":storeId,"minNum":minNum,"maxNum":maxNum,"minPrice":minPrice,"maxPrice":maxPrice,"txt":txt},
				function(reg){
					
				},'json');
			})
		}); --%>
		function getUrl(){
			var name=document.getElementById("name").value;
			var fullName=document.getElementById("fullName").value;
			var typename=document.getElementById("typename").value;
			
			var minPrice=document.getElementById("minPrice").value;
			var maxPrice=document.getElementById("maxPrice").value;
			var storename=document.getElementById("storename").value;
			url="${root}/member/search?name="+name+"&fullName="+fullName+"&typename="+typename
				+"&minPrice="+minPrice+"&maxPrice="+maxPrice;
		}
		function goPage(currentPage){
			getUrl();
			var shengXu=document.getElementById("shengXu").value;
			var jiangXu=document.getElementById("jiangXu").value;
			window.location.href=url+"&currentPage="+currentPage+"&shengXu="+shengXu+"&jiangXu="+jiangXu;
		}
		function shengXuClick(){
			getUrl();
			window.location.href=url+"&shengXu=1";
		}
		function jiangXuClick(){
			getUrl();
			window.location.href=url+"&jiangXu=1";
		}
		function souSuoClick(){
			var value=$("#txt").val();
			window.location.href="${root}/member/search?fullName="+value;
		} 
		function typeShow(chuantypename){
			getUrl();
			window.location.href=url+"&typename="+chuantypename;
		}
		$(function(){
			$("td").click(function(){
				var value=$(this).text();
				window.location.href="${root}/member/search?fullName="+value;
			})
		})
		
	</script>
  </head>
  <body>
    <center>
		<jsp:include page="top.jsp"></jsp:include>
		
		<div id="banner">
			<div id="banner_search">
				<div id="logo">
					
				</div>
				<div id="kuang">
					<input id="txt" type="text" placeholder="请输入关键字" id="souSuoKuang" value="${sc.fullName}" />
				</div>
				<div id="zi">
					<input id="btn" type="button" value="搜索" onclick="souSuoClick()" />
				</div>
				<div id="cart">
					<a href="${root}/user/cart/gomyCart">我的购物车&nbsp;&nbsp;
					<img src="${resources}/img/vector/cart.png" width="20px" height="20px" /></a>
				</div>
			</div>
		</div>
		
		<div id="banner_xia">
			<div id="left">
				<a href="javascript:typeShow('全部')">全部商品分类</a>
			</div>
			<div id="right">
				<ul>
					<c:forEach items="${typename}" var="l">
							<li><a href="javascript:typeShow('${l.name}')">${l.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div style="clear: both;"></div>
		<hr color="red" size="3" />
		
		<div id="bottom">
			<div id="bottom_left">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
								家用电器
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<table type="none" align="center">
								<tr >
									<td style="padding: 8px;">电脑</td>
									<td style="padding: 8px;">挂烫机</td>
								</tr>
								<tr>
									<td style="padding: 8px;">电视机</td>
									<td style="padding: 8px;">空调机</td>
								</tr>
								<tr >
									<td style="padding: 8px;">洗衣机</td>
									<td style="padding: 8px;">吹风机</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
								高科技电子
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
						<a style="text-decoration: none;">
							<table type="none" align="center">
								<tr>
									<td style="padding: 8px;">手机</td>
									<td style="padding: 8px;">电脑</td>
								</tr>
								<tr>
									<td style="padding: 8px;">U盘</td>
									<td style="padding: 8px;">显示器</td>
								</tr>
								<tr>
									<td style="padding: 8px;">硬盘</td>
								</tr>
							</table>
						</a>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
								服装品牌
							</a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse">
						<div class="panel-body">
						<a style="text-decoration: none;">
							<table type="none" align="center">
								<tr>
									<td style="padding: 8px;">男装</td>
									<td style="padding: 8px;">女装</td>
								</tr>
							</table>
						</a>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
								医药保健
							</a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse">
						<div class="panel-body">
						<a style="text-decoration: none;">
							<table type="none" align="center">
								<tr>
									<td style="padding: 8px;">中药</td>
									<td style="padding: 8px;">西药</td>
								</tr>
							</table>
						</a>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
								图书商城
							</a>
						</h4>
					</div>
					<div id="collapseSix" class="panel-collapse collapse">
						<div class="panel-body">
						<a style="text-decoration: none;">
							<table type="none" align="center">
								<tr>
									<td style="padding: 8px;">人文社科</td>
									<td style="padding: 8px;">IT科技</td>
								</tr>
								<tr>
									<td style="padding: 8px;">生活艺术</td>
								</tr>
							</table>
						</a>
						</div>
					</div>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
								食品酒类
							</a>
						</h4>
					</div>
					<div id="collapseSeven" class="panel-collapse collapse">
						<div class="panel-body">
						<a style="text-decoration: none;">
							<table type="none" align="center">
								<tr>
									<td style="padding: 8px;">陈年老酒</td>
									<td style="padding: 8px;">白酒</td>
								</tr>
								<tr>
									<td style="padding: 8px;">葡萄酒</td>
									<td style="padding: 8px;">养生酒</td>
								</tr>
								<tr>
									<td style="padding: 8px;">茗茶</td>
								</tr>
							</table>
						</a>
						</div>
					</div>
				</div>
				</div>
			</div>
			<div id="bottom_right_top">
				 <form action="${root}/member/search" method="post" >
					<div id="bottom_right_top_left">

						商品名称：<input id="name" name="name" value="${sc.name}" >
						商品全称：<input id="fullName" name="fullName" value="${sc.fullName}" >
						
						商品类型：<select id="typename" name="typename" >
									<option value="">请选择类型</option>
										<c:forEach  items="${listType}" var="l">
											<option  value="${l.name}"  <c:if test="${l.name eq sc.typename}">selected="selected"</c:if>    >${l.name}</option>
										</c:forEach>
								</select>
						商品店铺：<select id="storename" name="storename" >
									<option value="">请选择店铺</option>
									<c:forEach items="${listStore}" var="lss">
										<option value="${lss.name}"    <c:if test="${lss.name eq sc.storename}">selected="selected"</c:if>   >${lss.name}</option>
									</c:forEach>
								</select><br>
						
						商品价格：<input id="minPrice" name="minPrice" value="${sc.minPrice}" class="xiaokuang" >
								<input id="maxPrice" name="maxPrice" value="${sc.maxPrice}" class="xiaokuang" >
						<input type="button" value="升序" onclick="shengXuClick()" />
						<input type="button" value="降序" onclick="jiangXuClick()" />
						
						<input type="hidden" id="shengXu" name="shengXu" value="${sc.shengXu}" >
						<input type="hidden" id="jiangXu" name="jiangXu" value="${sc.jiangXu}">
					</div>
					<div id="bottom_right_top_right">
						<input id="sub" type="submit" value="查询">  
					</div>
				</form>
			</div>
<!-- 			<iframe frameborder="0" width="1000px" height="950px" name="yulan" ></iframe> -->
			
			 <div class="bottom_right_xia">
				<c:if test="${not empty listGood}">
					<c:forEach items="${listGood}" var="ls" > 
						<div class="bottom_right_xia_right">
						<a href="${root}/member/goXiangqing?id=${ls._id}"><img title="${ls.name}" src="${root}/resources/upload/${ls.coverimg}" class="bottom_right_xia_img" /></a>
							<b <c:if test="${ls.price ne 0}"> style="text-decoration: line-through; color:red;"</c:if>>原价：￥<fmt:formatNumber pattern="#,##################################################.00">${ls.price}</fmt:formatNumber></b><br>
							<b <c:if test="${ls.price eq 0}"> style="visibility:hidden;color:red;"</c:if>>促销价价：￥<fmt:formatNumber pattern="#,##################################################.00">${ls.sale}</fmt:formatNumber></b><br> 
							<p><a href="${root}/member/goXiangqing?id=${ls._id}">${ls.fullname}</a></p><br>
							<p>店铺：${ls.store}<br>类型：${ls.type}</p>
						</div>
					</c:forEach>   
				</c:if>
				<c:if test="${empty listGood}">
					
				
				</c:if>
			</div>
			<div id="bottom_right_foot">
				总条数：<input type="text" id="count" class="sel" value="${page.count}" readonly="readonly" > 
				总页数：<input type="text" id="totalPage" class="sel" value="${page.pageCount}" readonly="readonly" > 
				当前页数：<input type="text" id="currentPage" class="sel" value="${page.pageCode}" readonly="readonly" > 
				<c:if test="${page.pageCode ne 1}">
					<a href="javascript:goPage(1)">首页</a>
					<a href="javascript:goPage(${page.pageCode-1})">上一页</a>
				</c:if>
				<c:if test="${page.pageCode ne page.pageCount}">
					<a href="javascript:goPage(${page.pageCode+1})">下一页</a>
					<a href="javascript:goPage(${page.pageCount})">末页</a>
				</c:if>
			</div>
		</div>
		<!--foot-->
		<div class="foot">
			<p>
				<a href="#">关于我们</a>
				<a href="#">联系客服</a>
				<a href="#">合作招商</a>
				<a href="#">商家帮助</a>
				<a href="#">营销中心</a>
				<a href="#">友情链接</a>
				<a href="#">销售联盟</a>
				<a href="#">风险监测</a>
				<a href="#">隐私政策</a>
				<a href="#">公益活动</a>
			</p>
			<p>
				<a href="#">经公网安备&nbsp;1100000002000000088号</a>
				<span>京ICP证0700369</span>
				<a href="#">互联网信息服务资格证编号【2014】2014-008</a>
				<span>新出发京零 字第大120007号</span>
			</p>
			<p>
				<span>互联网出版许可证编号新出网证(京)字150号</span>
				<a href="#">出版物经营许可证</a>
				<a href="#">网络文化经营许可证京网文[2014]2148-348号</a>
				<span>违法和不良信息举报电话：4006561155</span>
			</p>
			<p>   
				<span>Copyright &copy; 2004 - 2017  EGO版权所有&nbsp;|&nbsp;消费者维权热线：4006067733</span>
				<a href="#">经营执照</a>
			</p>
		</div>
		</center>
  </body>
</html>
