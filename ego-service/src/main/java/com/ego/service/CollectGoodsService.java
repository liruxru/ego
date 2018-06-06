package com.ego.service;

import com.ego.document.CollectGoods;
import com.ego.document.Goods;
import com.ego.document.po.GoodCollectWithGoods;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

/**
 * 商品收藏的接口
 * @author Administrator
 *
 */
public interface CollectGoodsService {
	
	/**
	 * 添加商品收藏
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	ReturnMessage<CollectGoods> addGoodsToCollect(Integer userId,String goodsId);
	/**
	 * 移除商品收藏
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	ReturnMessage<CollectGoods> deleteGoodsFromCollect(Integer userId,String goodsId);
	/**
	 * 浏览收藏商品
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	PageBean<Goods> selectGoodsFromCollectByUserId(Integer userId,int pageCode,int pageSize);
	/**
	 * 通过商品id统计收藏的人数
	 * @param goodsId
	 * @return
	 */
	Long countUserByCollectGoodsId(String  goodsId);
	/**
	 * 查看所有商品被收藏的人数,同时返回商品和收藏人数
	 * @param goodsId
	 * @return
	 */
	public PageBean<GoodCollectWithGoods> selectGoodsCollect(int pageCode,int pageSize);
}
