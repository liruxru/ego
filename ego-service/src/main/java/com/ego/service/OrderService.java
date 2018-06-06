package com.ego.service;

import java.util.Date;

import com.ego.mapper.po.Order;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface OrderService {
	/**
	 * 通过用户id和选择的购物车id生成订单
	 * @param userId
	 * @param cartIds
	 * @return
	 */
	ReturnMessage<Order> addToOrderByCartIdsAndUserId(Integer userId,Integer[] cartIds);
	/**
	 * 通过用户id查找全部订单
	 * @param userId
	 * @return
	 */
	PageBean<Order> selectOrderByUserId(Integer userId, int pageCode, int pageSize);
	/**
	 * 通过订单id和状态码更新订单状态
	 * @return
	 */
	ReturnMessage<Order> updateOrderStatus(Integer orderId,Integer status);
	/**
	 * 通过订单id删除订单
	 * @param orderId
	 * @return
	 */
	ReturnMessage<Order> deleteOrderByOrderId(Integer orderId);
	/**
	 * 用户的操做:通过订单状态和用户id 查询订单
	 * @param userId
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> selectOrderByUserIdAndStatus(Integer userId,Integer status ,int pageCode, int pageSize);
	/**
	 * 用户的操做:通过日期范围和用户id 查询订单
	 * @param userId
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> selectOrderByUserIdAndDate(Integer userId,int pageCode, int pageSize,Date Begin,Date end);
	
	
	/**
	 * 管理员的操做:通过订单状态查询订单
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<Order> selectOrderByStatus(Integer status ,int pageCode, int pageSize);

	/**
	 * 用户操做:通过查询订单同时拿出订单详情
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<OrderWithOrderItem> selectOrderWithOrderItemByUser(Integer userId ,int pageCode, int pageSize);
	/**
	 * 通过订单id 查看订单详情 包括商品和店铺信息
	 * @param orderId
	 * @return
	 */
	OrderWithOrderItem selectOrderWithOrderItemById(Integer orderId);
	/**
	 * 为每一个店铺创建一个订单
	 * @param userId
	 * @param cartIds
	 * @return
	 */
	ReturnMessage<Order> addToOrderByCartIdsAndUserIdWithStore(Integer userId, Integer[] cartIds);
	/**
	 * 通过订单状态和用户id查询订单+订单条目
	 * @param userId
	 * @param status
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<OrderWithOrderItem> selectOrderWithOrderItemByUserIdAndStatus(Integer userId, Integer status, int pageCode, int pageSize);
	/**
	 * 通过订单状态查询订单+订单条目
	 * @param userId
	 * @param status
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<OrderWithOrderItem> selectOrderWithOrderItemByStatus(Integer status, int pageCode, int pageSize);
	/**
	 * 修改订单状态以及其对应的订单明细状态
	 * @param orderId
	 * @return
	 */
	/*Boolean updateOrderStatusWithOrderItems(String orderId,Integer status);*/
	
}
