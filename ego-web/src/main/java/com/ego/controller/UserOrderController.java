package com.ego.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ego.mapper.po.Area;
import com.ego.mapper.po.City;
import com.ego.mapper.po.Order;
import com.ego.mapper.po.Province;
import com.ego.mapper.po.Status;
import com.ego.mapper.po.ThOrderItem;
import com.ego.mapper.po.User;
import com.ego.mapper.po.relationPo.Address;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.service.AreaService;
import com.ego.service.CityService;
import com.ego.service.OrderService;
import com.ego.service.ProvinceService;
import com.ego.service.StatusService;
import com.ego.service.ThOrderItemService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SnUtils;

@Controller("userOrderController")
@RequestMapping("/user/order")
public class UserOrderController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="provinceService")
	ProvinceService provinceService;
	@Resource(name="cityService")
	CityService cityService;
	@Resource(name="areaService")
	AreaService areaService;
	@Resource(name="statusService")
	private StatusService statusService;
	@Resource(name="thOrderItemService")
	private ThOrderItemService thOrderItemService;
	
	@RequestMapping("/addtuihuo")
	@ResponseBody
	public ReturnMessage<ThOrderItem> addtuihuo(ThOrderItem thOrderItem,Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		thOrderItem.setUser(user.getId());
		thOrderItem.setShenhestatus(0);
		thOrderItem.setCreadedate(new Date());
		thOrderItem.setSn(SnUtils.getOrderSn());
		thOrderItem.setSnorders(String.valueOf(thOrderItem.getSnorders()));
		ReturnMessage<ThOrderItem> thOrder=this.thOrderItemService.insertThOrderItem(thOrderItem);
		System.out.println(thOrder.getResultCode());
		return thOrder;
	}
	
	@RequestMapping("/selectThorderitemsBysnOrders")
	@ResponseBody
	public ThOrderItem selectThBySnorders(String snorders) {
		ThOrderItem thOrderItem=this.thOrderItemService.selectThBySnOrders(snorders);
		return thOrderItem;
	}
	
	@RequestMapping("/gopay")
	public String gopay() {
		return "user/order/pay";
	}
	
	
	
	
	@RequestMapping("/addOrder")
	public String addOrder(Model disMdel,Integer[] cartId,HttpSession session, RedirectAttributes model,Integer buyNowCartId) {
		User user=(User) session.getAttribute("user");
		if(buyNowCartId!=null) {
			cartId=new Integer[] {buyNowCartId};
		}
		ReturnMessage<Order> returnMessage = orderService.addToOrderByCartIdsAndUserIdWithStore(user.getId(), cartId);
		String p=null;
		String c=null;
		String a=null;
		if (user.getProvince()!=null) {
			 p=provinceService.selectProvinceById(user.getProvince()).getName();
		}
		if (user.getCity()!=null) {
			 c=cityService.selectCityById(user.getCity()).getName();
		}
		if (user.getArea()!=null) {
			 a=areaService.selectAreaById(user.getArea()).getName();
		}
		String address=p+c+a;
		model.addFlashAttribute("address", address);
		model.addFlashAttribute("returnMessage", returnMessage);
		if(returnMessage.getResultCode()==0) {
			disMdel.addAttribute("order", returnMessage.getListBean().get(0));
//			disMdel.addAttribute("orderId", returnMessage.getListBean().get(0).getId());
			return "user/order/pay";
		}
		return "redirect:/user/cart/gomyCart";
	}
	@RequestMapping("/listMyOrder")
	public String listMyOrder(HttpSession session,Model model) {
		User user=(User) session.getAttribute("user");    
		PageBean<OrderWithOrderItem> selectOrderWithOrderItemByUser = 
				orderService.selectOrderWithOrderItemByUser(user.getId(), 1, 100);
		List<OrderWithOrderItem> orderWithOrderItemList = selectOrderWithOrderItemByUser.getBeanList();
		model.addAttribute("orderWithOrderItemList", orderWithOrderItemList);
		List<Status> listStatus=this.statusService.findAllStatus();
		List<Status> lstuiStatus=new ArrayList<Status>();
		List<Status> lsHuanStatus=new ArrayList<Status>();
		for (Status status : listStatus) {
			if (status.getId()==4 || status.getId()==6 || status.getId()==8 || status.getId()==10 || status.getId()==12) {
				lstuiStatus.add(status);
			}
			if (status.getId()==5 || status.getId()==7 || status.getId()==9 || status.getId()==11 || status.getId()==14) {
				lsHuanStatus.add(status);
			}
		}
		model.addAttribute("lstuiStatus", lstuiStatus);
		model.addAttribute("lsHuanStatus", lsHuanStatus);
		
		
		
		return "user/order/myOrder";
	}
	@RequestMapping("/selectOrderById")
	public String selectOrderById(Model model,Integer orderId) {
		OrderWithOrderItem orderWithOrderItem=orderService.selectOrderWithOrderItemById(orderId);
		model.addAttribute("orderWithOrderItem", orderWithOrderItem);
		return "user/order/myOrderDetail";
	}
	@RequestMapping("/selectOrderByStatus")
	public String selectOrderByStatus(Integer status,Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		PageBean<Order> pageOrder = orderService.selectOrderByUserIdAndStatus(user.getId(), status, 1, 100);
		model.addAttribute("pageOrder", pageOrder);
		return "user/order/myOrder";
	}
	//删除订单
	@RequestMapping("/deleteOrderById")
	@ResponseBody
	public ReturnMessage<Order> selectOrderByStatus(Integer orderId) {
		ReturnMessage<Order> returnMessage = orderService.deleteOrderByOrderId(orderId);
		return returnMessage;
	}
	
	//修改订单状态,确认收货,申请退货等
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public ReturnMessage<Order> updateOrderStatus(Integer orderId,Integer status) {
		ReturnMessage<Order> returnMessage = orderService.updateOrderStatus(orderId, status);
		return returnMessage;
	}
	//通过订单状态查询订单
	@RequestMapping("/selectOrderWithOrderItemByUserIdAndStatus")
	public String selectOrderWithOrderItemByUserIdAndStatus(Model model,Integer status,HttpSession session) {
		User user=(User) session.getAttribute("user");
		PageBean<OrderWithOrderItem> page = orderService.selectOrderWithOrderItemByUserIdAndStatus(user.getId(), status, 1, 16);
		List<OrderWithOrderItem> orderWithOrderItemList = page.getBeanList();
		model.addAttribute("orderWithOrderItemList", orderWithOrderItemList);
		return "user/order/myOrder";
	}
	
	//通过订单状态查询订单异步
	@RequestMapping("/selectOrderWithOrderItemByUserIdAndStatusAnsy")
	@ResponseBody
	public List<OrderWithOrderItem> selectOrderWithOrderItemByUserIdAndStatusAnsy(Model model,Integer status,HttpSession session) {
		User user=(User) session.getAttribute("user");
		PageBean<OrderWithOrderItem> page = orderService.selectOrderWithOrderItemByUserIdAndStatus(user.getId(), status, 1, 16);
		List<OrderWithOrderItem> orderWithOrderItemList = page.getBeanList();
		return orderWithOrderItemList;
	}
	
	
}
