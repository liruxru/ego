package com.ego.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.mapper.po.Order;
import com.ego.mapper.po.ThOrderItem;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.mapper.po.relationPo.ThandUserGood;
import com.ego.service.OrderService;
import com.ego.service.ThOrderItemService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

@Controller("thorderController")
@RequestMapping("/admin/thorder")
public class ThOrderController {
	@Resource(name="thOrderItemService")
	private ThOrderItemService thOrderItemService;
	
	@RequestMapping("/list1")
	public String goView(Model model,Integer pageCode) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer pageSize = 3;
		Integer shenhestatus = 0;
		PageBean<ThandUserGood> page = this.thOrderItemService.selectAll(pageSize, pageCode,shenhestatus);
		model.addAttribute("thorderList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/thorder/list1";
	}
	
	/**
	 * 管理员审核退货的方法
	 * @param model
	 * @param id
	 * @return  Map
	 */
	@RequestMapping("/tuihuo")
	@ResponseBody
	public Map send(Model model , Integer id,String sn) {
		ThOrderItem thOrderItem = new ThOrderItem();
		thOrderItem.setId(id);
		thOrderItem.setSnorders(sn);
		ReturnMessage<ThOrderItem> rm =this.thOrderItemService.updateThOrderItem(thOrderItem);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", rm.getResultCode());
		map.put("message", rm.getMessage());
		return map;
		
	}
	
	@RequestMapping("/list2")
	public String list2(Model model,Integer pageCode) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer pageSize = 3;
		Integer shenhestatus = 1;
		PageBean<ThandUserGood> page = this.thOrderItemService.selectAll(pageSize, pageCode,shenhestatus);
		model.addAttribute("thorderList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/thorder/list2";
	}
	
	
	/*@RequestMapping("/list3")
	public String list3(Model model,Integer pageCode) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer pageSize = 1;
		PageBean<OrderWithOrderItem> page = this.orderService.selectOrderWithOrderItemByStatus(11, pageCode, pageSize);
		model.addAttribute("orderList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/order/list3";
	}
	*/
}
