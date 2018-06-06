package com.ego.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.dao.impl.GoodsDao;
import com.ego.document.Goods;
import com.ego.mapper.CartMapper;
import com.ego.mapper.StoreMapper;
import com.ego.mapper.po.Cart;
import com.ego.mapper.po.relationPo.CartWithGoods;
import com.ego.mapper.po.relationPo.StoreCart;
import com.ego.service.CartService;
import com.ego.utils.PageBean;
import com.ego.utils.ResultUtils;
import com.ego.utils.ReturnMessage;
import com.mongodb.BasicDBObject;

public class CartServiceImpl implements CartService {
	@Resource(name="cartMapper")
	private CartMapper cartMapper;
	public CartMapper getCartMapper() {
		return cartMapper;
	}
	public void setCartMapper(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	@Resource(name="storeMapper")
	private StoreMapper storeMapper;
	public StoreMapper getStoreMapper() {
		return storeMapper;
	}
	public void setStoreMapper(StoreMapper storeMapper) {
		this.storeMapper = storeMapper;
	}
	public ReturnMessage<Cart> addToCart(String goodsId, Integer userId) {
		ReturnMessage<Cart> returnMessage=new ReturnMessage<Cart>();
		Cart cart=cartMapper.selectCartByGoodsIdAndUserId(goodsId,userId);
		try {
			if(cart==null) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id",new ObjectId(goodsId));
				BasicDBObject bt=new BasicDBObject(map);
				Goods goods=goodsDao.findOne(bt, "goods");
				if(goods==null) {
					throw new Exception("对不起,商品不存在或者已经下架,请选择其他商品");
				}
				BigDecimal price=goods.getPrice();
				if(goods.getSale()!=null) {
					price=goods.getSale();
				}
				
				cart=new Cart(null, userId, goodsId,price,1,
						goods.getCoverimg(), goods.getName(),
						new Date(), null);
				cartMapper.insert(cart);
				returnMessage.setMessage("添加购物车成功");
			}else {
				cart.setNum(cart.getNum()+1);
				cartMapper.updateCartNumByPrimaryKey(cart);
				returnMessage.setMessage("购物车数量+1");
			}
			returnMessage.setResultCode(0);
			return returnMessage;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	public PageBean<StoreCart> selectCartByUserId(Integer userId,Integer pageCode,Integer pageSize) {
		PageBean<StoreCart> page=new PageBean<StoreCart>();
		int count=cartMapper.countUserCart(userId);
		page.setCount(count);
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		List<Cart> cartList=cartMapper.selectCartByUser(userId,offset,length);
		if(cartList==null) {
			cartList=new ArrayList<Cart>();
		}
		List<StoreCart> storeCartList=convertCart(cartList);
		page.setBeanList(storeCartList);
		return page;
	}
	private List<StoreCart> convertCart(List<Cart> cartList) {
		List<StoreCart> storeCartList=new ArrayList<StoreCart>();
		List<CartWithGoods> cartWithGoodsList=new ArrayList<CartWithGoods>();
		
		Map<Integer,String> storeMap=new HashMap<Integer,String>();
		for (Cart cart : cartList) {
			String goodsId=cart.getGoods();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("_id",new ObjectId(goodsId));
			BasicDBObject bt=new BasicDBObject(map);
			Goods goods=goodsDao.findOne(bt, "goods");
			if(goods==null) {
				goods=new Goods();
			}
			CartWithGoods cartWithGoods=new CartWithGoods();
			cartWithGoods.setCart(cart);
			cartWithGoods.setGoods(goods);
			cartWithGoodsList.add(cartWithGoods);
			Integer storeId=storeMapper.selectStoreIdByName(goods.getStore());
			storeMap.put(storeId, goods.getStore());
		}
		Set<Entry<Integer, String>> entrySet = storeMap.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			StoreCart storeCart=new StoreCart(entry.getKey(), entry.getValue(), null);
			storeCartList.add(storeCart);
		}
		
		for (StoreCart storeCart : storeCartList) {
			 List<CartWithGoods> listCartWihtGoods=new ArrayList<CartWithGoods>();
			for (CartWithGoods cartWithGoods : cartWithGoodsList) {
				if(storeCart.getStoreName().
						equals(cartWithGoods.getGoods().getStore())) {
					listCartWihtGoods.add(cartWithGoods);
				}
			}
			storeCart.setCartWihtGoodsList(listCartWihtGoods);
		}
		return storeCartList;
		
	}
	public ReturnMessage<Cart> updateCartBycartIdAndTag(Integer cartId, String Tag) {
		ReturnMessage<Cart> returnMessage=new ReturnMessage<Cart>();
		Cart cart=cartMapper.selectByPrimaryKey(cartId);
		try {
			if("addNum".equalsIgnoreCase(Tag)) {
				cart.setNum(cart.getNum()+1);
				cartMapper.updateCartNumByPrimaryKey(cart);
//				returnMessage.setMessage("购物车数量+1");
			}else {
				if(cart.getNum()==1) {
					cartMapper.deleteByPrimaryKey(cartId);
					returnMessage.setMessage("购物车删除成功");
				}else {
					cart.setNum(cart.getNum()-1);
//					returnMessage.setMessage("购物车数量-1");
					cartMapper.updateCartNumByPrimaryKey(cart);
				}
				
			}
			returnMessage.setResultCode(0);
			return returnMessage;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	public ReturnMessage<Cart> deleteCartBycartId(Integer cartId) {
		try {
			cartMapper.deleteByPrimaryKey(cartId);
			return ResultUtils.success();
		} catch (Exception e) {
			return ResultUtils.error(e);
		}
	}
	public ReturnMessage<Cart> deleteCartBycartIds(Integer[] cartIds) {
		try {
			cartMapper.deleteByCartIds(cartIds);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	
	/**
	 * 通过用户查找购物车和里边的商品
	 */
	public PageBean<CartWithGoods> selectCartWithGoodsByUserId(Integer userId,Integer pageCode,Integer pageSize) {
		PageBean<CartWithGoods> page=new PageBean<CartWithGoods>();
		int count=cartMapper.countUserCart(userId);
		page.setCount(count);
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		List<Cart> cartList=cartMapper.selectCartByUser(userId,offset,length);
		if(cartList==null) {
			cartList=new ArrayList<Cart>();
		}
		List<CartWithGoods> cartWithGoodsList=new ArrayList<CartWithGoods>();
		Map<String,Object> map=new HashMap<String,Object>();
		for (Cart cart : cartList) {
			map.clear();
			map.put("_id",new ObjectId(cart.getGoods()));
			BasicDBObject bt=new BasicDBObject(map);
			Goods goods=goodsDao.findOne(bt, "goods");
			cartWithGoodsList.add(new CartWithGoods(goods, cart));
		}
		page.setBeanList(cartWithGoodsList);
		return page;
	}
	/**
	 * 添加购物车时候同时处理数量
	 */
	public ReturnMessage<Cart> addToCart(String goodsId, Integer userId, Integer cartNum) {
		ReturnMessage<Cart> returnMessage=new ReturnMessage<Cart>();
		Cart cart=cartMapper.selectCartByGoodsIdAndUserId(goodsId,userId);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("_id",new ObjectId(goodsId));
		BasicDBObject bt=new BasicDBObject(map);
		Goods goods=goodsDao.findOne(bt, "goods");
		
		try {
			if(goods==null) {
				throw new Exception("对不起,商品不存在或者已经下架,请选择其他商品");
			}
			if(cart==null) {
				BigDecimal price=goods.getPrice();
				if(goods.getSale()!=null) {
					price=goods.getSale();
				}
				if(cartNum>goods.getNum()) {
					throw new Exception("对不起,加入购物车数量超出库存,请与商家联系再购买");
				}
				//购买的数量为cartNum
				cart=new Cart(null, userId, goodsId,price,cartNum,
						goods.getCoverimg(), goods.getName(),
						new Date(), null);
				cartMapper.insert(cart);
				returnMessage.setMessage("添加购物车成功");
			}else {
				cart.setNum(cart.getNum()+cartNum);
				if(cart.getNum()+cartNum>goods.getNum()) {
					throw new Exception("对不起,加入购物车数量超出库存,请与商家联系再购买");
				}
				cartMapper.updateCartNumByPrimaryKey(cart);
				returnMessage.setMessage("购物车数量+1");
			}
			returnMessage.setResultCode(0);
			returnMessage.setBean(cart);
			return returnMessage;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
}
