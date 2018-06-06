<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言页</title>
<script type="text/javascript" src="${root}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${root}/resources/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${root}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${root}/resources/kindeditor/themes/default/default.css">
<link rel="stylesheet" type="text/css" href="${root}/resources/kindeditor/plugins/code/prettify.css">
<script type="text/javascript" src="${root}/resources/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${root}/resources/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript" src="${root}/resources/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">     
        $(function(){
          var pingJiaId = null;
          var descriptionId =null;
      	  $(".pingJia").click(function(){
      		 pingJiaId = $(this).attr("name");
      		 descriptionId = $(this).prev().attr("name");
      		 var obj = $(this).next();
      		 var html="<textarea class='form-control' rows='3'></textarea><input type='button' class='eee'  value='确定'><input type='hidden'  value='${m.id}'>";
      	     obj.html(html);
      	  })
      	  
      	  $(".eee").live("click",function(){
      		  /* 获取文本域中输入的内容 */
      		  var obj = $(this).prev();
      		  var description =obj.val();
      		  alert(description);
      		  
      		  /* 回复条目的 id*/
      		  alert(descriptionId);
      		  
      		  /*获取评价的id */
      		 /*  var pingJiaId = $(this).next().val(); */
      		  alert(pingJiaId);
      		 
      		 
      		var url ="${root}/admin/message/huifu";
      		$.post(url,
	    			  {"pingJiaId":pingJiaId,"description":description,"descriptionId":descriptionId},
  				    function(res){
	    				  if (res.code==0) {
	 	                	 window.location.reload();
	 					 }else{
	 					    alert(res.message);
	 					 }
  				  },'json');
      		  
      		  
      	  })
	
      	
       })
</script>
<style type="text/css">
	body{
	background-color: #ffffff;
	}
	a{
	text-decoration: none;
	}
</style>
</head>
<body>
	<div class="abright" style="color: #fffff">
	
		<c:forEach items="${messagesList}" var="m">
			<div class="pingJiadiv">
				<h5>用户${m.username}对${m.goodsfullname}的评价:</h5>
				<c:forEach items="${m.pin_hui_Entiy}" var="pin_hui" varStatus="v">
					<div class="usertext">
					    &nbsp;&nbsp;用户评价：${pin_hui.key.txt}
						<div>
						   <fmt:formatDate	value="${pin_hui.key.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
						<input type="hidden" name="${v.count}">
						<a href="javascript:void(0)" class="pingJia" name="${m._id}">回复： </a>
						<div class="message">
						  
						  
						</div>
				    </div>
				</c:forEach>
				<hr>
			</div> 
		</c:forEach>
		
	</div>
</body>
</html>