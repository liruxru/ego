package com.ego.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.mapper.po.Order;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.service.OrderService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

@Controller("orderController")
@RequestMapping("/admin/order")
public class OrderController {
	@Resource(name="orderService")
	private OrderService orderService;
	
	@RequestMapping("/list1")
	public String goView(Model model,Integer pageCode) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer pageSize = 1;
		PageBean<OrderWithOrderItem> page = this.orderService.selectOrderWithOrderItemByStatus(1, pageCode, pageSize);
		model.addAttribute("orderList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/order/list1";
	}
	
	@RequestMapping("/list2")
	public String list2(Model model,Integer pageCode) {
		if(pageCode==null||"".equals(pageCode)){
			pageCode =1;
		}
		Integer pageSize = 1;
		PageBean<OrderWithOrderItem> page = this.orderService.selectOrderWithOrderItemByStatus(2, pageCode, pageSize);
		model.addAttribute("orderList", page.getBeanList());
		model.addAttribute("page", page);
		return "admin/order/list2";
	}
	@RequestMapping("/send")
	@ResponseBody
	public Map send(Model model , Integer id) {
		ReturnMessage<Order> rm =this.orderService.updateOrderStatus(id, 3);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", rm.getResultCode());
		map.put("message", rm.getMessage());
		return map;
		
	}
	
	@RequestMapping("/list3")
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
	
}
