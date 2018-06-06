<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> 收银台</title>
	
		<style>
			*{margin: 0px auto;padding: 0px auto;}
			.topRight{width: 100%;height: 27px;background-color: #F6F6F6;}
			.topRightx{width: 1000px;height: 100%;}
			.hotTel{float: right;}
			#g1{font-size: 13px; color:#666666 ; margin:0px 10px;	}
			#g2{font-size: 16px;color: red;}
			.hotTels{float: right;}
			#g3{font-size: 12px; color:#666666 ;  margin-left: 10px ;line-height: 25px;text-decoration: none;}
			#g3:hover{font-size: 12px; color:red ;  margin-left: 10px ;line-height: 25px; text-decoration: underline;	}
			.logoNow{width:1000px;height: 110px;}
			.logo{margin-top: 20px; float: left;}
			#syt{font-size: 25px;line-height: 100px;}
			.order{width: 1000px;height: 285px;background-color: #FBFCFF;border: 1px #E4E4E4 solid;}
			.ordert{ width: 36px; height: 36px; background-image: url(resources/img/payIcon-24.png);float: left;margin-left: 40px;margin-top: 20px;}
			.orderx{width: 860px;height: 110px;margin-top: 20px;}
			.dd{list-style: none;margin: 11px 0px;font-size: 12px; color: #666666;}
			#dd1{font-size: 22px;color:#339933;}
			#dd2{font-size: 12px;color: #666666;}
			#dd3{width: 100%;height: 20px;border-bottom: dotted #666666;}
			div ul li a{color: #FF0000;}
			ul li{list-style: none;font-size: 14px;}
			.pay{width: 1000px;height: 350px;background-color: #FBFCFF; 
			border: 1px #E4E4E4 solid;margin-top: 10px;}
			.payCon{width: 1000px;height: 240px;}
		    #pays{width: 1000px;height: 60px;}
		   .paysz{float: left;margin:18px;20px}
		    .pay1{width: 120px;height: 40px;float: left;margin:10px 18px;
		    background-image: url(${rs}/img/Payment.png);}
		    .pay2{width: 120px;height: 40px;float: left;margin:10px 18px;
		    background-image: url(${rs}/img/Payment.png); background-position: 0px -40px;}
		    .pay3{width: 120px;height: 40px;float: left;margin:10px 18px ;
		    background-image: url(${rs}/img/Payment.png);background-position: 0px -80px;}
		    .pay4{width: 120px;height: 40px;float: left;margin:5px 18px;
		    background-image: url(${rs}/img/Payment.png);background-position: 0px -110px;}
		    i{font-size:14px ;line-height: 50px;float: left;display: inline-block;color: #999;font-style: normal;}
		    .payAmount{position: absolute;}
		    #tuijian{border: 1px solid #e6393d;height: 16px;line-height: 16px;padding: 0 2px;margin: 16px 10px 0 0;font-style：normal !important; color: #e6393d}
		    #ljzf{margin-left: 70px;margin-top: 30px;}
		    .zysx{width: 1000px;height: 160px;line-height: 24px;color: #666}
		    .zysxz{margin: 20px;}
		    span{color: #cc0000;font-weight: bold;padding: 0 2px;font-size: 14px;}
		</style>
	</head>
<body>
	<div>
		<div class="topRight">
			<div class="topRightx">
				<jsp:include page="../../member/top.jsp"></jsp:include>
			</div>
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
					<li id="dd1" class="dd">订单已支付成功，请您尽耐心等待，我们将为您闪电送达！</li>
					<li id="dd2" class="dd" style="margin-bottom: 15px;">我们将精心打包您的包裹，预计在&nbsp;&nbsp;<a><span>24小时</span></a>&nbsp;&nbsp;内为您发货，请耐心等待！
					</li>
					<li class="dd"
						style="border-top: 1px dotted #ccc; padding: 8px 0 5px;">已支付金额：<a><span>￥666.00</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单编号：4646441384346
					</li>
        		    <li id="dd2" class="dd" style="margin-top: 20px;">您可以     &nbsp;&nbsp;&nbsp; <a href="#">继续购物 </a> &nbsp;&nbsp;&nbsp; <a href="#">查看我的订单</a> </li>
        		    <li id="dd2" class="dd">Ego不会以<a>订单异常、系统升级</a>等为由，通过电话、短信等方式要求您点击任何连接进行退款操作，如有提及中奖、折扣、索要验证码等问题，
        		    <a style="margin-top: 9px;display: block;"><span style="color:#666666;font-weight: normal;font-size: 12px;">请拨打客服电话：</span><400-617-9999></400-617-9999></a></li>
				</ul>
			</div>
		</div>
		<div class="zysx">
			<ul>
				<br />
				<li id="zysxz" style="color: red;">注意事项：</li>
				<br />
				<li id="zysxz">"支付成功"表明Ego确认您的订单，只有您的支付通过审核后，才代表支付正式生效；</li>

				<li id="zysxz">请您务必认真检查所有货物，如有不符，您可以拒收；</li>

				<li id="zysxz">请您认真检查外包装。如有明显损坏迹象，您可以拒收该货品，并及时通知我们。</li>
				<br />
			</ul>
		</div>
	</div>
	<div style="height: 20px;"></div>
	<img src="${rs}/img/bom.png" />
</body>
</html>
