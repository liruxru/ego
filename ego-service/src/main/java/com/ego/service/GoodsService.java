package com.ego.service;


import java.util.List;

import com.ego.document.Goods;
import com.ego.mapper.po.Img;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchGoods;

public interface GoodsService {
	/***
	 * 全查  findAll
	 * 需要 CurrentPage  PageSize  Lock
	 * @param searchGoods
	 * @return
	 */
	PageBean<Goods> findAll(SearchGoods searchGoods);
	/**
	 * 类型全查   findAllByTypes
	 * 需要  Typename  CurrentPage  PageSize   Lock
	 * @param searchGoods
	 * @return
	 */
	PageBean<Goods> findAllByTypes(SearchGoods searchGoods);
	/**
	 * 店鋪全查商品   findAllByStores.
	 * 需要  Storename  CurrentPage  PageSize   Lock
	 * @param searchGoods
	 * @return
	 */
	PageBean<Goods> findAllByStores(SearchGoods searchGoods);
	/**
	 * id查一个
	 * @param id
	 * @return
	 */
	Goods findone(String id);
	/**
	 * 根据商品ID查训附属图片     selectImgsByGoodsId
	 * @param Id
	 * @return
	 */
	List<Img> selectImgsByGoodsId(String Id);
	/** 
	 * 多条件查询    selectGoodsBySearchGoods
	 * @param searchGoods
	 * @return
	 */
	PageBean<Goods> selectGoodsBySearchGoods(SearchGoods searchGoods);
	/**
	 * 添加商品addGoods
	 * @param goods
	 * @param lss 
	 * @param ls 
	 * @return
	 */
	ReturnMessage<Goods> addGoods(Goods goods, List<String> newImgs, List<String> imgs);
	/***
	 * 通过ID下嫁商品    deleteGoodsById
	 * @param GoodsId
	 * @return
	 */
	ReturnMessage<Goods> upodateGoodsLockById(String GoodsId,Integer lock);
	/**
	 * 通过IDS下嫁商品   deleteGoodsByIds
	 * @param GoodsId
	 * @return
	 */
	ReturnMessage<Goods> upodateGoodsLockByIds(String[] GoodsId,Integer lock);
	/**
	 * 修改商品    goods
	 * @param goods
	 * @return
	 */
	ReturnMessage<Goods> updateGoods(Goods goods);
	/**
	 * 库存紧张的商品
	 * @param searchGoods
	 * @return
	 */
	PageBean<Goods> warringGoods(SearchGoods searchGoods);

}




