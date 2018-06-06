<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<link href="${css}/style.css" rel="stylesheet" type="text/css" />
<link  type="text/css" rel="stylesheet"  href="${css}/style2.css"/>
<link  type="text/css" rel="stylesheet"  href="${css}/index.css"/>
<script  src="${js}/jquery.min.js"></script>
<style type="text/css">
body {
	background:#FFF
}
</style>
</head>

<body>
 
<div id="contentWrap">
<div class="pageTitle"></div>
<div class="pageColumn">
 <div class="right-menu">
  <div class="main-hd">
   <div class="page-teb" style="height:887px;">
    <div class="l-tab-links">
     <ul style="left:0;">
      <li class="l-select">
       <a href="${root}/admin/common/main" target="right" style="padding:0 25px;">首页</a>
      </li>
     </ul>
    </div>
    <div class="l-tab-content" style="height:851px;">
     <div class="tab-content-item">
      <div class="home">
      <!--成交金额-->
       <div class="space-style">
        <div class="col-xs">
         <a  href="${root}/admin/order/list3" target="right" class="title-button bg-deep">
          <div class="carousel">
           <div class="left-img">
            <i><img src="${img}/jiaose/left-img1.png"></i>
            <p>成交金额</p>
           </div>
           <div class="right-info"><f:formatNumber value="${adminMessage.totalSum}" pattern="#,###.00元"></f:formatNumber></div>
          </div>
         </a>
        </div>
       
         <div class="col-xs">
         <a  href="${root}/admin/order/list3" target="right" class="title-button bg-red">
          <div class="carousel">
           <div class="left-img bg-color-red">
            <i><img src="${img}/jiaose/left-img2.png"></i>
            <p>订单</p>
           </div>
           <div class="right-info">${adminMessage.totalNum}笔</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="${root}/admin/message/answer" target="right" class="title-button bg-green">
          <div class="carousel">
           <div class="left-img bg-color-green">
            <i><img src="${img}/jiaose/left-img3.png"></i>
            <p>通知</p>
           </div>
           <div class="right-info">${adminMessage.noAnswerNum}条</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="${root}/admin/order/list2" target="right" class="title-button bg-orange">
          <div class="carousel">
           <div class="left-img bg-color-orange">
            <i><img src="${img}/jiaose/left-img4.png"></i>
            <p>待处理</p>
           </div>
           <div class="right-info">${adminMessage.uncheckNum}条</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="${root}/admin/message/list" target="right" class="title-button bg-purple">
          <div class="carousel">
           <div class="left-img bg-color-purple">
            <i><img src="${img}/jiaose/left-img5.png"></i>
            <p>留言</p>
           </div>
           <div class="right-info">${adminMessage.messageNum}条</div>
          </div>
         </a>
        </div>
        
         <div class="col-xs">
         <a  href="${root}/admin/home/list" class="title-button bg-yellow">
          <div class="carousel">
           <div class="left-img bg-color-yellow">
            <i><img src="${img}/jiaose/left-img6.png"></i>
            <p>紧急警告</p>
           </div>
           <div class="right-info">${adminMessage.warn}条</div>
          </div>
         </a>
        </div>
       </div>

       <!--销售情况-->
       <div class="order-form">
     <!--    <div class="col-xs-3 col-lg-7">
         <div class="admin-info">
          <div class="title-name">
           <i></i>
            登录记录
           <a href="#">+更多</a>
          </div>
          <table class="record-list">
           <tbody>
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
            
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
            
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
            
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
            
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
            
            <tr>
             <td>管理员</td>
             <td>2017-05-26</td>
            </tr>
           </tbody>
          </table>
         </div>
        </div> -->
        
        <div class="col-xs-6 ranking-style">
         <div class="frame">
          <div class="title-name">
            <i></i>
            商品热度排行
           <a href="#">+更多</a>
          </div>
          <table class="table table-list">
           <thead>
            <tr>
             <th width="50">排名</th>
             <th>商品名称</th>
             <th>商品类型</th>
             <th width="80">收藏数量</th>
            </tr>
           </thead>
           <tbody>
          <c:forEach items="${collectList}" var="c" varStatus="v">
            <tr>
	             <td>
	              <em>${v.count}</em>
	             </td>
	             <td>${c.goods.fullname}</td>
	             <td><a href="#">${c.goods.type}</a></td>
	             <td>${c.countPeopleNum}</td>
            </tr>
          </c:forEach>
           </tbody>
          </table>
         </div>
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
</div>
</div></div>
</body>
</html>