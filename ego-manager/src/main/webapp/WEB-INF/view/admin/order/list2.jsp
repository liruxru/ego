<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
	<%-- <link rel="stylesheet" type="text/css" href="${css}/goodList.css"> --%>
	<link rel="stylesheet" href="${css}/order/admin.css" />
	<link rel="stylesheet" href="${css}/order/amazeui.css" />
	<link rel="stylesheet" href="${css}/order/orstyle.css" />
	<link rel="stylesheet" href="${css}/order/personal.css" />
	<link rel="stylesheet" type="text/css" href="${css}/bootstrap.min.css">
	<script type="text/javascript" src="${js}/jquery.min.js"></script>
	<script type="text/javascript" src="${js}/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function(){
	    $("#send").click(function(){
	    	var id = $(this).attr("name");
	    	/*alert(id); */
	    	var url ="${root}/admin/order/send";
	    	if(confirm("是否确认发货？")){
	    		$.post(url,
	    			  {"id":id},
    				  function(res){
	    				  if (res.code==0) {
	 	                	 window.location.reload();
	 					 }else{
	 					    alert(res.message);
	 					 }
    				  },'json');
	    	}else{
	    		return;
	    	}
			
		})
    	
	})
    
    function goPage(currentPage){
    	/* alert(currentPage); */
        window.location.href= "${root}"+"/admin/order/list2?pageCode="+currentPage;	
    }
   </script>
  </head>
  
  <body>
  <c:if test="${empty orderList}">
  <h1>暂时没有未支付的订单！</h1>
  </c:if>
  <c:if test="${not empty orderList}">
	<div class="am-tabs-bd">
	
		<div class="am-tab-panel am-fade am-in am-active" id="tab1">
			<div class="order-top">
				<div class="th th-item">
					<td class="td-inner">商品</td>
				</div>
				<div class="th th-price">
					<td class="td-inner">单价</td>
				</div>
				<div class="th th-number">
					<td class="td-inner">数量</td>
				</div>
				<div class="th th-operation">
					<td class="td-inner">商品操作</td>
				</div>
				<div class="th th-amount">
					<td class="td-inner">合计</td>
				</div>
				<div class="th th-status">
					<td class="td-inner">交易状态</td>
				</div>
				<div class="th th-change">
					<td class="td-inner">交易操作</td>
				</div>
			</div>

			<div class="order-main">
			 <c:forEach items="${orderList}" var="o">
				<div class="order-list">
					<!--交易成功-->
						<div class="order-title">
							<div class="dd-num">订单编号：<a href="javascript:;">${o.sn}</a></div>
							<span>成交时间：<fmt:formatDate value="${o.createdate}" pattern="yyyy-MM月-dd日  HH:mm:ss"/></span>
						</div>
						<c:forEach items="${o.orderItemsWithGoodsList}" var="c">
							<div class="order-content">
								<div class="order-left">
									<ul class="item-list">
										<li class="td td-item">
											<div class="item-pic">
												<a href="#" class="J_MakePoint">
													<img src="${root}/resources/upload/${c.goodsAll.coverimg}" class="itempic J_ItemImg">
												</a>
											</div>
											<div class="item-info">
												<div class="item-basic-info">
													<a href="#">
														<p>${c.goodsAll.description}</p>
														<p class="info-little">店铺：${o.store.name}</p>
														<!-- <p class="info-little">颜色：12#川南玛瑙
															<br/>包装：裸装 </p> -->
													</a>
												</div>
											</div>
										</li>
										<li class="td td-price">
											<div class="item-price">
												${c.price}
											</div>
										</li>
										<li class="td td-number">
											<div class="item-number">
												<span>×</span>${c.num}
											</div>
										</li>
										<li class="td td-operation">
											<div class="item-operation">
												
											</div>
										</li>
									</ul>
								</div>
							 </div>
						</c:forEach>
							<div class="order-right">
								<li class="td td-amount">
									<div class="item-amount">
										合计：<fmt:formatNumber value="${o.sum}" pattern="#,###.00"></fmt:formatNumber><br>
										<p>免邮：<span>00.00</span></p>
									</div>
								</li>
								<div class="move-right">
									<li class="td td-status">
										<div class="item-status">
											<p class="Mystatus">待发货</p>
											<!-- <p class="order-info"><a href="orderinfo.html">订单详情</a></p>
											<p class="order-info"><a href="logistics.html">查看物流</a></p> -->
										</div>
									</li>
									<li class="td td-change">
										<div id="send" class="am-btn am-btn-danger anniu" name="${o.orderId}">
											确认发货</div>
									</li>
								</div>
							</div>
				</div>
			</c:forEach>
					    
			</div>
					
		</div>
		
	</div>
		



	<div class="center_bottom">
				<span>总页数:${page.pageCount}，当前页第${page.pageCode}/${page.pageCount}页，总记录数:${page.count}</span>
				<div style="float:right;padding-right:30px">
				<c:if test="${page.pageCode ne 1}">				
					<a href="javascript:goPage(1)"><input type="button" value="首页" /></a>
				</c:if>
				 <c:if test="${page.pageCode ne 1}">
					<a href="javascript:goPage(${page.pageCode-1})"><input type="button" value="上页"/></a>
				</c:if> 
				<c:if test="${page.pageCode ne page.pageCount}">
					<a href="javascript:goPage(${page.pageCode+1})"><input type="button" value="下页"/></a>
				</c:if> 
				<c:if test="${page.pageCode ne page.pageCount}">
					<a href="javascript:goPage(${page.pageCount})"><input type="button" value="尾页"/></a>
				</c:if>					
				<!-- <input type="text" size="1" id="tiaozhuang" /> 
				<a  href="javascript:void(0)" ><input type="button" value="跳转" id="sub"/></a> -->
			</div>
</div>
</c:if>
  </body>
</html>
