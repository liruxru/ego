package com.ego.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.document.Goods;
import com.ego.mapper.po.Store;
import com.ego.service.GoodsService;
import com.ego.service.StoreService;
import com.ego.utils.PageBean;
import com.ego.utils.SearchGoods;
@Controller
@RequestMapping("/user/store")
public class UserStoreController {
	@Resource(name="storeService")
	private StoreService storeService;
	@Resource(name="goodsService")
	private	GoodsService goodsService;
	@RequestMapping("/showStore")
	public String showStore(Model model,Integer storeId,Integer pageCode) {
		Store store=storeService.selectStoreById(storeId);
		String storeName = store.getName();
		SearchGoods searchGoods=new SearchGoods();
		searchGoods.setStorename(storeName);
		searchGoods.setPageSize(16);
		if(pageCode==null) {
			searchGoods.setCurrentPage(1);
		}else {
			searchGoods.setCurrentPage(pageCode);
		}
		searchGoods.setLock(0);
		PageBean<Goods> page=goodsService.findAllByStores(searchGoods);
		List<Goods> storeGoodsList = page.getBeanList();
		model.addAttribute("store", store);
		model.addAttribute("storeGoodsList", storeGoodsList);
		//店铺详情页
		return "user/store/store";
	}
}
