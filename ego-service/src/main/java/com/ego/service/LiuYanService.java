package com.ego.service;

import com.ego.document.Description;
import com.ego.document.PingJia;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface LiuYanService {
	/**
	 * 评价添加  用户的id ,订单条目的id
	 * @param userId
	 * @param orderItemId
	 * @param description
	 * @return
	 */
	ReturnMessage<PingJia> addPingJia(Integer userId,
			Integer orderItemId,Description description);
	/**
	 * 追加评价
	 * @param userId
	 * @param goodsId
	 * @param description
	 * @return
	 */
	ReturnMessage<PingJia> addZhuiPing(Integer userId,
			Integer orderItemId,Description description);
	
	/**
	 * 商品所有评价的遍历  分页  
	 * 通过商品id 查找所有订单条目,再去monodb 通过所有订单条目查找所有评价
	 * @param goodsId
	 * @return
	 */
	PageBean<PingJia> selectPingjiaByGoodsId(Integer goodsId);
	/**
	 * 常看未回复留言    PingjiaByListCode
	 * 
	*/
	PageBean<PingJia> selectWeiHuiFuPingjia();
	
	
}
