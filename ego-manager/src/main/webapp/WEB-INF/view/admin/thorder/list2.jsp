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
	    $(".confirmGetOrder").click(function(){
	    	var id = $(this).attr("name");
	    	alert(id);
	    	var url ="${root}/admin/thorder/tuihuo";
	    	if(confirm("是否确认同意？")){
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
        window.location.href= "${root}"+"/admin/thorder/list2?pageCode="+currentPage;	
    }
   </script>
  </head>
  
  <body>
  <c:if test="${empty thorderList}">
  <h1>暂时没有待审核的退单！</h1>
  </c:if>
  <c:if test="${not empty thorderList}">
  
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
						<span class="td-inner">退货原因</span>
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
						
						<c:forEach items="${thorderList}" var="orderWithOrderItem" >
							<div class="order-status5">
								<div class="order-title">
									<div class="dd-num">
										退单编号：<a href="javascript:;">${orderWithOrderItem.sn}</a>
									</div>
									<span>申请时间：<fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${orderWithOrderItem.creadedate}"></fmt:formatDate>   </span>
									<%--  <em>店铺：${orderWithOrderItem.store.name}</em> --%>
								</div>
								<div class="order-content"   >
									<div class="order-left">
									<ul class="item-list">
											<li class="td td-item">
												<div class="item-pic">
													<a href="#" class="J_MakePoint">
													 <img	src="${root}/resources/upload/${orderWithOrderItem.goodscoverimg}"	class="itempic J_ItemImg"></a>
												</div>
												<div class="item-info">
													<div class="item-basic-info">
														<a href="#">
															<p>${orderWithOrderItem.fullname}</p>
															<p class="info-little">${orderWithOrderItem.goodsdescription}</p>
														</a>
													</div>
												</div>
											</li>
											<li class="td td-price">
												<div class="item-price">${orderWithOrderItem.goodsprice}</div>
											</li>
											<li class="td td-number">
												<div class="item-number">
													<span>×</span>
													1
												</div>
											</li>
										
											
											<li class="td td-operation">
											    <div class="item-operation">已退款</div>
										    </li>	
										</ul>
										
										
									</div>
									<div class="order-right">
										<li class="td td-amount">
											<div class="item-amount">
												${orderWithOrderItem.yuanyin}
											</div>
										</li>
										<div class="move-right">
										   <li class="td td-change">
													<a  href="javascript:void(0)">已通过</a>												
											</li>
											<li class="td td-change">
												<div class="am-btn am-btn-danger anniu">
													<a  class="confirmGetOrder" name="${orderWithOrderItem.id}"  href="javascript:void(0)">删除记录</a>
												</div>
											</li>
												
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						
					</div>

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
