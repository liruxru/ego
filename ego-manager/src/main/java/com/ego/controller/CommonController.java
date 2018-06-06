package com.ego.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.document.po.GoodCollectWithGoods;
import com.ego.mapper.po.relationPo.AdminMessage;
import com.ego.service.AdminMessageService;
import com.ego.service.CollectGoodsService;
import com.ego.utils.PageBean;

@Controller("commonController")
public class CommonController {
	@Resource(name="adminMessageService")
	private AdminMessageService adminMessageService;
	@Resource(name="collectGoodsService")
	private CollectGoodsService collectGoodsService;
	
	@RequestMapping("/admin/common/home")
	public String home(Model model){
		return "admin/common/index";
	}
	
	@RequestMapping("/admin/common/main")
	public String main(Model model){
		//首页需要的6大数据
		AdminMessage adminMessage = this.adminMessageService.selectOne();
		Integer warn = adminMessage.getWarn();
		System.out.println("库存紧张数量"+warn);
		System.out.println("++++++"+adminMessage.getTotalNum());
		model.addAttribute("adminMessage", adminMessage);
		
		//收藏排行榜
		PageBean<GoodCollectWithGoods> pageBean = this.collectGoodsService.selectGoodsCollect(1, 5);
		System.out.println(pageBean.getBeanList());
		model.addAttribute("collectList", pageBean.getBeanList());
		return "admin/common/main";
	}
	
}

