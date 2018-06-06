package com.ego.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.ego.dao.impl.GoodsDao;
import com.ego.dao.impl.PingJiaDao;
import com.ego.document.Goods;
import com.ego.mapper.OrderMapper;
import com.ego.mapper.po.relationPo.AdminMessage;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.service.AdminMessageService;
import com.ego.utils.PageBean;
import com.ego.utils.SearchGoods;

public class AdminMessageServiceImpl implements AdminMessageService {
	
	public static final String DB_NAME = "goods";
	
	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	@Resource(name = "goodsDao")
	private GoodsDao goodsDao;
	@Resource(name="pingJiaDao")
	private PingJiaDao pingJiaDao;
	
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	public PingJiaDao getPingJiaDao() {
		return pingJiaDao;
	}
	public void setPingJiaDao(PingJiaDao pingJiaDao) {
		this.pingJiaDao = pingJiaDao;
	}
	public AdminMessage selectOne() {
		AdminMessage adminMessage = null;
		SearchGoods searchGoods = new SearchGoods(null, null, null, 50, null, null, null, null, null, 9, 1, null, null, 3);
		try {
			//adminMessage = new AdminMessage(totalSum, totalNum, uncheckNum, messageNum, warn, noAnswerNum);
			PageBean<Goods> page = this.goodsDao.findAllBySearchGoods(searchGoods, DB_NAME);
			List<OrderWithOrderItem> orderWithOrderItemList= orderMapper.selectOrderWithOrderItemByStatus(11, 0, 500000);
			Integer noAnswerNum  = this.pingJiaDao.countWeiHuiFuPingjia("pingjia");
			BigDecimal  totalSum = this.orderMapper.selectCountMoney();
			Integer uncheckNum   = this.orderMapper.countOrderByStatus(2);
			Integer messageNum   = this.pingJiaDao.count("pingjia");
			adminMessage = new AdminMessage(totalSum, orderWithOrderItemList.size(), uncheckNum, messageNum,page.getCount() , noAnswerNum);
			System.out.println("++++++"+adminMessage.getTotalNum());
			System.out.println(adminMessage.getNoAnswerNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminMessage;
	}

}
