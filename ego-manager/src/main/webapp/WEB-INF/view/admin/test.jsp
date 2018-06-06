<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${root}/resources/kindeditor/themes/default/default.css">
	<link rel="stylesheet" type="text/css" href="${root}/resources/kindeditor/plugins/code/prettify.css">
	
	
	<script type="text/javascript" src="${root}/resources/kindeditor/kindeditor-all-min.js"></script>
	<script type="text/javascript" src="${root}/resources/kindeditor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="${root}/resources/kindeditor/plugins/code/prettify.js"></script>
  	<script>
        KindEditor.ready(function(K) {
			var editor1 = K.create('#editor_id', {
				cssPath : '<%=path%>/css/prettify.css',
				uploadJson : '${root}/resources/kindeditor/upload_json.jsp',
                fileManagerJson : '${root}/resources/kindeditor/file_manager_json.jsp'
			});
		});
</script>
  </head>
  
  <body>
  	<form action="<%=path%>/save" method="post">
    	<textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
    	<input type="submit"/>
  	</form>
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