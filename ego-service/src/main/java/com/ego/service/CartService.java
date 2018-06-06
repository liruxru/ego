package com.ego.service;

import com.ego.mapper.po.Cart;
import com.ego.mapper.po.relationPo.StoreCart;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface CartService {
	/**
	 * 通过用户id和商品id添加到购物车
	 * @param cartId
	 * @param userId
	 * @return
	 */
	ReturnMessage<Cart> addToCart(String goodsId ,Integer userId);
	/**
	 * 通过用户id查看购物车
	 * @param cartId
	 * @param userId
	 * @return
	 */
	PageBean<StoreCart> selectCartByUserId(Integer userId,Integer pageCode,Integer pageSize);
	/**
	 * 通过购物车id和前台传来的运算符数量+ - 1
	 * @param cartId
	 * @param Tag
	 * @return
	 */
	ReturnMessage<Cart> updateCartBycartIdAndTag(Integer cartId,String Tag);
	/**
	 * 通过购物车id删除购物车
	 * @param cartId
	 * @return
	 */
	ReturnMessage<Cart> deleteCartBycartId(Integer cartId);
	/**
	 * 通过购物车id数组 批量删除购物车
	 * @param cartId
	 * @return
	 */
	ReturnMessage<Cart> deleteCartBycartIds(Integer[] cartIds);
	ReturnMessage<Cart> addToCart(String goodsId, Integer userId, Integer cartNum);

}
