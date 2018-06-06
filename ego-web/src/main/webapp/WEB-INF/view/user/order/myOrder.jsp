<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>

<link rel="stylesheet" href="${css}/order/orstyle.css" />
<link rel="stylesheet" href="${css}/order/personal.css" />
<link rel="stylesheet" href="${css}/order/amazeui.css" />
<link rel="stylesheet" href="${css}/order/admin.css" />
<link rel="stylesheet" href="${css}/bootstrap.min.css" />

<script type="text/javascript" src="${js}/order/jquery.min.js"></script>
<script type="text/javascript" src="${js}/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${js}/bootstrap.min.js"></script>
<script type="text/javascript" src="${js}/jquery-1.8.3.min.js"></script>

<script type="text/javascript" src="${js}/order/amazeui.min.js"></script>
<!-- 富文本编辑器 -->
<link rel="stylesheet" type="text/css" href="${resources}/kindeditor/themes/default/default.css">
<link rel="stylesheet" type="text/css" href="${resources}/kindeditor/plugins/code/prettify.css">
<script type="text/javascript" src="${resources}/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${resources}/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript" src="${resources}/kindeditor/plugins/code/prettify.js"></script>

<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor1 = K.create('.editorId', {
			cssPath : '${resources}/css/prettify.css', 
			uploadJson : '${resources}/kindeditor/upload_json.jsp',
	        fileManagerJson : '${resources}/kindeditor/file_manager_json.jsp',
	        afterBlur:function(){
	        	this.sync();
	        }
		});
	});


	$(function(){
		
		var topHtml="<div class='order-top'><div class='th th-item'><span class='td-inner'>商品</span></div>";
			topHtml+="<div class='th th-price'><span class='td-inner'>单价</span></div>";
			topHtml+="<div class='th th-number'><span class='td-inner'>数量</span></div>";
			topHtml+="<div class='th th-operation'><span class='td-inner'>商品操作</span></div>";
			topHtml+="<div class='th th-amount'><span class='td-inner'>合计</span></div>";
			topHtml+="<div class='th th-status'><span class='td-inner'>交易状态</span></div>";
			topHtml+="<div class='th th-change'><span class='td-inner'>交易操作</span></div></div>";
			topHtml+="<div class='order-main'><div class='order-list'>";
		
			function dateFtt(fmt,date)   
			{ //author: meizz   
			  var o = {   
			    "M+" : date.getMonth()+1,                 //月份   
			    "d+" : date.getDate(),                    //日   
			    "h+" : date.getHours(),                   //小时   
			    "m+" : date.getMinutes(),                 //分   
			    "s+" : date.getSeconds(),                 //秒   
			    "q+" : Math.floor((date.getMonth()+3)/3),
			    "S"  : date.getMilliseconds()             //毫秒   
			  };   
			  if(/(y+)/.test(fmt))   
			    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
			    if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;   
			} 
			
		/* 异步加载不同状态的订单信息 */
		$(".tab").click(function(){
			var status=$(this).attr("name");
			var target;
			if(status==1){
				target=$("#tab2");
			}
			if(status==2){
				target=$("#tab3");
			}
			if(status==3){
				target=$("#tab4");
			}
			if(status==11){
				target=$("#tab5");
			}
			var url="${root}/user/order/selectOrderWithOrderItemByUserIdAndStatusAnsy"
			$.post(url,{"status":status},function(list){
				var html=topHtml;
				for(var i=0;i<list.length;i++ ){
					html+="<div class=''order-status5' id='order"+list[i].orderId+"'>";
					html+="<div class='order-title'><div class='dd-num'>";
					html+="订单编号：<a href='javascript:;'>"+list[i].sn+"</a></div>";
					var date=new Date(list[i].createdate);
					html+="<span>成交时间："+dateFtt("yyyy-MM-dd hh:mm:ss",date)+"</span>";	
					html+=" <em>店铺："+list[i].store.name +"</em></div>";
					html+="<div class='order-content'><div class='order-left'>";
					for(var j=0;j<list[i].orderItemsWithGoodsList.length;j++){
						var orderItemsWithGoods=list[i].orderItemsWithGoodsList[j];
						html+="<ul class='item-list'><li class='td td-item'><div class='item-pic'><a  class='J_MakePoint'>";
						html+=" <img src='${resources}upload/"+orderItemsWithGoods.goodsAll.coverimg+"'	class='itempic J_ItemImg'  name='"+orderItemsWithGoods.goodsAll._id+"'></a></div>";
						html+="<div class='item-info'><div class='item-basic-info'><a href='${root}/member/goXiangqing?id="+orderItemsWithGoods.goodsAll._id+"'>";
						html+="<p>"+orderItemsWithGoods.goodsAll.fullname+"</p>";
						html+="<p class='info-little'>"+orderItemsWithGoods.goodsAll.description+"</p></a></div></div></li>";
						html+="<li class='td td-price'><div class='item-price'>"+orderItemsWithGoods.price+"</div></li>";
						html+="<li class='td td-number'><div class='item-number'><span>×</span>"+orderItemsWithGoods.num;
						html+="</div></li><li class='td td-operation'>";
						if(list[i].status==1)html+="<div class='item-operation'> </div>";
// 						<a class="tui" rel="${orderItemsWithGoods.goods}"  name="${orderItemsWithGoods.id}" data-toggle="modal"   rel='"orderItemsWithGoods.goodsAll._id"'  data-target="#myTui"  href="javascript:void(0)" > 退款退货</a>
						if(list[i].status==2)html+="<div class='item-operation'> <a name='"+orderItemsWithGoods.id+"'   rel='"+orderItemsWithGoods.goods+"'   href='javascript:void(0)' class='tui' data-toggle='modal' data-target='#myTui' >退款</a> </div>";
						if(list[i].status==3)html+="<div class='item-operation'>  <a name='"+orderItemsWithGoods.id+"'   rel='"+orderItemsWithGoods.goods+"'   href='javascript:void(0)' class='tui' data-toggle='modal' data-target='#myTui' >退款退货</a>    </div>";
						if(list[i].status==10)html+="<div class='item-operation'> </div>";
// 						 														<a name="${orderItemsWithGoods.id }" data-toggle="modal" data-target="#myModal"    href="javascript:void(0)" class="pingJia" >  评价商品 </a>
						if(list[i].status==11)html+="<div class='item-operation'><a name='"+orderItemsWithGoods.id+"'  data-toggle='modal'  data-target='#myModal'   href='javascript:void(0)' class='pingJia' >评价商品</a> <br/> <a name='"+orderItemsWithGoods.id+"'   rel='"+orderItemsWithGoods.goods+"'  href='javascript:void(0)' class='tui' data-toggle='modal' data-target='#myTui' >退款退货</a> </div>";
						html+="</li></ul>";
					}
					html+="<div class='order-right'><li class='td td-amount'><div class='item-amount'>合计："+list[i].sum+"</div></li><div class='move-right'>";
					if(list[i].status==1){
						html+="<li class='td td-status'><div class='item-status'><p class='Mystatus'>等待买家付款</p><p class='order-info'>	<a  class='cancelOrder' ";
						html+="name='"+list[i].orderId+"'  href='javascript:void(0)'>取消订单</a></p></div></li>"
						html+="<li class='td td-change'><a href='javascript:void(0)' name='"+list[i].orderId+"'  class='pay'><div class='am-btn am-btn-danger anniu'  >一键支付</div></a>	</li>"; 
					}
					if(list[i].status==2){
						html+="<li class='td td-status'><div class='item-status'><p class='Mystatus'>买家已付款</p>";
						html+="<p class='order-info'><a href='orderinfo.html'>订单详情</a></p></div></li>";
						html+="<li class='td td-change'><div class='am-btn am-btn-danger anniu fahuo' >提醒发货</div></li>";
					}
					if(list[i].status==3){
						html+="<li class='td td-status'><div class='item-status'><p class='Mystatus'>卖家已发货</p>";
						html+="<p class='order-info'><a href='orderinfo.html'>订单详情</a></p>";
						html+="<p class='order-info'><a href='logistics.html'>查看物流</a></p>";
						html+="<p class='order-info'><a href='#'>延长收货</a></p>	</div></li>	<li class='td td-change'>";
						html+="<div class='am-btn am-btn-danger anniu'><a class='confirmGetOrder' name='"+list[i].orderId+"'  href='javascript:void(0)'>确认收货</a></div>	</li>";
					}
					if(list[i].status==10){
						html+="<li class='td td-status'><div class='item-status'><p class='Mystatus'>交易关闭</p>	</div></li>";
						html+="<li class='td td-change'><div class='am-btn am-btn-danger anniu'>";
						html+="<a class='deleteOrderBtn' name='"+list[i].orderId+"' href='javascript:void(0)' >删除订单</a></div></li>";
					}
					if(list[i].status==11){
						html+="<li class='td td-status'><div class='item-status'><p class='Mystatus'>交易成功</p>";
						html+="<p class='order-info'><a href='orderinfo.html'>订单详情</a></p><p class='order-info'><a href='logistics.html'>查看物流</a></p>";
						html+="</div></li><li class='td td-change'><div class='am-btn am-btn-danger anniu'>"
						html+="<a class='deleteOrderBtn' name='"+list[i].orderId+"' href='javascript:void(0)' >删除订单</a></div></li>";	
					}
					html+="</div></div></div></div>";
				}
				html+="</div></div></div></div>";
				target.html(html);
			},'json')
		})
		
		$(".J_ItemImg").live("click",function(){
			var goodsId=$(this).attr("name");
			window.location.href="${root}/member/goXiangqing?id="+goodsId;
		})
		
		/*提醒发货 */
		$(".fahuo").live("click",function(){
			alert("已提醒");
		})
		
		/* 取消订单 */
		$(".cancelOrder").live("click",function(){
			var orderId=$(this).attr("name");
			var obj=$(this).parent().parent().parent().parent();
			var url="${root }/user/order/updateOrderStatus";
			$.post(url,{"orderId":orderId,"status":10},function(returnMessage){
				/* alert(returnMessage.resultCode) */
				if(returnMessage.resultCode==0){
					obj.html("<li class='td td-status'><div class='item-status'><p class='Mystatus'>交易关闭</p></div></li><li class='td td-change'><div class='am-btn am-btn-danger anniu'><a name='"+orderId+"' class='deleteOrderBtn'  href='javascript:void(0)'>删除订单</a></div></li>");
				}
				
			},'json')
		})
		
		/* 删除订单 */
		$(".deleteOrderBtn").live("click",function(){
			if(confirm("确定删除订单吗？")){
				var orderId=$(this).attr("name");
				var orderObj=$("#order"+orderId);
				var url="${root}/user/order/deleteOrderById";
				$.post(url,{"orderId":orderId,"status":10},function(returnMessage){
					if(returnMessage.resultCode==0){
						orderObj.remove();
						window.location.reload();
					}else{
						alert(returnMessage.message);
					}
				},'json')
			}
		})
		/* 确认收货 */
		$(".confirmGetOrder").live("click",function(){
			var orderId=$(this).attr("name");
 			var obj=$(this).parent().parent().parent().parent().parent().parent();
// 			var obj=$("#order"+orderId);
			var url="${root }/user/order/updateOrderStatus";
			$.post(url,{"orderId":orderId,"status":11},function(returnMessage){
				/* alert(returnMessage.message) */
				if(returnMessage.resultCode==0){
					obj.remove();
					var html="";
					html+="<li class='td td-status'><p class='Mystatus'>交易成功</p><div class='item-status'>";
					html+="<p class='order-info'><a href='orderinfo.html'>订单详情</a></p>";
					html+="<p class='order-info'><a href='logistics.html'>查看物流</a></p>";
					html+="</div></li><li class='td td-change'><div class='am-btn am-btn-danger anniu'>";
					html+="<a class='deleteOrderBtn' name='"+orderId+"' href='javascript:void(0)' >删除订单</a></div></li>";
					obj.html(html);
				}
			},'json')
		})
		
		
		$(".pingJia").live("click",function(){
			$("#prePingJiaAndHuiFu").empty();
			$(".editorId").empty();
			var orderItemId=$(this).attr("name");
			$("#orderItemId").val(orderItemId);
			var url="${root}/user/pingJia/selectPingJiaByOrderItem";
			$.post(url,{"orderItemId":orderItemId},function(pingJiaWithPingHui){
				if(!$.isEmptyObject(pingJiaWithPingHui)){
					var pingJiaId=pingJiaWithPingHui.pingJiaId;
					$("#pingJiaId").val(pingJiaId);
					/* $(".pingJia").attr("id",pingJiaId); */
					var html="";
					var pin_hui=pingJiaWithPingHui.pingHuiWithRoleList;
					var prePingJiaAndHuiFu=$("#prePingJiaAndHuiFu");
					for(var i=0;i<pin_hui.length;i++){
						if(!$.isEmptyObject(pin_hui[i]))
							html+=dateFtt("yyyy-MM-dd hh:mm:ss",new Date(pin_hui[i].description.createdate))+"<br/>"
							html+=pin_hui[i].role+":"+pin_hui[i].description.txt+"<br/>";
					}
					prePingJiaAndHuiFu.html(html);
				}
			},'json')
		})
		
		
		$("#addPingJiaToMongo").live("click",function(){
			var orderItemId=$("#orderItemId").val();
			//$("#editor_id").text();
			var pingJiaObj=$(this).parent().parent().parent();
			var description=$(".editorId").val();
			var pingJiaId=$("#pingJiaId").val();
			if(pingJiaId.length>0){
				var url="${root}/user/pingJia/addZhuiPing";
				$.post(url,{"pingJiaId":pingJiaId,"description":description},function(res){
					/* alert(res.resultCode) */
					if(res.resultCode==1){
						alert("已经评价两次，不能再次评价~")
					}
					
				},'json');
			}else{
				var url="${root}/user/pingJia/addPingJia";
				$.post(url,{"orderItemId":orderItemId,"description":description},function(res){
					alert(res.message)
				
				},'json');
			} 
		})
	
		
		$(".tui").live("click",function(){
			$("#tuihuodiv").empty();
			$("#tuibutton").empty();
			$(".editorId").empty();
			var snorders=$(this).attr("name");
			$("#snorders").val(snorders);
			var goods=$(this).prop("rel");
			$("#goods").val(goods); 
			/* alert(snorders+"dingdan") */
			var url="${root}/user/order/selectThorderitemsBysnOrders";
			$.post(url,{"snorders":snorders},function(res){
				 if(!$.isEmptyObject(res)){
					var tuihuodiv=$("#tuihuodiv");
					var html="";
					if(res.shenhestatus==0){
						 html+="<span>正在审核，请耐心等待.....</span>";
					}
					if(res.shenhestatus==1){
						html+="<span>退货审核已通过，请耐心等待退款</span>";
					}
					tuihuodiv.html(html);
				}else{
					/*<button type="button" class="btn btn-primary" data-dismiss="modal" id="addTui"   >提交申请</button> --> */
					var tuihuodiv=$("#tuihuodiv");
					var tuibutton=$("#tuibutton");
					var html="";
					var tuihtml="";
					html+="退款状态:<select id='tuisel'>";
					html+="<c:forEach items='${lstuiStatus}' var='l'>";
					html+="<option value='${l.id}'>${l.statusname}</option>";
					html+="</c:forEach> </select>";
					html+="<hr/>";
					html+="<h3>退款原因：</h3>";
					html+="<textarea class='text'  rows='' cols='' style='width:560px;height: 350px;'></textarea>";
					tuihuodiv.html(html);
					tuihtml+="<button type='button' class='btn btn-primary' data-dismiss='modal'  id='addTui'   >提交申请</button>"
					tuibutton.html(tuihtml);
				} 
			},'json')
		})
	
	$("#addTui").live("click",function(){
		var snorders=$("#snorders").val();
		var status=$("#tuisel").val();
		var yuanyin=$(".text").val();
		var goods=$("#goods").val();  
		var url="${root}/user/order/addtuihuo";
		$.post(url,{"snorders":snorders,"status":status,"yuanyin":yuanyin,"goods":goods},function(res){
			/* alert(res.resultCode)	
			alert(res.message) */	
		},'json'); 
	
		
	})	
	$(".pay").live("click",function(){
		var orderId=$(this).attr("name");
// 		window.location.href="${root}/account/listGoodSrevlet?storeId="+storeId+"&souSuo="+search;
		window.location.href="${root}/pay/gopay?orderId="+orderId;
		
	})	
	
	
	
	
	
	})




</script>

</head>
<body>
		
		<!--头 -->
		<header>
			<article>
				<div class="mt-logo">
					<!--顶部导航条 -->
					<div class="am-container header" style="margin-left: -10px">
						<jsp:include page="../../member/top.jsp"></jsp:include>
						</div>
						<!--悬浮搜索框-->

						<div class="nav white">
							<div class="logoBig">
								<li><img width="120px" height="80px" src="${resources}img/logo.png" /></li>
							</div>

							<div class="search-bar pr">
								<a name="index_none_header_sysc" href="#"></a>
								<form>
									<input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
									<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
								</form>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</article>
		</header>
     
		<b class="line"></b>

	<div class="center">
		<div class="col-main">
			<div class="main-wrap">

				<div class="user-order">

					<!--标题 -->
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">订单管理</strong> / <small>Order</small>
						</div>
					</div>
					<hr />

					<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

						<ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
							<li class="am-active"><a href="${toot }/user/order/listMyOrder">所有订单</a></li>
							<li><a  class="tab"  name="1"  href="#tab2">待付款</a></li>
							<li><a  class="tab"  name="2" href="#tab3">待发货</a></li>
							<li><a class="tab"  name="3"  href="#tab4">待收货</a></li>
							<li><a  class="tab"  name="11" href="#tab5">待评价</a></li>
						</ul>

						<div class="am-tabs-bd">
							<div class="am-tab-panel am-fade am-in am-active" id="tab1">
								<div class="order-top">
									<div class="th th-item">
										<span class="td-inner">商品</span>
									</div>
									<div class="th th-price">
										<span class="td-inner">单价</span>
									</div>
									<div class="th th-number">
										<span class="td-inner">数量</span>
									</div>
									<div class="th th-operation">
										<span class="td-inner">商品操作</span>
									</div>
									<div class="th th-amount">
										<span class="td-inner">合计</span>
									</div>
									<div class="th th-status">
										<span class="td-inner">交易状态</span>
									</div>
									<div class="th th-change">
										<span class="td-inner">交易操作</span>
									</div>
								</div>

								<div class="order-main">
									<div class="order-list">
										
										<c:forEach items="${orderWithOrderItemList }" var="orderWithOrderItem" >
											<div class="order-status5" id="order${orderWithOrderItem.orderId }">
												<div class="order-title">
													<div class="dd-num">
														订单编号：<a href="javascript:;">${orderWithOrderItem.sn }</a>
													</div>
													<span>成交时间：<fmt:formatDate  pattern="yyyy-MM-dd hh:mm:ss" value="${orderWithOrderItem.createdate }"></fmt:formatDate>   </span>
													 <em>店铺：${orderWithOrderItem.store.name }</em>
												</div>
												<div class="order-content"   >
													<div class="order-left">
													<c:forEach  items="${orderWithOrderItem.orderItemsWithGoodsList }"  var="orderItemsWithGoods"> 
													
													<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a  class="J_MakePoint">
																	 <img  name="${orderItemsWithGoods.goodsAll._id}" src="${resources}upload/${orderItemsWithGoods.goodsAll.coverimg }"	class="itempic J_ItemImg"></a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="${root}/member/goXiangqing?id=${orderItemsWithGoods.goodsAll._id}">
																			<p>${orderItemsWithGoods.goodsAll.fullname }</p>
																			<p class="info-little">${orderItemsWithGoods.goodsAll.description }</p>
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">${orderItemsWithGoods.price}</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${orderItemsWithGoods.num}
																</div>
															</li>
															<c:choose>
															<c:when test="${orderWithOrderItem.status eq 1 }">
															<li class="td td-operation">
																<div class="item-operation"> </div>
															</li>
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 2 }">
																<li class="td td-operation">
																<div class="item-operation"><a class="tui" rel="${orderItemsWithGoods.goods}"  name="${orderItemsWithGoods.id}" data-toggle="modal" data-target="#myTui"  href="javascript:void(0)" > 退款退货</a></div>
															</li>
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 3 }">
																<li class="td td-operation">
																<div class="item-operation"></div>
															</li>
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 10 }">
																<li class="td td-operation">
																<div class="item-operation"></div>
															</li>
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 11 }">
																<li class="td td-operation">
																<div class="item-operation">
																<a name="${orderItemsWithGoods.id }"
																 data-toggle="modal" data-target="#myModal"   
																  href="javascript:void(0)" class="pingJia" >
																  评价商品 </a>
<%-- 																  <a name="${orderItemsWithGoods.id }" data-toggle="modal" data-target="#myModal"    href="javascript:void(0)" class="pingJia" >  评价商品 </a> --%>
																<br/><a class="tui" rel="${orderItemsWithGoods.goods}"  name="${orderItemsWithGoods.id}" data-toggle="modal" data-target="#myTui"  href="javascript:void(0)" > 退款退货</a></div>
																	
																</li>
															</c:when>
															</c:choose>
															
														</ul>
														
													  </c:forEach>
														
													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：${orderWithOrderItem.sum }
															</div>
														</li>
														<div class="move-right">
															<c:choose>
															
															<c:when test="${orderWithOrderItem.status eq 1 }">
															<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">等待买家付款</p>
																	<p class="order-info">
																	<a  class="cancelOrder"  
																	name="${orderWithOrderItem.orderId}"  href="javascript:void(0)">
																	取消订单</a>
																	</p>

																</div>
															</li>
															<li class="td td-change">
<!-- 																<a href='javascript:void(0)' name='"+list[i].orderId+"'  class='pay'></a> -->
																<a href="javascript:void(0)" name="${orderWithOrderItem.orderId}" class="pay" >
																<div class="am-btn am-btn-danger anniu pay">
																	一键支付</div></a>
															</li>
															
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 2 }">
																	<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">买家已付款</p>
																	<p class="order-info"><a href="orderinfo.html">订单详情</a></p>
																</div>
															</li>
															<li class="td td-change">
																<div class="am-btn am-btn-danger anniu fahuo">
																	提醒发货</div>
															</li>
															
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 3 }">
																<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">卖家已发货</p>
																	<p class="order-info"><a href="orderinfo.html">订单详情</a></p>
																	<p class="order-info"><a href="logistics.html">查看物流</a></p>
																	<p class="order-info"><a href="#">延长收货</a></p>
																</div>
															</li>
															<li class="td td-change">
															
																<div class="am-btn am-btn-danger anniu">
																	<a  class="confirmGetOrder" name="${orderWithOrderItem.orderId}"  href="javascript:void(0)">确认收货</a>
																</div>
															
															</li>
															
															
															
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 10 }">
																<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">交易关闭</p>
																</div>
															</li>
															<li class="td td-change">
																<div class="am-btn am-btn-danger anniu">
																	<a class="deleteOrderBtn" name="${orderWithOrderItem.orderId}" href="javascript:void(0)" >删除订单</a></div>
															</li>
															
															
															</c:when>
															<c:when test="${orderWithOrderItem.status eq 11 }">
																<li class="td td-status">
																<div class="item-status">
																	<p class="Mystatus">交易成功</p>
																	<p class="order-info"><a href="orderinfo.html">订单详情</a></p>
																	<p class="order-info"><a href="logistics.html">查看物流</a></p>
																</div>
															</li>
															<li class="td td-change">
																<div class="am-btn am-btn-danger anniu">
																	<a class="deleteOrderBtn" name="${orderWithOrderItem.orderId}" href="javascript:void(0)" >删除订单</a></div>
															</li>
															</c:when>
															</c:choose>
															
																
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
										
									</div>

								</div>

							</div>

							<div class="am-tab-panel am-fade" id="tab2"></div>
							<div class="am-tab-panel am-fade" id="tab3"></div>
							<div class="am-tab-panel am-fade" id="tab4"></div>
							<div class="am-tab-panel am-fade" id="tab5"></div>

						</div>

					</div>
				</div>
			</div>



			<!--底部-->
			<div class="footer">
				<div class="footer-hd">
					<p>
						<a href="#">EGO</a> <b>|</b> <a href="#">商城首页</a> <b>|</b> <a
							href="#">支付宝</a> <b>|</b> <a href="#">物流</a>
					</p>
				</div>
				<div class="footer-bd">
					<p>
						<a href="#">关于EGO</a> <a href="#">合作伙伴</a> <a href="#">联系我们</a> <a
							href="#">网站地图</a> <em>© 2015-2025 <a
							href="http://www.EGO.com/" target="_blank" title="EGO">EGO之家</a>
							- Collect from <a href="http://www.EGO.com/" title="EGO"
							target="_blank">EGO</a></em>
					</p>
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
						<li><a href="${root}/user/person/golist?loginname=${user.loginname}">个人信息</a></li>
						<li><a href="safety.html">安全设置</a></li>
						<li><a href="address.html">地址管理</a></li>
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

		</aside>
	</div>
	
		<!-- 评价的motai -->
		<div class="modal fade"   aria-hidden="true" data-backdrop="static" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" >
				<div class="modal-content" style="width: 680px;">
					<div class="modal-header"  >
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" style="text-align: center;">评价商品</h4>
					</div>
					<div  id="prePingJiaAndHuiFu">
					
					</div>
					<div class="modal-body" >
						 <hr/>
							<textarea class="editorId"  rows="" cols="" style="width:560px;height: 350px;"></textarea>
							
							<textarea rows="" cols="" style="display: none" id="hiddenText"></textarea>
						</div>
						<input id="orderItemId" value="" type="hidden">
						<input id="pingJiaId" value="" type="hidden">
					
					<div class="modal-footer" align="center" >
						<button type="button" class="btn btn-primary" data-dismiss="modal" id="addPingJiaToMongo"   >提交评价</button>
					</div>
				</div>
			</div>
		</div>
		
		<!--退款退货的模态框  -->
		<div class="modal fade"   aria-hidden="true" data-backdrop="static" id="myTui" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" >
				<div class="modal-content" style="width: 680px;">
					<div class="modal-header"  >
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel" style="text-align: center;">退款退货</h4>
					</div>
					<div class="modal-body" >
						<div id="tuihuodiv">
							
						</div>
					</div>
					
					<input id="snorders" value="" type="hidden">
					<input id="goods" value="" type="hidden">	
					<div class="modal-footer" align="center" >
						<div id="tuibutton">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>


