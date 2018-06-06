package com.ego.service.impl;

import javax.annotation.Resource;

import com.ego.dao.impl.LiuYanDao;
import com.ego.document.Description;
import com.ego.document.PingJia;
import com.ego.service.LiuYanService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public class LiuYanServiceImpl implements LiuYanService{
	@Resource(name="liuYanDao")
	private LiuYanDao liuYanDao;
	public LiuYanDao getLiuYanDao() {
		return liuYanDao;
	}
	public void setLiuYanDao(LiuYanDao liuYanDao) {
		this.liuYanDao = liuYanDao;
	}
	public ReturnMessage<PingJia> addPingJia(Integer userId, Integer orderItemId, Description description) {
		// TODO Auto-generated method stub
		return null;
	}
	public ReturnMessage<PingJia> addZhuiPing(Integer userId, Integer orderItemId, Description description) {
		// TODO Auto-generated method stub
		return null;
	}
	public PageBean<PingJia> selectPingjiaByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return null;
	}
	public PageBean<PingJia> selectWeiHuiFuPingjia() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
