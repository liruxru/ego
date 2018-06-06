<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收银台</title>
<link rel="stylesheet" type="text/css" href="${resources}css/pays.css" />
<script type="text/javascript" src="${resources}js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<div>
		<div class="topRight" style="margin-left: 115px">
			<jsp:include page="../../member/top.jsp"></jsp:include>
		</div>
		<div class="logoNow">
			<a href="#"><img src="${resources}img/logo.png"
				style="width: 245px; height: 80px; top: -6px; position: relative; margin-right: 20px;"
				class="logo" /></a>&nbsp;&nbsp;<a id="syt">收银台</a>
		</div>
		<div class="order">
			<div class="ordert"></div>
			<div class="orderx">
				<ul>
					<li id="dd1" class="dd">请您尽快付款，我们将为您闪电送达！</li>
					<li id="dd2" class="dd" style="margin-bottom: 15px;">请您在&nbsp;&nbsp;<a><span>30分钟</span></a>&nbsp;&nbsp;内付款，逾期订单将被取消！
					</li>
					<li class="dd"
						style="border-top: 1px dotted #ccc; padding: 8px 0 5px;">应付金额：<a><span>￥${order.sum}</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单编号：${order.sn}
					</li>
				</ul>
			</div>
		</div>
		<div class="order" style="margin-top: 10px;">
			<form action="${root}/pay/dopay" method="post" name="payfrom">
				<table borderColor="red">
					<tr>
						<c:forEach items="${applicationScope.bank}" var="l"
							varStatus="num">
							<td><input type="radio"
								value="${fn:substring(l,0,fn:indexOf(l,'.'))}" class="inp"
								NAME="pd_FrpId" <c:if test="${num.count eq 1 }">checked</c:if>>
								<img src="${resources}img/bank/${l}"></td>
							<c:if test="${num.count%5==0}">
					</tr>
					<tr>
						</c:if>
						
						</c:forEach>
						 
					</tr>
				</table>
		</div>
	</div>
	 <%-- ${orderId} --%>
	<input type="hidden" value="0.01" name="price">
<%-- 	<input type="hidden" value="${orderId}" name="sn"> --%>
	<input type="hidden" name="orderId"  value="${order.id}"/>	
<%-- 	<input type="hidden" name="oId"  value="${orderId}"/> --%>
	<div class="payzf">
		<p>
			<img src="${resources}img/ljzf.png" id="ljzf" onclick="pay()">
		</p>
	</div>
	</form>
	<div class="zysx">
		<ul>
			<li id="zysxz" style="color: red;">注意事项：</li>
			<li id="zysxz">"订单提交成功"仅表明食全酒美网收到您的订单，只有您的订单通过审核后，才代表订单正式生效；</li>
			<li id="zysxz">请您务必认真检查所有货物，如有不符，您可以拒收；</li>
			<li id="zysxz">请您认真检查外包装。如有明显损坏迹象，您可以拒收该货品，并及时通知我们。</li>
		</ul>
	</div>
	<div style="height: 20px;"></div>
	<img src="${resources}img/bom.png" />
</body>
<script type="text/javascript">
function pay(){
	
	document.payfrom.submit();
}
</script>
</html>
