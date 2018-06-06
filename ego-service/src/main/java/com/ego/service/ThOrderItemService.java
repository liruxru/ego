package com.ego.service;

import com.ego.mapper.po.ThOrderItem;
import com.ego.mapper.po.relationPo.ThandUserGood;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface ThOrderItemService {

	/**
	 * findAllOrderItems 全查所有的退换货信息，
	 * 需要  int offset, int length
	 * @param thOrderItem
	 * @return
	 */
	PageBean<ThOrderItem> selectAllOrderItems(PageBean<ThOrderItem> pageBean);

	/**
	 * selectAllOrderItemsByUserId 用户id查询退换货信息
	 * 需要  int userId, int offset, int length
	 * @param pageBean
	 * @return
	 */
	PageBean<ThOrderItem> selectAllOrderItemsByUserId(int userId, PageBean<ThOrderItem> pageBean);

	/**
	 * addThOrderItem 用户添加
	 * 需要传整个对象
	 * @param thOrderItem
	 * @return
	 */
	ReturnMessage<ThOrderItem> insertThOrderItem(ThOrderItem thOrderItem);

	/**
	 * updateThOrderItem 管理员审批状态改变
	 * 需要传整个对象
	 * @param thOrderItem
	 * @return
	 */
	ReturnMessage<ThOrderItem> updateThOrderItem(ThOrderItem thOrderItem);

	/**
	 * 通过订单明细编号查询退货货信息
	 * @param snorders
	 * @return
	 */
	ThOrderItem selectThBySnOrders(String snorders);
	
	
	/**
	 * 查看所有未审核的退单，
	 * @param snorders
	 * @return
	 * @author 秦健
	 * @param shenhestatus 
	 */
	PageBean<ThandUserGood> selectAll(Integer pageSize,Integer pageCode, Integer shenhestatus);


}
