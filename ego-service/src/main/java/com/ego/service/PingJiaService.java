package com.ego.service;

import com.ego.document.PingJia;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface PingJiaService {
	/**
	 * 评价添加 
	 * @param userId	 用户的id 
	 * @param orderItemId 订单条目的id
	 * @param description 评价的内容
	 * @return
	 */
	ReturnMessage<PingJia> addPingJia(Integer userId,
			Integer orderItemId,String description);
	/**
	 * 追加评价
	 * @param pingJiaId  原来评价的id
	 * @param description
	 * @return
	 */
	ReturnMessage<PingJia> addZhuiPing(String pingJiaId,String description);
	/**
	 * 回复评价
	 * @param pingJiaId  评价的id
	 * @param description    回复的内容
	 * @param   descriptionId    要回复评价内容的id
	 * @return 		
	 */
	ReturnMessage<PingJia> PingJiaHuiFu(String pingJiaId,String description,String descriptionId);
	

	/**
	 * 商品所有评价的遍历  分页  
	 * 查找所有订单条目,再去monodb 通过所有订单条目查找所有评价
	 * @param goodsId
	 * @return
	 */
	PageBean<PingJiaWithDescription> selectPingjia(int pageCode,int pageSize);
	
	/**
	 * 商品所有评价的遍历  分页  
	 * 通过商品id 查找所有订单条目,再去monodb 通过所有订单条目查找所有评价
	 * @param goodsId
	 * @return
	 */
	PageBean<PingJiaWithDescription> selectPingjiaByGoodsId(String goodsId,int pageCode,int pageSize);
	/**
	 * 查看未回复评价
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	PageBean<PingJiaWithDescription> selectWeiHuiFuPingjia(int pageCode,int pageSize);
	/**
	 * 设置评价为已经回复  意思是不回复直接修改状态
	 * @param pingJiaId
	 * @return
	 */
	ReturnMessage<PingJia> updatePingjiaToHuiFu(String pingJiaId);
	PingJiaWithDescription selectPingJiaByOrderItem(Integer orderItemId);



}
