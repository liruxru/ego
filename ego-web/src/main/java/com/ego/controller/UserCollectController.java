package com.ego.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.document.CollectGoods;
import com.ego.document.Description;
import com.ego.document.Goods;
import com.ego.document.LiuLan;
import com.ego.document.PingJia;
import com.ego.document.po.GoodCollectWithGoods;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.mapper.po.User;
import com.ego.mapper.po.relationPo.PingHuiWithRole;
import com.ego.mapper.po.relationPo.PingJiaWithPingHui;
import com.ego.service.CollectGoodsService;
import com.ego.service.LiuLanService;
import com.ego.service.PingJiaService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchLiulan;

@Controller
@RequestMapping("/user")
public class UserCollectController {
	@Resource(name="collectGoodsService")
	private CollectGoodsService  collectGoodsService;
	@RequestMapping("/gomyCollect")
	public String gomyCart(Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		PageBean<Goods> page= collectGoodsService.selectGoodsFromCollectByUserId(user.getId(), 1, 16);
		List<Goods> goodsList = page.getBeanList();
		model.addAttribute("goodsList", goodsList);
		return "user/collect/myCollect";
	}
	
	
	
	@RequestMapping("/collect/deleteFromCollect")
	@ResponseBody
	public ReturnMessage<CollectGoods> deleteFromCollect(HttpSession session,String goodsId) {
		User user=(User) session.getAttribute("user");
		ReturnMessage<CollectGoods> returnMessage = collectGoodsService.deleteGoodsFromCollect(user.getId(), goodsId);
		return returnMessage;
	}
	@RequestMapping("/collect/addToCollect")
	@ResponseBody
	public ReturnMessage<CollectGoods> addToCollect(HttpSession session,String goodsId) {
		
		User user=(User) session.getAttribute("user");
		ReturnMessage<CollectGoods> returnMessage = collectGoodsService.addGoodsToCollect(user.getId(), goodsId);
		return returnMessage;
	}
	@RequestMapping("/collect/selectGoodsCollect")
	public String selectGoodsCollect(Model model,HttpSession session,String goodsId) {
		 PageBean<GoodCollectWithGoods> page = collectGoodsService.selectGoodsCollect(1, 16);
		 List<GoodCollectWithGoods> goodCollectWithGoodsList = page.getBeanList();
		 model.addAttribute("goodCollectWithGoodsList", goodCollectWithGoodsList);
		return "user/goods/goodsOrderByCollect";
	}
	//订单评价
	@Resource(name="pingJiaService")
	private PingJiaService pingJiaService;
	@RequestMapping("/pingJia/addPingJia")
	@ResponseBody
	public ReturnMessage<PingJia> addPingJia(Model model,HttpSession session,Integer orderItemId,String description ) {
		User user=(User) session.getAttribute("user");
		description=description.replaceAll("\"", "\'");
		System.out.println(description);
		ReturnMessage<PingJia> returnMessage = pingJiaService.addPingJia(user.getId(), orderItemId, description);
		return returnMessage;
	}
	//查看商品评价
	@RequestMapping("pingJia/selectPingjiaByGoodsId")
	public String selectPingjiaByGoodsId(Model model,String goodsId) {
		PageBean<PingJiaWithDescription> page = pingJiaService.selectPingjiaByGoodsId(goodsId, 1, 16);
		List<PingJiaWithDescription> pingJiaWithDescriptionList = page.getBeanList();
		model.addAttribute("pingJiaWithDescriptionList", pingJiaWithDescriptionList);
		return "";
	}
	//
	@RequestMapping("pingJia/selectPingJiaByOrderItem")
	@ResponseBody
	public PingJiaWithPingHui selectPingJiaByOrderItem(Integer orderItemId) {
		PingJiaWithDescription pingJiaWithDescription = pingJiaService.selectPingJiaByOrderItem(orderItemId);
		if(pingJiaWithDescription==null) {
			return null;
		}
		LinkedHashMap<Description, Description> pin_hui_Entiy = pingJiaWithDescription.getPin_hui_Entiy();
		Set<Entry<Description, Description>> entrySet = pin_hui_Entiy.entrySet();
		PingJiaWithPingHui pingJiaWithPingHui=new PingJiaWithPingHui();
		pingJiaWithPingHui.setPingJiaId(pingJiaWithDescription.get_id());
		List<PingHuiWithRole> pingHuiWithRoleList=new ArrayList<PingHuiWithRole>();
		for (Entry<Description, Description> entry : entrySet) {
			Description key = entry.getKey();
			if(key!=null) {
				pingHuiWithRoleList.add(new PingHuiWithRole("user",key));
			}
			Description value = entry.getValue();
			if(value!=null) {
				pingHuiWithRoleList.add(new PingHuiWithRole("admin",value));
			}
		}
		pingJiaWithPingHui.setPingHuiWithRoleList(pingHuiWithRoleList);
		return pingJiaWithPingHui;
		
	}
	
	//追评
	@RequestMapping("pingJia/addZhuiPing")
	@ResponseBody
	public ReturnMessage<PingJia> addZhuiPing(String pingJiaId,String description) {
		ReturnMessage<PingJia> returnMessage = pingJiaService.addZhuiPing(pingJiaId, description);
		return returnMessage;
	}
	//足迹
	@Resource(name="liuLanService")
	private LiuLanService liuLanService;
	@RequestMapping("/liuLan/deleteLiuLan")
	@ResponseBody
	public ReturnMessage<LiuLan> deleteLiuLanByUserId(HttpSession session,Goods goods) {
		User user=(User) session.getAttribute("user");
		Integer id = user.getId();
		ReturnMessage<LiuLan> returnMessage = liuLanService.deleteLiuLanByUserId(id.toString(), goods);
		return returnMessage;
	}
	@RequestMapping("/liuLan/findLiuLan")
	public String selectLiuLanByUserId(HttpSession session,Goods goods,Model model) {
		User user=(User) session.getAttribute("user");
		Integer id = user.getId();
		SearchLiulan searchLiulan=new SearchLiulan(null, user.getId().toString(), 16, 1);
		PageBean<LiuLan> page = liuLanService.selectLiuLanByUserId(searchLiulan);
		List<LiuLan> liuLanList = page.getBeanList();
		List<Goods> goodsList=null;
		if(liuLanList!=null) {
			for (LiuLan liuLan : liuLanList) {
				goodsList=liuLan.getGoodList();
			}
		}
		
		model.addAttribute("goodsList", goodsList);
		return "user/liuLan/liuLan";
	}
	
}
