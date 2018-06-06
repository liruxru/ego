package com.ego.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.dao.impl.GoodsDao;
import com.ego.document.Goods;
import com.ego.mapper.OrderMapper;
import com.ego.mapper.ThOrderItemMapper;
import com.ego.mapper.po.ThOrderItem;
import com.ego.mapper.po.relationPo.ThandUserGood;
import com.ego.service.ThOrderItemService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public class ThOrderItemServiceImpl implements ThOrderItemService{

	@Resource(name="thOrderItemMapper")
	private ThOrderItemMapper thOrderItemMapper;
	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	
	public ThOrderItemMapper getThOrderItemMapper() {
		return thOrderItemMapper;
	}
	public void setThOrderItemMapper(ThOrderItemMapper thOrderItemMapper) {
		this.thOrderItemMapper = thOrderItemMapper;
	}
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
	/*public PageBean<ThOrderItem> selectAllOrderItems(PageBean<ThOrderItem> pageBean) {
		int count =thOrderItemMapper.countWeiShenHe();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<ThOrderItem> OrderItemList= thOrderItemMapper.selectAllOrderItems(offset,length);
		pageBean.setBeanList(OrderItemList);
		return pageBean;
	}*/
	public PageBean<ThOrderItem> selectAllOrderItemsByUserId(int userId,PageBean<ThOrderItem> pageBean) {
		int count =thOrderItemMapper.countAllOrderItems();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<ThOrderItem> OrderItemList= thOrderItemMapper.selectAllOrderItemsByUserId(userId,offset,length);
		pageBean.setBeanList(OrderItemList);
		return pageBean;
	}
    /**
     * 根据审核状态来查询
     */
	public PageBean<ThandUserGood> selectAll(Integer pageSize,Integer pageCode, Integer shenhestatus) {
		int count =thOrderItemMapper.count(shenhestatus);
		PageBean<ThandUserGood> pageBean = new PageBean<ThandUserGood>(pageCode, pageSize, count,null);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<ThandUserGood> OrderItemList= thOrderItemMapper.selectByShenhestatus(shenhestatus,offset, length);
		Map<String, Object> map = new HashMap<String, Object>();
		for (ThandUserGood thandUserGood : OrderItemList) {
			Goods goods = new Goods();
			map.put("_id", new  ObjectId(thandUserGood.getGoods()));
			goods = this.goodsDao.findOne(map, "goods");
			thandUserGood.setFullname(goods.getFullname());
			thandUserGood.setGoodscoverimg(goods.getCoverimg());
			thandUserGood.setGoodsdescription(goods.getDescription());
			thandUserGood.setGoodsprice(goods.getPrice());
		}
		pageBean.setBeanList(OrderItemList);
		return pageBean;
	}
	
	public ReturnMessage<ThOrderItem> insertThOrderItem(ThOrderItem thOrderItem) {
		ReturnMessage<ThOrderItem> rm=new ReturnMessage<ThOrderItem>();
		try {
			thOrderItemMapper.insertSelective(thOrderItem);
//			orderMapper.updateOrderStatus(thOrderItem.getSnorders(), thOrderItem.getStatus());
//			thOrderItemMapper.seleOrderItemByUserIdAndGoods(thOrderItem.getUser());
			
			rm.setResultCode(0);
			return rm;
		} catch (Exception e) {
			e.printStackTrace();
			rm.setResultCode(1);
			rm.setMessage("退货换货失败，请重新提交！"+e.getMessage());
			return rm;
		}
	}
	
	public ReturnMessage<ThOrderItem> updateThOrderItem(ThOrderItem thOrderItem) {
		ReturnMessage<ThOrderItem> rm=new ReturnMessage<ThOrderItem>();
		try {
			thOrderItem.setStatus(12);
			thOrderItem.setShenhestatus(1);
			thOrderItemMapper.updateByPrimaryKeySelective(thOrderItem);
			orderMapper.updateOrderStatus(Integer.parseInt(thOrderItem.getSnorders()), thOrderItem.getStatus());
			rm.setResultCode(0);
			return rm;
		} catch (Exception e) {
			rm.setResultCode(1);
			rm.setMessage("修改失败，请重新提交！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return rm;
		}
	}
	
	public ThOrderItem selectThBySnOrders(String snorders) {
		ThOrderItem thOrderItem=thOrderItemMapper.selectThBySnOrders(snorders);
		return thOrderItem;
	}
	public PageBean<ThOrderItem> selectAllOrderItems(PageBean<ThOrderItem> pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
