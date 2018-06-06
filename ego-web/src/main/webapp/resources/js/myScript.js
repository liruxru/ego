$(function(){
	
	var gs = $("#box .posi");
	var gsBig = $("#box .bigImage");
	var gsMove = $("#box .move");
	
	
	gs.hover(function(e){
		gsMove.css("display","block");
		gsBig.css("display","block");
		gsBig.css("z-index",9999);
		
		
	},function(e){
		gsMove.css("display","none");
		gsBig.css("display","none");
		
		
	});
	
	gs.mousemove(function(e){
		
		var cL = e.clientX;
		var cT = e.clientY;
		var gL = $(this).offset().left;
		var gT = $(this).offset().top;
		
		var mW = gsMove.width();
		var mH = gsMove.height();
		
		var left = cL - gL - mW / 2;
		var top = cT - gT - mH / 2;
		
		if (left < 0) {
			left = 0;
		}else if (left > gs.width() - mW) {
			left = gs.width() - mW;
		}
		
		if (top < 0) {
			top = 0;
		}else if (top > gs.height() - mH) {
			top = gs.height() - mH;
		}
		
		gsMove.css({"left":left,"top":top});
		
		
		var L_b = left / (gs.width() - mW);
		var T_b = top / (gs.height() - mH);
		
		var gsBig_L = L_b * (gsBig.find("img").width() - gsBig.width());
		var gsBig_T = T_b * (gsBig.find("img").height() - gsBig.height());
		gsBig.find("img").css({"margin-left":-gsBig_L,"margin-top":-gsBig_T});
	});	
});













