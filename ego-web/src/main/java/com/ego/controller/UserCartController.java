package com.ego.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ego.mapper.po.Area;
import com.ego.mapper.po.Cart;
import com.ego.mapper.po.City;
import com.ego.mapper.po.Order;
import com.ego.mapper.po.Province;
import com.ego.mapper.po.User;
import com.ego.mapper.po.relationPo.StoreCart;
import com.ego.service.AreaService;
import com.ego.service.CartService;
import com.ego.service.CityService;
import com.ego.service.GoodsService;
import com.ego.service.ProvinceService;
import com.ego.service.UserService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user/cart")
public class UserCartController {
	
	@Resource(name="cartService")
	private CartService cartService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="provinceService")
	ProvinceService provinceService;
	@Resource(name="cityService")
	CityService cityService;
	@Resource(name="areaService")
	AreaService areaService;
	@Resource(name="goodsService")
	GoodsService goodsService;
	@RequestMapping("/gomyCart")
	public String gomyCart(Model model,HttpSession session,@ModelAttribute("returnMessage") ReturnMessage<Order> returnMessage) {
		User user=(User) session.getAttribute("user");
		//分页会出现不可预测的bug,请让pageSize足够大
		PageBean<StoreCart> page = this.cartService.selectCartByUserId(user.getId(),1,100);
		List<StoreCart> storeCartList = page.getBeanList();
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
		model.addAttribute("storeCartList", storeCartList);
		model.addAttribute("address", address);
		model.addAttribute("returnMessage", returnMessage);
		return "user/cart/myCart";
	}
	@RequestMapping("/deleteById")
	@ResponseBody
	public ReturnMessage<Cart> deleteById(Integer cartId) {
		ReturnMessage<Cart> returnMessage = cartService.deleteCartBycartId(cartId);
		return returnMessage;
	}
	@RequestMapping("/updateCartNum")
	@ResponseBody
	public ReturnMessage<Cart> updateCartNum(Integer cartId,String tag) {
		ReturnMessage<Cart> returnMessage = cartService.updateCartBycartIdAndTag(cartId, tag);
		return returnMessage;
	}
	@RequestMapping("/batchDeleteCart")
	@ResponseBody
	public ReturnMessage<Cart> batchDeleteCart(String cartIds) {
		String[] ids=cartIds.split(",");
		Integer[] cartId=new Integer[ids.length];
		for (int i = 0; i < cartId.length; i++) {
			cartId[i]=Integer.valueOf(ids[i]);
		}
		ReturnMessage<Cart> returnMessage = cartService.deleteCartBycartIds(cartId);
		return returnMessage;
	}
	
	@RequestMapping("/addToCart")
	@ResponseBody
	public ReturnMessage<Cart> addToCart(String goodsId,HttpSession session,@RequestParam(value="cartNum",required=false) Integer cartNum) {
		User user=(User) session.getAttribute("user");
		ReturnMessage<Cart> returnMessage=null;
		if(cartNum==null) {
			returnMessage = cartService.addToCart(goodsId, user.getId());
		}else {
			returnMessage=cartService.addToCart(goodsId, user.getId(),cartNum);
		}
		
		return returnMessage;
	}
	@RequestMapping("/buyNow")
	public String buyNow(String goodsId,Integer cartNum,HttpSession session,RedirectAttributes model,HttpServletResponse response) {
		User user=(User) session.getAttribute("user");
		ReturnMessage<Cart> returnMessage=null;
		if(cartNum==null) {
			returnMessage = cartService.addToCart(goodsId, user.getId());
		}else {
			returnMessage=cartService.addToCart(goodsId, user.getId(),cartNum);
		}
		if(returnMessage.getResultCode()==0) {
			return "redirect:/user/order/addOrder?buyNowCartId="+returnMessage.getBean().getId();
		}else {
			try {
				JSONObject json=JSONObject.fromObject(returnMessage);
				response.getWriter().println(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
