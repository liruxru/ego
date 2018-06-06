<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title></title>
 <link rel="stylesheet" href="${css}/cart.css" />
<script type="text/javascript" src="${js}/jquery.min.js"></script>
<script>
		//编写jQuery脚本
		$(function(){	
		//搜索跳转
		$("#searchBtn").click(function(){
				var searchValue=$("#searchValue").val();
			url="${root}/account/listGoodSrevlet?souSuo="+searchValue;
			location=url;
		})
		function change(){
			var len1=$(".check").length;
			var len2=$(".check:checked").length;
			var selNum=$(":checkbox[name='cartId']:checked").length;
			//当被选择的数量小于可选择的数量，全选按钮不被勾选
			if(len1!=len2){$("#chkAll,.eGoCheckbox").prop("checked",false);}
			if(len1==len2){$("#chkAll,.eGoCheckbox").prop("checked",true);}
		}	
		//结算按钮单击事件
		$(".submit_Btn").click(function(){
				var len2=$(".check:checked").length;
				if(len2==0)return;
				//提交表单		
				creatOrderForm.submit();
			})
			//批量删除购物车条目	
			$(".remove-batch").click(function(){
				var len2=$(".check:checked").length;
				if(len2==0)return;
				if(!confirm("确定删除购物车条目吗？"))return;
				var cartIdsObj=$(":checkbox[name='cartId']:checked");
				var cartIds=new Array();
				for(var i=0;i<cartIdsObj.length;i++){
					cartIds[i]=cartIdsObj.eq(i).val()
				}
				var url="${root}/user/cart/batchDeleteCart";
				$.post(url,{"cartIds":cartIds.toString()},
				function(res){
					if(res.resultCode==0){
						//删除成功
						for(var i=0;i<cartIds.length;i++){
							$("#"+cartIds[i]).remove();
					    }
					    //计算小计在计算总价
						subTotal();
						countToal();
					}else{
						alert("删除失败");
						return;
					}
				},'json')
			})
			//小计和总计的函数
			function subTotal(){
				var subObjArr=$(".td-sum span");
				for (var i=0;i<subObjArr.length;i++) {
					//在js中遍历元素使用eq
					var obj=subObjArr.eq(i);
					 var godPrice=obj.parent().prev().prev().children().eq(0).text()*1;
					var goodNum=obj.parent().parent().children().eq(4).children().eq(1).children().eq(0).val()*1;
					var subTotal=godPrice*goodNum
					obj.text(subTotal.toFixed(2));
				}
			}
			//计算被选中商品的总价
			function countToal(){
				var subTotalObj=$(".subTotal");
				var sumTotal=0;
				var goodNum=0;
				for (var i=0;i<subTotalObj.length;i++) {
					if(subTotalObj.eq(i).parent().parent().children().eq(0).children().eq(0).prop("checked")){
						var subTotal=subTotalObj.eq(i).text()*1;
						sumTotal+=subTotal;
						goodNum+=(subTotalObj.eq(i).parent().prev().children().eq(1).children().eq(0).val()*1);
					}
				}
				$("#sumPrice").text(sumTotal.toFixed(2));
				$("#numTotal").text(goodNum);
				$("#cartNumber").text(goodNum);
				
			}
			//计算小计在计算总价
			subTotal();
			countToal();
			//移除购物车
			$(".deleteCart").click(function(){
				if(!confirm("确定删除吗"))return;
				var cartId=$(this).parent().parent().parent().parent().attr("id");
				var url="${root }/user/cart/deleteById";
				//Ajax异步刷新删除 url data function 'json'
				$.post(url,{"cartId":cartId},
				function(res){
					if(res.resultCode==0){
						//删除成功，移除节点
						$("#"+cartId).remove();
						//计算小计在计算总价
						subTotal();
						countToal();
					}else{
					alert("删除失败");
					return;
					}
				},'json');
			})
			//修改购物车数量
			$(".addNum,.subNum").click(function(){
				var url="${root}/user/cart/updateCartNum";
				var cartId=$(this).parent().parent().parent().parent().attr("id");
				var tag=$(this).attr("class");//add sub Num
				var cartNumObj=$(this).parent().parent().find("input");
				var cartNum=cartNumObj.val()*1;
				if(tag=="subNum"){
					if(cartNum==1){
						if(!confirm("确定删除这个购物车条目吗？"))return; 
					}
				}
				//如果选择是，执行异步添加减少删除
				$.post(url,{"tag":tag,"cartId":cartId},
					function(res){
						if(res.resultCode==0){
							//更新成功，修改节点				
							if (tag == "subNum") {
								if (cartNum == 1) {
									//删除成功，移除节点
									$("#"+cartId).remove();
								}else{
									cartNumObj.val(cartNum-1);
								}
							}else{
								//操作符是加号，执行数量+1
								cartNumObj.val(cartNum+1);
							}
							//计算小计在计算总价
							subTotal();
							countToal();
						}
			}, 'json')
		})
		//单击全选事件		
		$("#chkAll,.eGoCheckbox").click(function() {
			//单击全选，所有店铺实现全选与取消
			$(".chkAll").prop("checked", $(this).prop("checked"));
			//改变店铺商品的复选框属性
			$(".check").prop("checked", $(this).prop("checked"));
			//固定条全选
			$(".eGoCheckbox,#chkAll").prop("checked", $(this).prop("checked"));
			//计算小计在计算总价
			subTotal();
			countToal();
		});
		//单个商品的单击事件
		$(".check").click(
				function() {
					change();
					var len3 = $(this).parent().parent().parent()
							.find(".check").length;
					var len4 = $(this).parent().parent().parent().find(
							".check:checked").length;
					if (len3 > len4) {
						$(this).parent().parent().parent().prev().find(
								".chkAll").prop("checked", false);
					}
					if (len3 == len4) {
						$(this).parent().parent().parent().prev().find(
								".chkAll").prop("checked", true);
					}
					//计算小计在计算总价
					subTotal();
					countToal();
				});
	})
</script>
</head>
<body>
	<div id="header">
		<div id="word">
			<ul class="fl">
				<div id="homeImg"></div>
				<li id="ttbar-home"><a href="${root }" target="_blank">我的首页</a></li>
				<li class="dorpdown" id="ttbar-mycity"></li>
			</ul>
			<ul class="fr">
				<li class="fore1" id="ttbar-login"><a
					href="javascript:login();" class="link-login">欢迎:${user.username }</a>&nbsp;&nbsp;
					<a href="" class="link-regist style-red"></a>
				</li>
				<li class="spacer"></li>
				<li class="fore2">
					<div class="dt">
						<a target="_blank" href="${root }/account/listOrder">我的订单</a>
					</div>
				</li>
				<li class="spacer"></li>
				<li class="fore3 dorpdown" id="ttbar-myjd">
					<div class="dt cw-icon">
						<a target="_blank" href="${root }/">我的EGO</a><i
							class="iconfont">&#xe605;</i>
					</div>
					<div class="dd dorpdown-layer"></div>
				</li>
				<li class="spacer"></li>
			</ul>
			<span class="clr"></span>
		</div>
	</div>
	<div id="logoAndsearch">
		<div id="logo"></div>
<img alt="" style="width: 100px;height: 97px;" src="${resources}/img/logo.png">
		<div id="search">
			<div id="form">
				<input type="text" id="input" id="searchValue"> <input type="button"
					id="searchBtn" value="搜索">
			</div>
		</div>
	</div>
	 <c:if test="${empty cartWithGoodsList }">
	 <br/><br/><br/><br/>
			<div id="container"  style="font-size: 20px" >购物车还没有商品<br/>赶快去购物把<a href="${root }/">商城主页</a></div>
		
	</c:if>
	<c:if test="${ not empty cartWithGoodsList }">
	<!--        	描述：购物车主题条目       -->
	<div id="container">
		<!--标题和配送地址           -->
		<div class="cart-filter-bar">
			<div id="listCart">
				<b>全部商品</b> <span id="cartNumber"></span>
			</div>

			<div id="userAddress">
				<span>配送至:<c:if test="${ not empty userChooseAddr }">${userChooseAddr }</c:if>  <c:if test="${empty userChooseAddr }">${address }</c:if></span>
			</div>
		</div>
		<!--表头   -->
		<div id="cartTableHead">
			<div class="checkAll">
				<input id="chkAll" type="checkbox"> <span>全选</span>
			</div>
			<div class="t-goods">商品</div>
			<div class="t-props"></div>
			<div class="t-price">单价</div>
			<div class="t-quantity">数量</div>
			<div class="t-sum">小计</div>
			<div class="t-action">操作</div>
		</div>
		<!--
            	作者：offline
            	时间：2017-11-21
            	描述：这个表单用于购物车结算，给服务器发送数据，被勾选的商品
            -->
			<form action="${root }/user/order/addOrder" method="post"  name="creatOrderForm">
				<c:forEach items="${cartWithGoodsList }" var="cartWithGoods" >
				<div class="cartItem" id="${cartWithGoods.id}">
				<div class="position">
						<div class="chck">
							<input  value=${cartWithGoods.id } name="cartId" class="check" type="checkbox" />
						</div>
						<div class="td-goods">
							<div class="goodImg">
								<img style="width: 100px;height: 100px;" src="${root }/img/upload/${cartWithGoods.coverimg }">
							</div>
							<div class="goodDesc">${cartWithGoods.goodname } </div>
						</div>
						<div class="td-props">${cartWithGoods.goods.description }</div>
						<div class="td-price">
							<span class="goodPrice">${cartWithGoods.price }</span>
						</div>
						<div class="td-quantity">
							<div class="subDiv">
								<a href="javascript:void(0)" class="subNum">-</a>
							</div>
							<div class="changeNumDiv">
								<input type="text" class="changeNum" value="${cartWithGoods.num }">
							</div>
							<div class="addDiv">
								<a href="javascript:void(0)" class="addNum">+</a>
							</div>

						</div>
						<div class="td-sum">
							<span class="subTotal"></span>
						</div>
						<div class="td-action">
							<div>
								<a class="deleteCart" href="javascript:void(0)">删除</a>
							</div>

						</div>
						</div>
				</div>		
			
					</c:forEach>	
				
			
				<div id="place"></div>		

			<!--
	作者：offline
	时间：2017-11-21
	描述：结算悬浮块
-->
			<div id="cart-floatbar">
				<div class="select-all">
					<input class="eGoCheckbox" type="checkbox">全选
				</div>
				<div class="operation">
					<a href="javascript:void(0)" class="remove-batch" >删除选中的商品</a>

				</div>
				<div class="comm-right">

					<a href="#none" class="submit_Btn">去结算</a>

					<div class="price-sum">
						<div>
							总价：<span id="sumPrice"><em>¥0.00</em></span>
						</div>
					</div>
					<div class="amount-sum">
						已选择<em id="numTotal">0</em>件商品<b class="up"></b>
					</div>
					<div class="clr"></div>
				</div>
			</div>
		</form>
	</div>
	</c:if>


</body>
</html>
