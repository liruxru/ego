<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'xiangqing.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="${resources}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${resources}/css/descripation.css" />
<link rel="stylesheet" href="${resources}/css/des2.css">

<script type="text/javascript" src="${resources}/js/jquery.min.js"></script>
<script type="text/javascript" src="${resources}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${resources}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${resources}/js/myScript.js"></script>

<script type="text/javascript" src="${resources}/js/jqueryXQ.js"></script>
<script type="text/javascript" src="${resources}/js/scroll.js"></script>

<script>
	//点击小图片时 传递地址
	function select(obj){
			var sImg=document.getElementById("shopImg");
			sImg.src=obj.src;
			var bImg=document.getElementById("bigImg");
			bImg.src=obj.src;
	}
	
	$(function(){
		
		var $seldiv=$(".seldiv");
		var $abright=$(".abright");
		$seldiv.each(function(index){
			$(this).click(function () {
				$(this).css("background-color","lightgoldenrodyellow").siblings().css("background-color","white"); 
				$abright.eq(index).css("display","block").siblings().css("display","none");
			}) 
		}) 
		
	})


	
	
	
	$(function(){
			//提交搜索框
			$(".button").click(function(){
				var search=$("#search").val();
				window.location.href="${root}/member/search?fullName="+search;
			})
			$(".button2").click(function(){
				var search=$("#search").val();
				var storename=$("#storeName").val();
				window.location.href="${root}/member/search?storename="+storename+"&fullName="+search;
			})
			//ego头条
			$(document).ready(function(){
				$('.list_lh li:even').addClass('lieven');
			})
			$(function(){
				$("div.list_lh").myScroll({
					speed:60, //数值越大，速度越慢
					rowHeight:68 //li的高度
				});
			});
	
	
			//加减商品数量
			$(".addNum").click(function(){
					var numObj=$(this).prev();
					var num=$(this).prev().val()*1;
					numObj.val(num+1);
				})
				
			$(".jianNum").click(function(){
				var numObj=$(this).next();
				var num=$(this).next().val()*1;
				if((num -1)>0){
					numObj.val(num-1);
				}else{
					numObj.val(num);
				}
			})
		//选择不同系列 改变边框
		$(".series_but").click(function(){
			var butArr=$(".series_but");
			
			for(var i=0;i<butArr.length;i++){
				butArr.eq(i).css("border","1px solid #F7F7F7")
			}
			$(this).css("border","1px solid #df3033")				
			
		})
		//选择不同颜色改变边框
		$(".colour_but").click(function(){
			var butArr=$(".colour_but");
			
			for(var i=0;i<butArr.length;i++){
				butArr.eq(i).css("border","1px solid #F7F7F7")
			}
			$(this).css("border","2px solid #28BD19")				
			
		})
		
		//选择不同小图片时改变边框
		$(".imgselect").click(function(){
			var butArr=$(".imgselect");
			
			for(var i=0;i<butArr.length;i++){
				butArr.eq(i).css("border","1px solid #F7F7F7")
			}
			$(this).css("border","1px solid #df3033")				
			
		})
		
		
		$("#sheng").change(function(){
				var shengcode=$(this).val(); 
				var shiObj=$("#shi");
				var quObj=$("#qu"); 
				if(shengcode!=0){
					var url="${root}/getListShiBysheng";
					$.post(url,
					{"shengCode":shengcode},
					function(data){
						shiObj.html("<option value='0'>请选择市</option>");
						quObj.html("<option value='0'>请选择区</option>");
						var html="";
						for(var i=0;i<data.length;i++){
							html+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
						}
						shiObj.append(html);
					},
					'json');
				}else{
					shiObj.html("<option value='0'>请选择市</option>");
					quObj.html("<option value='0'>请选择区</option>");
					
				}
			});
		$("#shi").change(function(){
				var shicode=$(this).val();
				var quObj=$("#qu");
				if(shicode!=0){
					var url="${root}/getQuByShi";
					$.post(url,{"shiCode":shicode},
						function(data){
							quObj.html("<option value='0'>请选择区</option>");
							var html="";
							for(var i=0;i<data.length;i++){
								html+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
							}
							quObj.append(html);
					},
					'json');
				}else{
					quObj.html("<option value='0'>请选择区</option>");
					
				}
			}) 
			
				
			/*点击立即购买按钮  */
			  $("#orderBut").click(function(){
				
				var goodsId=$("#goodsId").val();
				var cartNum=$("#cartNum").val();
				/* alert(goodsId) */
				window.location.href="${root}/user/cart/buyNow?tag=cart&goodsId="+goodsId+"&cartNum="+cartNum;
					
				
			})  
			
			/* 点击购物车按钮时触发的事件 */	
			$("#div_message").hide();//初始 消失
			$("#div_addCartOk").hide()
			function hide(){
				$("#div_message").animate({opacity: "hide"}, "slideUp");
				$("#div_addCartOk").animate({opacity: "hide"}, "slideUp");
			}
				
			//添加购物车  未登录 添加成功返回的code为0
			$("#cartBut").click(function(){
				var goodsId=$("#goodsId").val();
				var cartNum=$("#cartNum").val();
				var cartGoodprice=$("#cartPrice").val(); 
				var url="${root}/user/cart/addToCart";
				$.post(url,{"goodsId":goodsId,"cartNum":cartNum},
						function(message){
							if(message==301){//用户没有登录  将请求 跳转至登录请求
								window.location.href="${root}/member/gologinView?tag=cart&goodsId="+goodsId;
								return;
							}
							if(message.resultCode==0){
								 $("#div_addCartOk").show();
								 setTimeout(hide,1500);//1.5秒后消失
							} 
							if(message.resultCode==1){
								/* alert(message.message) */
								$("#div_message").show();
								setTimeout(hide,1500);//1.5秒后消失
							} 
					
				},'json');
			})
			
			//添加购物车  1.确保已经全选  2.确保用户处于登录状态   3.
			 /* $("#cartBut").click(function(){
				var shengVal=$("#sheng").val();
				var shiVal=$("#shi").val();
				var quVal=$("#qu").val();
				var goodId=$("#goodId").val();
				var cartNum=$("#cartNum").val();
				var cartGoodname=$("#cartGoodname").val();
				var cartImgid=$("#imgId").val();
				var storeId=$("#storeId").val();
				var cartGoodprice=$("#cartPrice").val(); 
				
				if(shengVal==0||shiVal==0||quVal==0){
						$("#div_message").show();
						setTimeout(hide,1500);//1.5秒后消失
				}else{
						var url="${root}/account/addCart";
						$.post(url,{"goodId":goodId,"cartNum":cartNum,"shengVal":shengVal,"shiVal":shiVal,"quVal":quVal,
						"cartGoodprice":cartGoodprice,"cartGoodname":cartGoodname,"cartImgid":cartImgid,"storeId":storeId,"sheng":shengVal,"shi":shiVal,"qu":quVal},
							function(data){
								if(data==301){//用户没有登录  将请求 跳转至登录请求
									window.location.href="${root}/account/gouserLoginServlet?tag=cart&goodId="+goodId;
									return;
								}
								if(data==0){
									$("#div_addCartOk").show();
									setTimeout(hide,1500);//1.5秒后消失
								}
							},
							'json');
				}
			})  */
			
			
			//关注店铺
			$(".guan").click(function(){
				var store=$("#store").val();
				
// 				alert(store)
			})
			//收藏宝贝
			$("#collect").click(function(){
				var goodsId=$("#goodsId").val();
				var url="${root}/user/collect/addToCollect";
				$.post(url,{"goodsId":goodsId},function(message){
					if(message==301){//用户没有登录  将请求 跳转至登录请求
						window.location.href="${root}/member/gologinView?tag=cart&goodsId="+goodsId;
						return;
					}
					
					if(message.resultCode==0){
						alert("收藏成功")
						/*  $("#div_addCartOk").show();
						 setTimeout(hide,1500);//1.5秒后消失 */
					} 
					if(message.resultCode==1){
						alert(message.message)
						/* $("#div_message").show();
						setTimeout(hide,1500);//1.5秒后消失 */
					} 
				},'json')
				
			})
			
			
			
	})
	 
</script>
</head>
<body>
	<div id="main">
		<jsp:include page="top.jsp"></jsp:include><!-- 动态导入 页面 -->
		<div class="header">
			<div class="headerLeft">
				<img src="${resources}/img/XQ/logo.png" height="100px;" width="200px" style="margin-left: 2px" />
			</div>
			<div class="headerCenter">
				<input id="search" type="text" placeholder="请输入检索内容"  class="text">
				<button class="button"  >搜全站</button>
				<button class="button2"  >搜本店</button>
			</div>
			<div class="headerRight">
				<ul>
					<li><a href="${root}/user/cart/gomyCart" style="text-decoration: none; color: red">我的购物车</a>
					<img alt="" src="${resources}/img/XQ/topcart.png"></li>
				</ul>
			</div>
		</div>
		<!--店铺招牌区域 -->
		<div class="zhaopai">
			<div class="zhaologo">
				<img src="${resources}/img/XQ/dianpulogo.PNG"> <img
					src="${resources}/img/XQ/dianpu.PNG">
			</div>
			<div class="jian">
				<img src="${resources}/img/XQ/phone.PNG"> 
					<span style="font-size: 16px;color: #125B64;" >${goods.store}</span>
			</div>
			<div class="collectdiv">
				<button id="collect">收藏宝贝</button>
			</div>
		</div>
		<div class="nav">
			<div class="dropdown">
				<button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown" style="background-color:red">
					<span style="color: #DFF0D8">全部商品</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="#" style="text-decoration: none;">
							<table type="none" align="center" style="width: 160px; ">
								<tr>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=家用电器">家用电器</a></td>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=家用电器">家用电器</a></td>
								</tr>
								<tr>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=服装品牌">服装品牌</a></td>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=医药保健">医药保健</a></td>
								</tr>
								<tr style="padding: 8px;">
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=图书商城">图书商城</a></td>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=食品酒类">食品酒类</a></td>
								</tr>
								<tr style="padding: 8px;">
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=服装品牌">服装品牌</a></td>
									<td style="padding: 8px;color: red;"><a href="${root}/account/listGoodSrevlet?souSuo=图书商城">图书商城</a></td>
								</tr>
							</table>
					</a>
				</ul>
			</div>
			<div class="menu">
				<ul>
					<li><a href="#">首页</a></li>
					<c:forEach items="${listType}" var="l">
						<li><a href="${root}/member/search?fullName=${l.name}">${l.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- <div id="clear"></div>
			<hr style="height: 4px; background-color: red; width: 1200px;margin-top: 0px;" /> -->
		<div id="content">
			<div id="conLeft">
				<div id="box">
				
					<!--商品部分-->
					<div class="posi">
						 <img src="${root}/resources/upload/${goods.coverimg}" id="shopImg" width="400px" height="400px"/>
						<div class="move">
						</div>
					</div>
					<!--商品对应的大图片-->
					<div class="bigImage">
						<img src="${root}/resources/upload/${goods.coverimg}" id="bigImg" height="800px" width="800px"/>
					</div>
				</div>
				
				<!--商品下方的小图 -->
				<div id="clbutton">
					<img  src="${root}/resources/upload/${goods.coverimg}" class="imgselect"  style="border: 1px solid #df3033;" width="70px" height="70px" onclick="select(this)" />
					<c:if test="${arrImgs ne null }">
						<c:forEach items="${arrImgs}" var="als"> 
							<%-- <input type="hidden" value="${als.imgUuidname}"  id="checkIv"/> --%>
							<img  src="${root}/resources/upload/${als.uuidname}" class="imgselect" width="70px" height="70px" onclick="select(this)" />
						</c:forEach>
					</c:if>
				</div>
			</div>
				<!-- 商品信息部分 -->
			 <div id="goodsinfo">
			 		<p id="goods_name">${goods.fullname}</p>
			 		<div id="div_price">
			 			售&nbsp;&nbsp;价:
			 				<c:if test="${goods.price ne 0}">  <!-- 原价不等于0时，展示现在售价-->
			 					<span class="price" style="font-size: 20px;color:red; " >¥<fmt:formatNumber pattern="#,###.00" value="${goods.sale}"></fmt:formatNumber></span>&nbsp;&nbsp;
			 					<span class="price" style="text-decoration: line-through;">¥<fmt:formatNumber pattern="#,###.00" value="${goods.price}"></fmt:formatNumber></span><span>&nbsp;&nbsp;&nbsp;<a href="#" style="text-decoration: none">降价通知</a></span><br>
			 			 	</c:if>
			 			 	<c:if test="${goods.price eq 0}">
			 			 		<span class="price" style="font-size: 20px;color:red" >¥<fmt:formatNumber pattern="#,###.00" value="${goods.sale}"></fmt:formatNumber></span>&nbsp;&nbsp; <span><a href="#"  style="text-decoration: none">降价通知</a></span><br>
			 			 	</c:if>
					</div>
			 		<div id="sale">
			 		 	促&nbsp;&nbsp;销：<em class="cuxiao">多买优惠</em>
			 		 	<em class="cutext">满2件，积分加1倍；满3件，积分加2倍</em>
			 		</div>
			 		<div id="address">
			 			颜&nbsp;&nbsp;色：<input class="colour_but" type="button"  style="border: 2px solid #28BD19;background-color:#F51F3F;width: 40px;">
			 							<input class="colour_but" type="button"  style="background-color: #2AB453;width: 40px;">
			 							<input class="colour_but" type="button"  style="background-color: #3492D1;width: 40px;">
			 		</div>
			 		<div id="series">
			 				<div>尺&nbsp;寸&nbsp;分&nbsp;类：</div>
			 				<div class="series_div">
				 				<ul>
				 					<li><input class="series_but" type="button" value="S" style="border: 1px solid #df3033;"></li>
				 					<li><input class="series_but" type="button" value="M"></li>
				 					<li><input class="series_but" type="button" value="L"></li>
				 				</ul>
				 				<ul>
				 					<li><input class="series_but" type="button" value="XL"></li>
				 					<li><input class="series_but" type="button" value="XXL"></li>
				 					<li><input class="series_but" type="button" value="XXXL"></li>
				 				</ul>
			 				</div>
			 		</div>
			 		<span id="Num">数量：<input type="button" value="-" class="jianNum"/><input type="text" id="cartNum" value="1" readonly="readonly"  style="width: 20px;"/><input type="button" value="+" class="addNum"/></span> &nbsp;&nbsp;&nbsp; <span id="numSpan">有货</span>
			 		<div id="addCart">
			 			<p><button id="cartBut" style="color: red">加入购物车<img alt="" src="${root}/img/topcart.png"></button></p>
						<p><button id="orderBut">立即购买</button></p>
			 		</div>
			 		<div id="fuwu">
			 			<ul>
			 				<li><a>服务承诺</a></li>
			 				<li><a>正品保证</a></li>
			 				<li><a>极速退款</a></li>
			 				<li><a>赠运费险</a></li>
			 				<li><a>晚到必赔</a></li>
			 			</ul>
			 		</div>
			 		<!-- 页面中的隐藏内容  -->
			 		<div id="div_message" style="display: none">
			 			添加失败，稍后重试
			 		</div>
			 		<div id="div_addCartOk" style="display: none">
			 			添加购物车成功
			 		</div>
			 		
			 		<%-- <input value="${arrImgs.get(0).imgId}"  id="imgId" type="hidden">
			 		<input type="hidden" value="${goods.sale}" id="cartPrice"/>
			 		<input type="hidden" value="${goods.store}" id="storeName"/>
			 		<input type="hidden" value="${goods._id}" id="goodsId"/>
			 		<input type="hidden" value="${goods.goodName}" id="cartGoodname"/> --%>
			 		<input type="hidden" value="${goods._id}" id="goodsId"/>
			 		<input type="hidden" value="${goods.store}" id="storeName"/>
			</div>
			<!--ego头条  -->
			<div id="conRight">
						<div class="bcon">
						<h1><b>ego头条</b></h1>
						<!-- 代码开始 -->
						<div class="list_lh">
							<ul>
								<li>
									<p><a href="#" target="_blank">1000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">红黄蓝事件又有新说法</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">2000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">3000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">老班长的猪又跑了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">4000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">5000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">老班长的猪又跑了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">6000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">7000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">老班长的猪又跑了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">8000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">9000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">老班长的猪又跑了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">1000000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">1100000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">老班长的猪又跑了</a><span>17:28:05</span></p>
								</li>
								<li>
									<p><a href="#" target="_blank">1200000</a><a href="#" target="_blank" class="btn_lh">领号</a><em>获得</em></p>
									<p><a href="#" target="_blank" class="a_blue">通说马蓉又再婚了</a><span>17:28:05</span></p>
								</li>
							</ul>
						</div>
						<!-- 代码结束 -->
					</div>
			</div>
		</div>
		<div style="clear: both;"></div>
		<hr color="red" size="3" />
		<div id="banner_xia">
			<div id="left">
				 <a href="javascript:typeShow(0)"style="text-align: center;">皇冠卖家</a> 
			</div>
			<div id="right">
				<ul>
					<li class="seldiv"><a href="javascript:void(0)">商品详情</a></li>
					<li class="seldiv"><a href="javascript:void(0)">累计评价</a></li>
					<li class="seldiv"><a href="javascript:void(0)">服务质量</a></li>
					<li class="seldiv"><a href="javascript:void(0)">售后保障</a></li>
				</ul>
			</div>
		</div>
		<div id="bottom">
				<div id="bottom_left">
					<p style="text-align: center;">${store.name}</p>			
					<p>信誉：<img src="${resources}/img/XQ/jiandian2.PNG"/></p>			
					<p>联系卖家：${store.phone}</p>			
					<p>资质：<img src="${resources}/img/XQ/jindian3.PNG"/></p>
					<p>评分：<span style="color: red">${store.score}</span></p>		
					<hr />
					<p><img src="${resources}/img/XQ/jindian4.PNG"/></p>
					<p>
						<button class="storebut">进店逛逛</button>&nbsp;&nbsp;
						<button class="storebut guan">关注店铺</button>
					</p>
					<!-- <input type="button" border="1px solid #F7F7F7" value="进入店铺"><input type="button" value="收藏店铺" > -->	
					<a target=blank href=tencent://message/?uin=1070443459&Site=自己&Menu=yes>
						<img style="width: 180px;" border="0" SRC="${resources}/img/QQKEFU.jpg" alt="点击这里给我发消息">
					</a>
					<input type="hidden" id="store" value="${store.id}">
				</div>
				<div class="bottom_right">
					<!--商品详情div-->
					<div class="abright" style="display: block">
						  
					<div id="Jsmain">
						<div id="Info">
							<div id="Info_left">
						    	<p>品牌：艾德玛</p>
						    	<p>货号：${goods._id}</p>
						    	<p>产地：中国大陆</p>
					    	</div>
					    	<div id="Info_center">
					    		<p>上市年份：2017年冬</p>
					    		<p>基本风格：时尚都市</p>
					    		<p>主图来源：自主实拍图</p>
					    	</div>
					    	<div id="Info_right">
					    		<p>店铺：${store.name}</p>
					    		<p>售后服务：全国联保</p>
					    		<p>证书状态：有效</p>
					    	</div>
				    	</div>
			
				    	
				    	<div id="dess">
					    	<div id="video">
					    		<img src="${resources}/img/XQ/xinx.PNG" width="1000px" height="100px"/>
								<!--<video width="650px" height="500px" controls="controls">
										<source src="img/f0.mp4" type="video/mp4" />
								</video>-->
							</div>
							<div id="show">
								<span><img  src="${resources}/upload/${goods.coverimg}" style="border: 1px solid #E7E7E7;" width="400px" height="500px"  /></span>
								<c:if test="${arrImgs ne null }">
				    				<span><img  src="${resources}/upload/${arrImgs.get(0).uuidname}" style="border: 1px solid #E7E7E7;" width="400px" height="500px"  /></span>  
				    			</c:if>
				    		</div>
				    	</div>
	    	
	    		</div>
						  
						  
					</div>
					<!--累计评价div-->
					
					<div class="abright" style="display: none">
						
							<c:forEach  items="${pingJiaWithDescriptionList }" var="pingJiaWithDescription">
								<div class="pingJiadiv">
								<h5>用户${pingJiaWithDescription.userId}** :</h5>
								<c:forEach items="${pingJiaWithDescription.pin_hui_Entiy}" var="pin_hui"  >
									<div class="usertext" >
										&nbsp;&nbsp;
										 用户评价：${pin_hui.key.txt}<br/>
										<span><fmt:formatDate value="${pin_hui.key.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>  </span>
									</div>
									<div class="usertext" >
										<c:if test="${not empty pin_hui.value}">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											管理员的回复:${pin_hui.value.txt}<br/>
											<span><fmt:formatDate value="${pin_hui.value.createdate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
										</c:if>
									</div>
							</c:forEach>
							</div>
							</c:forEach>
						
						
						
					<%-- <c:forEach  items="${pingJiaWithDescriptionList }" var="pingJiaWithDescription">
					用户${pingJiaWithDescription.userId }的评价  评价日期${pingJiaWithDescription.createdate}<br/>
					<c:forEach items="${pingJiaWithDescription.pin_hui_Entiy}" var="pin_hui"  >
					用户评价:${pin_hui.key.createdate} -->${pin_hui.key.txt}<br/>
					<c:if test="${not empty pin_hui.value}">
					管理员的回复:${pin_hui.value.createdate} -->${pin_hui.value.txt}<br/>
					</c:if>
					</c:forEach>
					<br/><br/><br/>
					
					</c:forEach> --%>
						
						
						
						
							
					</div>
				
				</div>
				
			
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
				<span>Copyright &copy; 2004 - 2017 ego.com 版权所有&nbsp;|&nbsp;消费者维权热线：4006067733</span>
				<a href="#">经营执照</a>
			</p>
		</div>
	
	
	
</body>
</html>
