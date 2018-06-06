package com.ego.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.document.Goods;
import com.ego.document.PingJia;
import com.ego.document.po.GoodCollectWithGoods;
import com.ego.service.AdminMessageService;
import com.ego.service.CollectGoodsService;
import com.ego.service.GoodsService;
import com.ego.service.PingJiaService;
import com.ego.utils.PageBean;
import com.ego.utils.SearchGoods;

@Controller("homeController")
@RequestMapping("/admin/home")
public class HomeController {
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	@Resource(name="adminMessageService")
	private AdminMessageService adminMessageService;
	
	@RequestMapping("/list")
	public String listMessage(Model model) {
		SearchGoods searchGoods = new SearchGoods(null, null, null, 50, null, null, null, null, null, 9, 1, null, null, 3);
		PageBean<Goods> page = this.goodsService.warringGoods(searchGoods);
		model.addAttribute("goodsList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/home/list";
		
	}
	
	
	

}
