<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ page import="com.ego.mapper.po.*" %>
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
    
    <title>EGO</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${css}/mian.css" /> 
	<link rel="stylesheet" type="text/css" href="${css}/bootstrap.min.css"/>
    <link rel="stylesheet" href="${css}/searchgoods.css" type="text/css">	
	<link rel="stylesheet" href="${css}/nav.css" type="text/css">

	<script src="${js}/jquery.js"></script>
	<script src="${js}/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
	
	</script>
	
	
  </head>
  
  <body  onload="leftTimer()">
  		<jsp:include page="top.jsp"></jsp:include><!-- 动态导入 页面 -->
  		<div id="all">
  	 
		<div id="content">
			
			
			<div id="header">
			<div id="logo">
				<img alt="EGO商城" src="${resources}/img/main/EGOlogo.png">
			</div>
			<form action="${root}/member/search"  method="post">
				<div id="search-form">    
				<!-- class="glyphicon glyphicon-search" -->
					<input name="fullName" placeholder="请输入检索内容" /><button type="submit" class="btn btn-default btn-sm" style="color: white; background-color: red; font-size: 22px;"><span >搜索</span></button>
				</div>
			</form>
				<a href="${root}/user/cart/gomyCart"><div class="mycart">
					<div>我的购物车<img alt="" src="${resources}/img/main/gouwuche.png"></div>
				</div>
				</a>
				<div id="hot-words">
					<a href="${root}/member/search?fullName=联想">  联想      </a>
					<a href="${root}/member/search?fullName=DELL">  DELL </a>
					<a href="${root}/member/search?fullName=苹果">  苹果      </a>
					<a href="${root}/member/search?fullName=小苹果">  小苹果      </a>
					<a href="${root}/member/search?fullName=红苹果">  红苹果      </a>
					<a href="${root}/member/search?fullName=绿苹果">  绿苹果      </a>
					<a href="${root}/member/search?fullName=青苹果">  青苹果      </a>
					<a href="${root}/member/search?fullName=果">  果      </a>
					<a href="${root}/member/search?fullName=青萍">  青萍      </a>
				</div>
				<div id="vative-items">
					<ul>
						<c:forEach items="${typename}" var="l">
							<li><a href="${root}/member/search?typename=${l.name}">${l.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div id="listGoods">
						<div class="nav">
    <div class="nav-con">
        <div class="nav-con-left">
            <div class="nav-con-lefttext">
                <a href="#">全部商品分类</a>
            </div>
            <div class="nav-variety" id="things">
                <ul class="thing-variety" id="variety">
                		<c:forEach items="${typename}" var="l">
                			<li class="tab-item">
		                       <div class="thname">
		                           <h4><a href="${root}/member/search?typename=${l.name}">${l.name}</a></h4>
		                           <span>&gt;</span>
		                       </div>
	                   		</li>
                		</c:forEach>
                </ul>
                <div class="dropdown hiden">
                    <div class="fmaint clear hiden">                     
                        <div class="item-channels">
                            <div class="channels">
                                <a href="#">图书频道 <span>&gt;</span></a>
                                <a href="#">音响 <span>&gt;</span></a>
                                <a href="#">电子书 <span>&gt;</span></a>
                                <a href="#">图书榜 <span>&gt;</span></a>
                                <a href="#">音乐榜 <span>&gt;</span></a>
                                <a href="#">文娱众筹馆 <span>&gt;</span></a>
                            </div>
                        </div>
                        <div class="subitems">
                            <dl class="sub1">
                                <dt><a href="${root}/member/search">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub2">
                                <dt><a href="${root}/member/search">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub3">
                                <dt><a href="#">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub4">
                                <dt><a href="#">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub5">
                                <dt><a href="#">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub6">
                                <dt><a href="${root}/member/search">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                                              
                        </div>
	                    </div>
	                    
	                    <div class="fmaint clear hiden">
			                        <a href="${root}/member/search?fullname=' '"><img  class="fmaint-img"alt="datu  " src="${resources}/img/main/31254e8c3982fc31ffd4f7b7bda6919b.jpg"></a>
						</div> 
	                    
	                    
						<div class="fmaint clear hiden">
						   <div class="item-channels">
                            <div class="channels">
                                <a href="#">图书频道 <span>&gt;</span></a>
                                <a href="#">音响 <span>&gt;</span></a>
                                <a href="#">电子书 <span>&gt;</span></a>
                                <a href="#">图书榜 <span>&gt;</span></a>
                                <a href="#">音乐榜 <span>&gt;</span></a>
                                <a href="#">文娱众筹馆 <span>&gt;</span></a>
                            </div>
                        </div>
                        <div class="subitems">
                            <dl class="sub1">
                                <dt><a href="${root}/member/search">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub2">
                                <dt><a href="${root}/member/search">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                    <a href="${root}/member/search" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                             <dl class="sub3">
                                <dt><a href="#">众筹<span>&gt;</span></a></dt>
                                <dd>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                    <a href="#" target="_blank">众筹众筹</a>
                                </dd>
                            </dl>
                        </div>
						</div>  
								 
	                    
	                            
					                </div>
					            </div>
					        </div>
					        </div>
					    </div>
					</div>
					

            
            
            
				<!--
            	作者：offline
            	时间：2017-11-23
            	描述：轮播   轮播
            -->
				<div id="bigPic">
					<div id="lunbo">
						<div id="myCarousel" class="carousel slide">
							<!-- 轮播（Carousel）指标 -->
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="1"></li>
								<li data-target="#myCarousel" data-slide-to="2"></li>
							</ol>   
							<!-- 轮播（Carousel）项目 -->
							<div class="carousel-inner">
								<div class="item active">
									<a href="${root}/member/goXiangqing?id=4"><img src="${resources}/img/main/lunbo1.jpg" alt="First slide"></a>
								</div>
								<div class="item">
									<a href="${root}/member/goXiangqing?id=4"><img src="${resources}/img/main/lunbo1.jpg" alt="First slide"></a>
								</div>
								<div class="item">
									<a href="${root}/member/goXiangqing?id=4"><img src="${resources}/img/main/lunbo1.jpg" alt="First slide"></a>
								</div>
							</div>
							<!-- 轮播（Carousel）导航 -->
							<a class="carousel-control left" href="#myCarousel" 
							   data-slide="prev">&lsaquo;</a>
							<a class="carousel-control right" href="#myCarousel" 
							   data-slide="next">&rsaquo;</a>
						</div> 
					</div>
					<div id="leftpic">
						<a href="${root}/member/search?fullName="><img src="${resources}/img/main/zuoce.png"/></a>
					</div>
					<div id="rightpic">
						<a href="${root}/member/search?fullName="><img src="${resources}/img/main/zuoce.png"/></a>
					</div>
				</div>
				
				<!--
	            	作者：offline
	            	时间：2017-11-23
	            	描述：  右侧公告栏
	            -->
				<div id="user-info">
					<div id="user">
						<br />
							Hi,欢迎<span id=""></span>来到EGO商城<br /><br />
							<a href="${root}/member/outlogin"><button class="but2">切换账号</button><br /></a>
							<a href="${root}/member/outlogin"><button class="but2">退出登录</button><br /></a>
					</div>
					
					
					<div class="gongGao">
						<div class="gongGao1">
							公告
						</div>
						<div class="gongGao2">
							促销
						</div>
						<div class="gongGao3"  id="gongGao3-1">
							<ul>
								<li><a href="#">双十二大减价</a></li>
								<li><a href="">甩卖</a></li>
								<li><a href="">啦啦啦啦</a></li>
								<li><a href="">啦啦啦</a></li>
	
							</ul>
						</div>
						<div class="gongGao3"  id="gongGao3-2">
							<ul>
								<li><a href="#">这是公告</a></li>
								<li><a href="#">这是公告</a></li>
								<li><a href="#">这是公告</a></li>
							</ul>
						</div>
					</div>
						<div id="user-game">
							<div id="clock">
							    <p class="date">{{ date }}</p>
							    <p class="time">{{ time }}</p>
							    <p class="text">数字时钟</p>
							</div>
							
						</div> 
					
			</div>
			
			<!--
	        	作者：offline
	        	时间：2017-11-23
	        	描述：秒杀div
	        -->
				<div id="miaosha" name="miao">
					<div id="miaosha-title">
						<img src="${resources}/img/main/miaosha.jpg"/>
						<span id="miaosha-title-1">
							EGO秒杀
						</span>
						<span id="miaosha-title-2">
							<div id="timer"></div>
						</span>
					</div>
				
				
					<div id="miaosha-body">
						<c:forEach items="${listgoods1}" var="l">
						<a href="${root}/member/goXiangqing?id=${l._id}">
							<div class="miaosha-body-goods" id="goods1">
								<div class="miaosha-tuandwenzi">
									<div class="miaosha-tu">
										<img src="${root}/resources/upload/${l.coverimg}"/>
									</div>
									<div class="miaosha-wenzi">
										<span style="color:black">
											${l.fullname}
										</span>
									</div>
								</div>
								<div class="miaosha-price">
									<span id="miaosha-price-1">
										 ${l.sale}
									</span>
									<span>&nbsp;&nbsp;</span>
									<span id="miaosha-price-2">
										<%-- <%=goodslist4.get(i).getGoodPrice()%> --%>
									</span>
								</div>
							</div>
						</a>
					</c:forEach>						
						
					</div>
				</div>
				
				
				<!--
                	作者：offline
                	时间：2017-11-23
                	描述：商品类型显示
                -->
				<div class="goodtype" id="goodtype1" name="service">
					<div class="goodtype-title">
						<img src="${resources}/img/main/niaoqianJuanJIao.png"/>
						<span class="goodtype-title-left">
							家用电器
						</span>
						<span class="goodtype-title-right">
							<a href="${root}/member/search?fullName=家用电器"  style="color:white">前往血拼</a>
						</span>
					</div>
					<div class="goodtype-body">
						<c:forEach items="${listgoods2}" var="l">
							
						 		 <a href="${root}/member/goXiangqing?id=${l._id}"  style="color:black"> 
								<div class="goodtype-goods">
									<div class="goodtype-goods-p">
										<p>${l.fullname}</p>
									</div>
									<div class="goodtype-goods-img">
										<img src="${root}/resources/upload/${l.coverimg}"/>
									</div>	
								</div>
								</a>	
						</c:forEach>
					</div>
				</div>
				
				
				
				<div class="goodtype" id="goodtype2">
					<div class="goodtype-title">
						<img src="${resources}/img/main/niaoqianJuanJIao-2.png"/>
						<span class="goodtype-title-left">
							高科技电子
						</span>
						<span class="goodtype-title-right">
							<a href="${root}/member/search?fullName=高科技电子"  style="color:white">前往血拼</a>
						</span>
					</div>
					<div class="goodtype-body">
						<c:forEach items="${listgoods3}" var="l">
							<a href="${root}/member/goXiangqing?id=${l._id}"  style="color:black"> 
								<div class="goodtype-goods">
									<div class="goodtype-goods-p">
										<p>${l.fullname}</p>
									</div>
									<div class="goodtype-goods-img">
										<img src="${root}/resources/upload/${l.coverimg}"/>
									</div>	
								</div>
								</a>	
						</c:forEach>
					</div>
				</div>
				
				
				
				<div class="goodtype" id="goodtype3">
					<div class="goodtype-title">
						<img src="${resources}/img/main/niaoqianJuanJIao-3.png"/>
						<span class="goodtype-title-left">
							服装
						</span>
						<span class="goodtype-title-right">
							<a href="${root}/member/search?fullName=服装"  style="color:white">前往血拼</a>
						</span>
					</div>
					
					 <div class="goodtype-body">
					 	<c:forEach items="${listgoods4}" var="l">
								<a href="${root}/member/goXiangqing?id=${l._id}"  style="color:black"> 
								<div class="goodtype-goods">
									<div class="goodtype-goods-p">
										<p>${l.fullname}</p>
									</div>
									<div class="goodtype-goods-img">
										<img src="${root}/resources/upload/${l.coverimg}"/>
									</div>	
								</div>
								</a>	
						</c:forEach>
					</div> 
					
					
					
				
				</div>
				
		</div>

		<!--
        	作者：offline
        	时间：2017-11-22
        	描述：  右侧购物篮
        -->
		<div class="J-global-toolbar">
			<div class="toolbar-wrap J-wrap">
				<div class="toolbar">
					<div class="toolbar-panels J-panel">
						<div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">
							<h3 class="tbar-panel-header J-panel-header">
								<a href="" class="title"><i></i><em class="title">购物车</em></a>
								<span class="close-panel J-close"></span>
							</h3>
							<div class="tbar-panel-main">
								<div class="tbar-panel-content J-panel-content">
									<div id="J-cart-tips" class="tbar-tipbox hide">
										<div class="tip-inner">
											<span class="tip-text">还没有登录，登录后商品将被保存</span>
											<a href="#none" class="tip-btn J-login">登录</a>
										</div>
									</div>
									<div id="J-cart-render">
										<div class="tbar-cart-list">
			
			
				<c:forEach items="${storeCartList}"   var ="storeCart">
				<c:forEach  items="${storeCart.cartWihtGoodsList}" var="cartWihtGoods">
				<div class="tbar-cart-item" >
												<div class="jtc-item-promo">
													<em class="promo-tag promo-mz">名称<i class="arrow"></i></em>
													<div class="promo-text">${cartWihtGoods.cart.goodname}</div>
												</div>
												<div class="jtc-item-goods">
													<span class="p-img"><a href="${root }/member/goXiangqing?id=${cartWihtGoods.goods._id}" target="_blank"><img src="${resources}upload/${cartWihtGoods.cart.coverimg }" height="50" width="50" /></a></span>
													<div class="p-name">
														<a href="${root }/member/goXiangqing?id=${cartWihtGoods.goods._id}">${cartWihtGoods.goods.fullname}</a>
													</div>
													<div class="p-price"><strong>¥${cartWihtGoods.cart.price }</strong>×${cartWihtGoods.cart.num } </div>
												</div>
										    </div>	
				
				</c:forEach>
											
				</c:forEach>
										
				
									
									
									
									
									
									
										    
										    
															    
										
											
											
										</div>
									</div>
								</div>
							</div>
							<div class="tbar-panel-footer J-panel-footer">
								<div class="tbar-checkout">

									<a class="jtc-btn J-btn" href="${root}/user/cart/gomyCart" target="_blank">去购物车结算</a>
								</div>
							</div>
						</div>
		
						<dtGoodId()"visibility: hidden;" data-name="follow" class="J-content toolbar-panel tbar-panel-follow">
							<h3 class="tbar-panel-header J-panel-header">
								<a href="#" target="_blank" class="title"> <i></i> <em class="title">我的关注</em> </a>
								<span class="close-panel J-close"></span>
							</h3>
							<div class="tbar-panel-main">
								<div class="tbar-panel-content J-panel-content">
									<div class="tbar-tipbox2">
										<div class="tip-inner"> <i class="i-loading"></i> </div>
									</div>
								</div>
							</div>
							<div class="tbar-panel-footer J-panel-footer"></div>
						</div>
						
						<div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-history toolbar-animate-in">
							<h3 class="tbar-panel-header J-panel-header">
								<a href="#" target="_blank" class="title"> <i></i> <em class="title">我的足迹</em> </a>
								<span class="close-panel J-close"></span>
							</h3>
							<div class="tbar-panel-main">
								<div class="tbar-panel-content J-panel-content">
									<div class="jt-history-wrap">
										<ul>
											<li class="jth-item">
												<a href="#" class="img-wrap"> <img src="http://img10.360buyimg.com/n0/s100x100_g9/M03/0C/18/rBEHalCKCk8IAAAAAAD5nbR5xOAAACfgQENi_wAAPm1269.jpg" height="100" width="100" /> </a>
												<a class="add-cart-button" href="#" target="_blank">加入购物车</a>
												<a href="#" target="_blank" class="price">￥498.00</a>
											</li>
											<li class="jth-item">
												<a href="#" class="img-wrap"> <img src="http://img10.360buyimg.com/n0/s100x100_g9/M03/0C/18/rBEHalCKCk8IAAAAAAD5nbR5xOAAACfgQENi_wAAPm1269.jpg" height="100" width="100" /></a>
												<a class="add-cart-button" href="#" target="_blank">加入购物车</a>
												<a href="#" target="_blank" class="price">￥498.00</a>
											</li>
										</ul>
										<a href="#" class="history-bottom-more" target="_blank">查看更多足迹商品 &gt;&gt;</a>
									</div>
								</div>
							</div>
							<div class="tbar-panel-footer J-panel-footer"></div>
						</div>
					</div>
					
					<div class="toolbar-header"></div>
					
					<div class="toolbar-tabs J-tab">
						<div class="toolbar-tab tbar-tab-cart">
							<i class="tab-ico" style="">购</i>
							<em class="tab-text ">我的购物车</em>
							<span class="tab-sub J-count ">${g.cartNum}</span>
						</div>
						<!-- <div class=" toolbar-tab tbar-tab-follow">
							<i class="tab-ico"></i>
							<em class="tab-text">我的关注</em>
							<span class="tab-sub J-count hide">0</span> 
						</div>
						<div class=" toolbar-tab tbar-tab-history ">
							<i class="tab-ico"></i>
							<em class="tab-text">我的足迹</em>
							<span class="tab-sub J-count hide">0</span>
						</div>  -->
					</div>
					
					<div class="toolbar-footer">
						<div class="toolbar-tab tbar-tab-top"> <a href="#"> <i class="tab-ico  ">顶</i> <em class="footer-tab-text">顶部</em> </a> </div>
						<div class=" toolbar-tab tbar-tab-feedback"> <a href="#" target="_blank"><i class="tab-ico">反</i> <em class="footer-tab-text ">反馈</em> </a> </div>
					</div>
					
					<div class="toolbar-mini"></div>
					
				</div>
				
				<div id="J-toolbar-load-hook"></div>
				
			</div>
		</div>
		
		
		<!--
            	作者：offline
            	时间：2017-11-22
            	描述：楼层显示
            	
            -->
          <!-- <div id="louceng">
            	
	           
				<div id="box">
					<ul>
						<li>
							<a class="num" href="#miao" style="display:none">1F</a>
							<a class="word" href="#miao"  style="display:block">秒杀</a>
						</li>

						<li class="last">
							<a class="num" href="#service">2F</a>
							<a class="word" href="#service">服务</a>
						</li>
					</ul>
				</div>
			</div> -->
		
		
		
		<div class="xuanfuSearch">
			<div id="xuanfu-all">
				<div id="xuanfu-all-img">
					<img src=""/>
				</div>
				<form action="${root}/member/search" method="post">
				<div id="search-form-1">   <!-- class="glyphicon glyphicon-search" -->
					<input  name="fullName"  placeholder="请输入sousuo内容" /><button type="submit" class="btn btn-default btn-sm" style="color: white; background-color: red; font-size: 22px;"><span >搜索</span></button>
				</div>
				</form>
			</div>
		</div>
		
		
		<div class="foot">
			<p>
				<a href="#">关于我们</a>
				<a href="#">联系客服</a>
				<a href="#">合作招商</a>
				<a href="#">商家帮助</a>
				<a href="#">营销中心</a>
				<a href="#">友情链接</a>
				<a href="#">销售联盟</a>
				<a href="#">风险监测</a>
				<a href="#">隐私政策</a>
				<a href="#">公益活动</a>
			</p>
			<p>
				<a href="#">经公网安备&nbsp;1100000002000000088号</a>
				<span>京ICP证0700369</span>
				<a href="#">互联网信息服务资格证编号【2014】2014-008</a>
				<span>新出发京零 字第大120007号</span>
			</p>
			<p>
				<span>互联网出版许可证编号新出网证(京)字150号</span>
				<a href="#">出版物经营许可证</a>
				<a href="#">网络文化经营许可证京网文[2014]2148-348号</a>
				<span>违法和不良信息举报电话：4006561155</span>
			</p>
			<p>   
				<span>Copyright &copy; 2004 - 2017 EGO版权所有&nbsp;|&nbsp;消费者维权热线：4006067733</span>
				<a href="#">经营执照</a>
			</p>
		</div>
		
	</div>
		
	
	</body>
	

	<script type="text/javascript" src ='${resources}/js/nav.js'></script>
			<!--
            	作者：offline
            	时间：2017-11-23
            	描述：富强", "民主", "文明", "和谐", "自由  点击特效
            -->
			<script type="text/javascript"> 
				/* 鼠标特效 */ 
				var a_idx = 0; 
				jQuery(document).ready(function($) { 
				    $("body").click(function(e) { 
				        var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正" ,"法治", "爱国", "敬业", "诚信", "友爱"); 
				        var $i = $("<span />").text(a[a_idx]); 
				        a_idx = (a_idx + 1) % a.length; 
				        var x = e.pageX, 
				        y = e.pageY; 
		                $i.css({ 
				            "z-index": 999999999999999999999999999999999999999999999999999999999999999999999, 
				            "top": y - 20, 
				            "left": x, 
				            "position": "absolute", 
				            "font-weight": "bold", 
				            "color": "#ff6651" 
				        }); 
				        $("body").append($i); 
				        $i.animate({ 
				            "top": y - 180, 
				            "opacity": 0 
				        }, 
				        1500, 
				        function() { 
				            $i.remove(); 
				        }); 
				    }); 
				}); 
		</script>

	<!--
    	作者：offline
    	时间：2017-11-23
    	描述：左侧商品导航listGoodsscript
    -->
	 <script>
		$(function(){
		    var $tabItem = $('#things .tab-item');//获得导航条部分
		    var $fmaint = $('#things .fmaint');//获得内容部分
		    var $at = $('#variety a');
		    var $spant = $('#variety span');
		    var $dropdown = $('.dropdown');
		    $tabItem.each(function (index) {//用each方法给每个导航条添加鼠标移入移除事件
		        $(this).mouseover(function () {
		            $dropdown.removeClass('hiden');
		            $(this).addClass('showLi');
		            $fmaint.eq(index).removeClass('hiden').siblings().addClass('hiden');
		            $at.eq(index).addClass('showA');
		            $spant.eq(index).addClass('showSpan');
		        }).mouseout(function () {
		            $dropdown.addClass('hiden');
		            $(this).removeClass('showLi');
		            $fmaint.eq(index).addClass('hiden');
		            $at.eq(index).removeClass('showA');
		            $spant.eq(index).removeClass('showSpan');
		        });
		    });
		    $fmaint.each(function (index) {//用each方法给每个内容添加鼠标移入移除事件
		        $(this).mouseover(function () {
		            $dropdown.removeClass('hiden');
		            $tabItem.eq(index).addClass('showLi');
		            $fmaint.eq(index).removeClass('hiden').siblings().addClass('hiden');
		            $at.eq(index).addClass('showA');
		            $spant.eq(index).addClass('showSpan');
		        }).mouseout(function () {
		            $dropdown.addClass('hiden');
		            $at.eq(index).removeClass('showA');
		            $spant.eq(index).removeClass('showSpan');
		            $tabItem.eq(index).removeClass('showLi');
		            $fmaint.eq(index).addClass('hiden');
		        });
		    });
		})
				
	</script> 
	
	<!--
    	作者：offline
    	时间：2017-11-23
    	描述：顶部悬浮搜索栏
    -->
		<script>
		$(function(){
			// @ 给窗口加滚动条事件
			$(window).scroll(function(){
				// 获得窗口滚动上去的距离
				var ling1 = $(document).scrollTop();
				// 在标题栏显示滚动的距离
				// 如果滚动距离大于1534的时候让滚动框出来
				if(ling1>200){
					$('.xuanfuSearch').show();
				}
				if(ling1<200){
					$('.xuanfuSearch').hide();
				}
				
			})
	
		})
	
		</script>
	
	
	
	<!--
    	作者：offline
    	时间：2017-11-23
    	描述：楼层滚动script
    -->
	<script>
		$(function(){
			// @ 给窗口加滚动条事件
			$(window).scroll(function(){
				// 获得窗口滚动上去的距离
				var ling = $(document).scrollTop();
				// 在标题栏显示滚动的距离
				//document.title = ling;
				// 如果滚动距离大于1534的时候让滚动框出来
				if(ling>000){
					$('#box').show();
				}
				if(00<ling && ling<400){
					// 让第一层的数字隐藏，文字显示，让其他兄弟元素的li数字显示，文字隐藏
					$('#box ul li').eq(0).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(0).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<700){
					$('#box ul li').eq(1).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(1).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<800){
					$('#box ul li').eq(2).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(2).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<900){
					$('#box ul li').eq(3).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(3).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<1000){
					$('#box ul li').eq(4).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(4).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<5460){
					$('#box ul li').eq(5).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(5).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<6035){
					$('#box ul li').eq(6).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(6).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<6645){
					$('#box ul li').eq(7).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(7).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<7360){
					$('#box ul li').eq(8).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(8).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<7905){
					$('#box ul li').eq(9).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(9).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}else if(ling<8790){
					$('#box ul li').eq(10).find('.num').hide().siblings('.word').css('display','block');
					$('#box ul li').eq(10).siblings('li').find('.num').css('display','block').siblings('.word').hide();
				}
				if(ling>8800 || ling<100){
					// $('#box').css('display','none');  // @ 这一句和下一句效果一样。
					$('#box').hide();
				}
				
			})
	
		})
	
			$(function(){
				$('#box ul li').hover(function(){
					$(this).find('.num').hide().siblings('.word').css({'display':'block','background':'#CB1C39','color':'white'});
				},function(){
				
					$(this).find('.num').css({'display':'block','background':'white','color':'#666'}).siblings('.word').hide().css({'display':'none','background':'','color':''});
				})
			})
		</script>
		
		
        
        <!--
        	作者：offline
        	时间：2017-11-23
        	描述：公告的点击事件
        -->
        <script>
        	$(document).ready(function(){
				 	 	 
				 //title 悬浮效果
				 $("#myUniverse-li").mouseenter(function(){
				 	 $("#myUniverse").css("display","inline");
				 })
				 $("#myUniverse").mouseleave(function(){
				 	 $("#myUniverse").css("display","none");
				 })
				 
				 $(".gongGao1").click(function(){
				 	$(".gongGao3").css("display","none");
				 	$("#gongGao3-2").css("display","inline");
				 	$(".gongGao1").css("border","1px solid red");
				 	$(".gongGao2").css("border","1px solid white");
				 })
				 $(".gongGao2").click(function(){
				 	$(".gongGao3").css("display","none");
				 	$("#gongGao3-1").css("display","inline");
				 	$(".gongGao2").css("border","1px solid red");
				 	$(".gongGao1").css("border","1px solid white");
				 }) 
			 })

    	
        </script>
        
        
        <script language="javascript" type="text/javascript"> 
				function leftTimer(year,month,day,hour,minute,second){ 
				 var leftTime = (new Date(year,month-1,day,hour,minute,second)) - (new Date()); //计算剩余的毫秒数 
				 var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
				 var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
				 var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
				 var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数 
				 days = checkTime(days); 
				 hours = checkTime(hours); 
				 minutes = checkTime(minutes); 
				 seconds = checkTime(seconds); 
				 setInterval("leftTimer(2018,11,30,00,00,00)",1000); 
				 document.getElementById("timer").innerHTML = days+"天" + hours+"小时" + minutes+"分"+seconds+"秒"; 
				} 
				function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
				 if(i<10) 
				 { 
				  i = "0" + i; 
				 } 
				 return i; 
				} 
				 
		</script> 
		<!--
        	作者：offline
        	时间：2017-11-23
        	描述：shizhong
        -->
		
		<script type="text/javascript" src="${resources}/js/vue.min.js"></script>
		<script>
			var clock = new Vue({
			    el: "#clock",
			    data: {
			        time: '',
			        date: ''
			    }
			});
			
			var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
			var timerID = setInterval(updateTime, 1000);
			updateTime();
			function updateTime() {
			    var cd = new Date();
			    clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) ;//+ ':' + zeroPadding(cd.getSeconds(), 2)
			    clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
			};
			
			function zeroPadding(num, digit) {
			    var zero = '';
			    for(var i = 0; i < digit; i++) {
			        zero += '0';
			    }
			    return (zero + num).slice(-digit);
			}
		</script>		
</html>
