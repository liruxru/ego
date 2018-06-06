package com.ego.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.mapper.po.User;
import com.ego.service.CartService;
import com.ego.service.StoreService;
import com.ego.service.UserService;

@Controller
@RequestMapping("/user/person")
public class UserController {

	@Resource(name="userService")
	private UserService userService;
	@RequestMapping("/golist")
	public String person(Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "user/person/infolist";
	}
	
	
	
}
