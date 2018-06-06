package com.ego.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;

import com.ego.dao.impl.GoodsDao;
import com.ego.document.Goods;
import com.ego.mapper.OrderItemsMapper;
import com.ego.mapper.po.OrderItemsWithGoods;
import com.ego.service.OrderItemsService;
import com.ego.utils.PageBean;
import com.mongodb.BasicDBObject;

public class OrderItemsServiceImpl implements OrderItemsService {
	@Resource(name="orderItemsMapper")
	private OrderItemsMapper  orderItemsMapper;
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	public OrderItemsMapper getOrderItemsMapper() {
		return orderItemsMapper;
	}
	public void setOrderItemsMapper(OrderItemsMapper orderItemsMapper) {
		this.orderItemsMapper = orderItemsMapper;
	}

	public PageBean<OrderItemsWithGoods> selectOrderItemsByOrderId(Integer orderId, int pageCode, int pageSize) {
		PageBean<OrderItemsWithGoods> page=new PageBean<OrderItemsWithGoods>(pageCode, pageSize);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		int count=orderItemsMapper.countByOrderId(orderId);
		page.setCount(count);
		List<OrderItemsWithGoods>  OrderItemsWithGoodsList=orderItemsMapper.selectOrderItemsWithGoodsByOrderId(orderId,offset,length);
		Map<String,Object> map=new HashMap<String,Object>();
		for (OrderItemsWithGoods orderItemsWithGoods : OrderItemsWithGoodsList) {
			//去document查找信息
			map.clear();
			map.put("_id",new ObjectId(orderItemsWithGoods.getGoods()));
			BasicDBObject BasicDBObject=new BasicDBObject(map);
			Goods goods=goodsDao.findOne(BasicDBObject, "goods");
			orderItemsWithGoods.setGoodsAll(goods);
		}
		page.setBeanList(OrderItemsWithGoodsList);
		return page;
	}
	
}
