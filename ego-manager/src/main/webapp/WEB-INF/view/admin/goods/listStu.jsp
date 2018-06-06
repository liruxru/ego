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
$(function(){	
	//单个下架的方法
	$(".del").click(function(){
		var id = $(this).attr("name");
		var url="${pageContext.request.contextPath}/admin/goodsStu/delete";
		 if(confirm("是否操作")){
	         $.post(url,
	               {"id":id},
	               function(res){		            
	                 if (res.code==0) {
	                	 window.location.reload();
					 }else{
					    alert("上架失败")
					 }
	               },
	               'json');	       
	         }	     	
		
	})
	
	 //全选的方法
    $("#allChk").click(function(){
      var obj = $(".ch");
      for(var i =0;i<obj.length;i++){
         obj.prop("checked", $("#allChk").prop("checked"));
      }	
      changDel();     
    })
    
    $(".ch").click(function(){
       changDel();   
    })  
    
    //跳转方法
    $("#sub").click(function(){
       var page = $("#tiaozhuang").val();
        var re=/^\d*$/; 
        if(!re.test( page )||page>${totalPage}||page<1){
            alert("输入有误!");
            return;
        }
       goPage(page);
    })	     
    
	
})
//改变按钮的状态
	function changDel(){
	   var ButObj = document.getElementById("but");
	   var obj    = document.getElementsByName("ch");
	   var j = 0;
	   for(var i=0; i<obj.length;i++){
	       if(obj[i].checked){
	           j++;
	       }   
	   }
	   if(j>1){
	     ButObj.disabled="";
	   }else{
	     ButObj.disabled="disabled";
	   }
	
	
	}
function but() {
	if(confirm("是否上架这些数据？")){
		document.delForm.submit();
	}else{
		return;
	}
}

function goPage(currentPage){
    window.location.href= "${root}"+"/admin/goodsStu/list?pageCode="+currentPage;	
}
</script>
</head>
<body>
    <c:if test="${empty listGood}">
		<h1>您还没有已下架的商品</h1>
	</c:if>			
				  
	<c:if test="${not empty listGood}">
							  
<div class="table_div">
		<div class="div_clear">
			<div class="center_top">
				<div style="float:right;padding-right:6px">
					<%-- <img width='16' height='16' src="${img}/table/20160414160909739.gif" style="vertical-align:middle" />
					<a href="${pageContext.request.contextPath}/admin/goodsStu/edit?pageCode=${page.pageCode}">新增</a>&nbsp;  --%>
					<%-- <img width='16' height='16' src="${img}/table/20160414160914661.gif" style="vertical-align:middle" />
					<a href="#">修改</a>&nbsp;  --%>
					<img width='16' height='16' src="${img}/table/20160414160859494.gif"	style="vertical-align:middle" />
					<input type="button" value="批量上架" id="but" onclick="but()" disabled="disabled" />
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="center_center">
				<div class="table_content">
				
					<table cellspacing="0px" cellpadding="0px">
						<thead>
							<tr>
								<th>全选 <input type="checkbox" id="allChk" onclick="All(this)" /></th>
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
								<th style="border-right:none">操作</th>
							</tr>
						</thead>
						<tbody>
						
							<form action="${pageContext.request.contextPath}/admin/goodsStu/deleteAll" method="post" name="delForm">
							<c:forEach items="${listGood}" var="g" varStatus="v">
							<tr>
								<td><input type="checkbox" onclick="chi()" class="ch" name="ch" value="${g._id}" /></td>
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
								<td style="border-right:none">
									<%-- <img width='16' height='16'	src="${img}/table/20160414160935161.gif" style="vertical-align:middle" />
									<a href="${pageContext.request.contextPath}/admin/goodsStu/edit?_id=${g._id}&pageCode=${page.pageCode}">修改</a>&nbsp; --%>
									<img width='16' height='16' src="${img}/table/20160414160926864.gif" style="vertical-align:middle" />
									<a  href="javascript:void(0)" name="${g._id}" class="del">上架</a>
								</td>
							</tr>
						    </c:forEach>
						</form>
						</tbody>
						<!-- <input type="hidden"/> -->
					</table>
				</div>
			</div>
		</div>
		<div class="div_clear">
			<div class="left_bottom"></div>
			<div class="center_bottom">
				<span>总页数:${totalPage}，当前页第${page.pageCode}/${totalPage}页，总记录数:${page.count}</span>
				<div style="float:right;padding-right:30px">
				<c:if test="${page.pageCode ne 1}">				
					<a href="javascript:goPage(1)"><input type="button" value="首页" /></a>
				</c:if>
				 <c:if test="${page.pageCode ne 1}">
					<a href="javascript:goPage(${page.pageCode-1})"><input type="button" value="上页"/></a>
				</c:if> 
				<c:if test="${page.pageCode ne totalPage}">
					<a href="javascript:goPage(${page.pageCode+1})"><input type="button" value="下页"/></a>
				</c:if> 
				<c:if test="${page.pageCode ne totalPage}">
					<a href="javascript:goPage(${totalPage})"><input type="button" value="尾页"/></a>
				</c:if>					
				<input type="text" size="1" id="tiaozhuang" /> 
				<a  href="javascript:void(0)" ><input type="button" value="跳转" id="sub"/></a>
				</div>
			</div>
		</div>
	</div>
</body>
							  
</c:if>
</html>