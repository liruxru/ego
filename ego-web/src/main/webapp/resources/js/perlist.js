/**
 * 根据右侧导航变换左侧内容
 */
$(function(){
		
		    var $tabItem = $(".tab-item");//获得导航条部分
		    var $right = $(".center-right");//获得内容部分	   
		    $tabItem.each(function (index) {//用each方法给每个导航条添加点击事件
		        $(this).click(function () {
		            $(this).css("background-color","lightgoldenrodyellow").siblings().css("background-color","white");  //变换背景
		            $right.eq(index).css("display","block").siblings().css("display","none");
		        })
		    })
		    
		})