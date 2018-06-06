package com.ego.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.document.Goods;
import com.ego.document.PingJia;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.mapper.po.User;
import com.ego.service.GoodsService;
import com.ego.service.PingJiaService;
import com.ego.service.UserService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
@Controller("messageController")
@RequestMapping("/admin/message")
public class MessageController {
	
	@Resource(name="pingJiaService")
	private PingJiaService pingJiaService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	@RequestMapping("/list")
	public String list(Model model,Integer pageCode) {
/*		if(pageCode==null||"".equals(pageCode)) {
			pageCode = 1;
		}
		Integer pageSize = 5 ;
		PageBean<PingJiaWithDescription>  page = this.pingJiaService.selectPingjia(pageCode, pageSize);
		List<PingJiaWithDescription> pingJiaList = page.getBeanList();
		List<User>  userList  = new ArrayList<User>();
		List<Goods> goodsList = new ArrayList<Goods>();
		for (PingJiaWithDescription pingJiaWithDescription : pingJiaList) {
			User  user  = new User();
			Goods goods = new Goods();
			user = this.userService.selectByPrimaryKey(pingJiaWithDescription.getUserId());
			goods= this.goodsService.findone(pingJiaWithDescription.getGoodsId());
			userList.add(user);
			goodsList.add(goods);
		}
		for (int i = 0; i < pingJiaList.size(); i++) {
			if(pingJiaList.get(i).getUserId()==userList.get(i).getId()) {
				pingJiaList.get(i).setUsername(userList.get(i).getUsername());
			}
			if(pingJiaList.get(i).getGoodsId().equals(goodsList.get(i).get_id())) {
				pingJiaList.get(i).setGoodsfullname(goodsList.get(i).getFullname());
			}
		}
		model.addAttribute("messagesList", pingJiaList);*/
	    //return "admin/message/message";
	    return "admin/test";
		
	}
	
	@RequestMapping("/answer")
	public String answer(@RequestParam(value = "pageCode", required = false)Integer pageCode,Model model) {
		if(pageCode==null) {
			pageCode = 1;
		}
		Integer pageSize = 5 ;
		PageBean<PingJiaWithDescription>  page = this.pingJiaService.selectWeiHuiFuPingjia(pageCode, pageSize);
		List<PingJiaWithDescription> pingJiaList = page.getBeanList();
		List<User>  userList  = new ArrayList<User>();
		List<Goods> goodsList = new ArrayList<Goods>();
		for (PingJiaWithDescription pingJiaWithDescription : pingJiaList) {
			User  user  = new User();
			Goods goods = new Goods();
			user = this.userService.selectByPrimaryKey(pingJiaWithDescription.getUserId());
			goods= this.goodsService.findone(pingJiaWithDescription.getGoodsId());
			userList.add(user);
			goodsList.add(goods);
		}
		for (int i = 0; i < pingJiaList.size(); i++) {
			if(pingJiaList.get(i).getUserId()==userList.get(i).getId()) {
				pingJiaList.get(i).setUsername(userList.get(i).getUsername());
			}
			if(pingJiaList.get(i).getGoodsId().equals(goodsList.get(i).get_id())) {
				pingJiaList.get(i).setGoodsfullname(goodsList.get(i).getFullname());
			}
		}
		model.addAttribute("messagesList", pingJiaList);		
	    return "admin/message/answerMessage";
		
	}
	@RequestMapping("/huifu")
	@ResponseBody
	public Map huifuMessage(String pingJiaId,String description, String descriptionId) {
		ReturnMessage<PingJia> pa= this.pingJiaService.PingJiaHuiFu(pingJiaId, description, descriptionId);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", pa.getResultCode());
		map.put("message", pa.getMessage());
		return map;
		
	}

}
