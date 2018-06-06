<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品全查页</title>
<link rel="stylesheet" type="text/css" href="${css}/goods/goodList.css">
<link rel="stylesheet" type="text/css" href="${css}/bootstrap.min.css">
<script type="text/javascript" src="${js}/jquery.min.js"></script>
<script type="text/javascript" src="${js}/bootstrap.min.js"></script>
<script type="text/javascript">
function goPage(currentPage){
	/* alert(currentPage); */
    window.location.href= "${root}"+"/admin/goods/list?pageCode="+currentPage;	
}
</script>
</head>
<body>
<div class="table_div">
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>No</th>
								<th>名称</th>
								<th>全称</th>
								<th>描述</th>
								<th>库存</th>
								<th>价格</th>
								<th>促销价</th>
								<th>成本价</th>
								<th>类型</th>
								<th>店铺</th>
								<th>上架</th>
								<!-- <th style="border-right:none">操作</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${goodsList}" var="g" varStatus="v">
							<tr>
								<td>${v.count}</td>
								<td>${g.name}</td>
								<td>${g.fullname}</td>
								<td>${g.description}</td>
								<td>${g.num}</td>
								<td>${g.price}</td>
								<td>${g.sale}</td>
								<td>${g.jinprice}</td>								
								<td>${g.type}</td>
								<td>${g.store}</td>
								<td>
								<c:if test="${g.lock eq 0}">已上架</c:if>
								<span style="color: red;"><c:if test="${g.lock ne 0}">已下架</c:if></span>
								</td>
								<%-- <td><ul><li><div>预览</div><img src="${pageContext.request.contextPath}/img/upload/${g.goodCoverimg}" /></li></ul></td> --%>
								<%-- <td style="border-right:none">
									<img width='16' height='16'	src="${img}/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/goods/edit?_id=${g._id}&pageCode=${page.pageCode}">修改</a>&nbsp;
									<img width='16' height='16' src="${img}/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${g._id}" class="del">下架</a>
								</td> --%>
							</tr>
						    </c:forEach>
						</tbody>
						<!-- <input type="hidden"/> -->
					</table>
				</div>
			</div>
		</div>
</div>
<div class="div_clear">
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
			</div>
		</div>
	</div>
</body>
</html>