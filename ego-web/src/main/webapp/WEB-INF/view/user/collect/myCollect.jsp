<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
<title>我的收藏</title>


<link rel="stylesheet" href="${css}order/admin.css"  type="text/css" />
<link rel="stylesheet" href="${css}order/amazeui.css" type="text/css"  />

<link rel="stylesheet" href="${css}order/personal.css"  type="text/css" />
<link rel="stylesheet"  href="${css}order/colstyle.css" type="text/css">	
<script type="text/javascript" src="${js}/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".deleteCollect").live("click",function(){
			
			var goodsId=$(this).attr("name");
			/* alert(goodsId) */
			var url="${root}/user/collect/deleteFromCollect";
			$.post(url,{"goodsId":goodsId},function(res){
				/* alert(res.resultCode) */
				if(res.resultCode==0){
					window.location.reload();  
				}
			},'json')
			
		})
		
		$(".addCart").live("click",function(){
			
			var goodsId=$(this).attr("name");
			/* alert(goodsId) */
			var url="${root}/user/cart/addToCart";
			$.post(url,{"goodsId":goodsId},function(res){
				/* alert(res.resultCode) */
				if(res.resultCode==0){
					alert("添加成功")
				}
			},'json')
			
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
					<div class="am-container header">
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
					<div class="user-collection">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">我的收藏</strong> / <small>My&nbsp;Collection</small></div>
						</div>
						<hr/>
						
						
						<div class="you-like">
							<div class="s-bar">
								我的收藏
							</div>
							<div class="s-content">
								<c:forEach items="${goodsList}" var="goods">
										
										<div class="s-item-wrap">
											<div class="s-item"  >
												<div class="s-pic" >
													<a href="${root}/member/goXiangqing?id=${goods._id}" class="s-pic-link">
														<img style="width:150px;height: 150px;" src="${resources}/upload/${goods.coverimg}" alt="" title="${goods.fullname }" class="s-pic-img s-guess-item-img">
													</a>
												</div>
												<div class="s-info">
													<div class="s-title"><a href="#" title="${goods.fullname }">${goods.fullname }</a></div>
													<div class="s-price-box">
														<span class="s-price">现价<em class="s-price-sign">¥</em><em class="s-value"><fmt:formatNumber pattern="##.00">${goods.sale }</fmt:formatNumber>  </em></span>
														<span class="s-history-price">原价<em class="s-price-sign">¥</em><em class="s-value"><fmt:formatNumber pattern="##.00">${goods.price }</fmt:formatNumber></em></span>
													</div>
													<div class="s-extra-box">
														<span class="s-comment">关注人数:</span><br/>
													</div>
												</div>
												<div class="s-tp">
														<span class="ui-btn-ding-before buy"> <a  class="addCart" name="${goods._id}"   href="javascript:void(0)">加入购物车</a>   </span>
														<span><a href="javascript:void(0)" name="${goods._id}" class="c-nodo J_delFav_btn deleteCollect">取消收藏</a></span>
														
												</div>
												
											</div>
										</div>
										
										
									</c:forEach>
							</div>
							
						
						
						</div>
						
						<div class="s-more-btn i-load-more-item" data-screen="0"><i class="am-icon-refresh am-icon-fw"></i>更多</div>
					</div>
					
				
				
				</div>
					
					<!--底部-->
					<div class="footer">
						<div class="footer-hd">
							<p>
								<a href="#">商城首页</a>
								<b>|</b>
								<a href="#">支付宝</a>
								<b>|</b>
								<a href="#">物流</a>
							</p>
						</div>
						<div class="footer-bd">
							<p>
								<a href="#">合作伙伴</a>
								<a href="#">联系我们</a>
								<a href="#">网站地图</a>
							</p>
						</div>
					</div>
				
			</div>
			
			
					
			<aside class="menu">
				<ul>
					<li class="person active">
						<a href="${root}/user/person/golist?loginname=${user.loginname}"><i class="am-icon-user"></i>个人中心</a>
					</li>
					<li class="person">
						<p><i class="am-icon-newspaper-o"></i>个人资料</p>
						<ul>
							<li> <a href="${root}/user/person/golist?loginname=${user.loginname}">个人信息</a></li>
							<li> <a href="safety.html">安全设置</a></li>
							<li> <a href="address.html">地址管理</a></li>
							<li> <a href="cardlist.html">快捷支付</a></li>
						</ul>
					</li>
					<li class="person">
						<p><i class="am-icon-balance-scale"></i>我的交易</p>
						<ul>
							<li><a href="${root}/user/order/listMyOrder">订单管理</a></li>
							<li> <a href="change.html">退款售后</a></li>
							<li> <a href="comment.html">评价商品</a></li>
						</ul>
					</li>
					<li class="person">
						<p><i class="am-icon-dollar"></i>我的资产</p>
						<ul>
							<li> <a href="points.html">我的积分</a></li>
							<li> <a href="coupon.html">优惠券 </a></li>
							<li> <a href="bonus.html">红包</a></li>
							<li> <a href="walletlist.html">账户余额</a></li>
							<li> <a href="bill.html">账单明细</a></li>
						</ul>
					</li>

					<li class="person">
						<p><i class="am-icon-tags"></i>我的收藏</p>
						<ul>
							<li> <a href="${root}/user/gomyCollect">收藏</a></li>
							<li> <a href="${root}/user/liuLan/findLiuLan">足迹</a></li>
						</ul>
					</li>
				</ul>

			</aside>
	
		</div>

</body>
</html>