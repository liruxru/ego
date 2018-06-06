package com.ego.service;

import com.ego.mapper.po.OrderItemsWithGoods;
import com.ego.utils.PageBean;

public interface OrderItemsService {
	/**
	 * 通过订单id查看订单条目
	 * @param orderId
	 * @return
	 */
	PageBean<OrderItemsWithGoods> selectOrderItemsByOrderId(Integer orderId,int pageCode,int pageSize);
	




}
