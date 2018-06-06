<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言查看页</title>	
<style type="text/css">
body{
background-color: #ffffff;
}
</style>
</head>
<body>
<%-- 	<h1>${messagesList}</h1> --%>
	<div class="abright" style="color: #fffff">

		<c:forEach items="${messagesList}" var="m">
			<div class="pingJiadiv">
				<h5>用户${m.username}对${m.goodsfullname}的评价:</h5>
				<c:forEach items="${m.pin_hui_Entiy}" var="pin_hui">
					<div class="usertext">
						&nbsp;&nbsp; 用户评价：${pin_hui.key.txt}
						<div>
						<fmt:formatDate	value="${pin_hui.key.createdate}" pattern="yyyy-MM-dd HH:mm:ss" />
						</div><a  href="javascript:void(0)" class="pingJia" >
							 回复： </a>
						
				</div>
					<%-- <div class="usertext">
						<c:if test="${not empty pin_hui.value}">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											管理员的回复:${pin_hui.value.txt}<br />
							<span><fmt:formatDate value="${pin_hui.value.createdate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></span>
						</c:if>
					</div> --%>
				</c:forEach>
				<hr>
			</div>
		</c:forEach>
	</div>
</body>
</html>