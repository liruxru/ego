package com.ego.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ego.bean.Pay;
import com.ego.mapper.po.Order;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.service.OrderService;
import com.ego.utils.PaymentUtil;

@RequestMapping("/pay")
@Controller
public class PayController {
	
	@Resource(name="orderService")
	private OrderService orderService;

	
	
	
	private static final String API_ID = "10001126856";
	private static final String API_KEY = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
	private static final String BACK_URL = "http://localhost:8080/EGO_web/pay/success";
	private static final String SEND_URL = "https://www.yeepay.com/app-merchant-proxy/node?";
	@RequestMapping("/dopay")
	public String pay(Model model, @RequestParam("orderId") String sn, @RequestParam("price") Double price,
			@RequestParam("pd_FrpId") String pd_FrpId) {
		this.orderService.updateOrderStatus(Integer.parseInt(sn),2);
		String hmac = PaymentUtil.buildHmac("Buy", API_ID, sn, price.toString(), "CNY", "", "", "", BACK_URL, "0", "",
				pd_FrpId, "0", API_KEY);
		StringBuffer sb = new StringBuffer(SEND_URL);
		sb.append("p0_Cmd=").append("Buy").append("&");
		sb.append("p1_MerId=").append(API_ID).append("&");
		sb.append("p2_Order=").append(sn).append("&");
		sb.append("p3_Amt=").append(price.toString()).append("&");
		sb.append("p4_Cur=").append("CNY").append("&");
		sb.append("p5_Pid=").append("").append("&");
		sb.append("p6_Pcat=").append("").append("&");
		sb.append("p7_Pdesc=").append("").append("&");
		sb.append("p8_Url=").append(BACK_URL).append("&");
		sb.append("p9_SAF=").append("0").append("&");
		sb.append("pa_MP=").append("").append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append("0").append("&");
		sb.append("hmac=").append(hmac);
		return "redirect:" + sb.toString();
	}
	@RequestMapping("/success")
	public String success(Model model,Pay pay) {
		boolean bln = PaymentUtil.verifyCallback(pay.getHmac(), pay.getP1_MerId(), pay.getR0_Cmd(),
				pay.getR1_Code(), pay.getR2_TrxId(), pay.getR3_Amt(), pay.getR4_Cur(), pay.getR5_Pid(), pay.getR6_Order(), pay.getR7_Uid(),
				pay.getR8_MP(), pay.getR9_BType(), API_KEY);
		if(bln) {
			this.orderService.updateOrderStatus(Integer.parseInt(pay.getP1_MerId()),2);
			return "/orders/success";
		}
		return "/orders/pay";
	}
	
	@RequestMapping("/gopay")
	public String gopay(Model model,Integer orderId) {
		OrderWithOrderItem orderwith= this.orderService.selectOrderWithOrderItemById(orderId);
//	model.addAttribute("orderId", orderId);
		
		Order order=new Order();
		order.setId(orderwith.getOrderId());
		order.setSn(orderwith.getSn());
		order.setSum(orderwith.getSum());
		
		model.addAttribute("order", order);
		return "user/order/pay";
	}
	
}
