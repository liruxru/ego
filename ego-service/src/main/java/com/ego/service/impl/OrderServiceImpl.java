package com.ego.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.dao.impl.GoodsDao;
import com.ego.document.Goods;
import com.ego.mapper.CartMapper;
import com.ego.mapper.OrderItemsMapper;
import com.ego.mapper.OrderMapper;
import com.ego.mapper.StoreMapper;
import com.ego.mapper.po.Cart;
import com.ego.mapper.po.Order;
import com.ego.mapper.po.OrderItems;
import com.ego.mapper.po.OrderItemsWithGoods;
import com.ego.mapper.po.relationPo.CartWithGoods;
import com.ego.mapper.po.relationPo.OrderWithOrderItem;
import com.ego.mapper.po.relationPo.StoreCart;
import com.ego.service.OrderService;
import com.ego.utils.PageBean;
import com.ego.utils.ResultUtils;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SnUtils;
import com.mongodb.BasicDBObject;

public class OrderServiceImpl implements OrderService{
	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	@Resource(name="cartMapper")
	private CartMapper cartMapper;
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	@Resource(name="orderItemsMapper")
	private OrderItemsMapper  orderItemsMapper;
	public ReturnMessage<Order> addToOrderByCartIdsAndUserId(Integer userId, Integer[] cartIds) {
		ReturnMessage<Order> returnMessage=new ReturnMessage<Order>();
		try {
			//1.计算总价，生成订单，生成订订单条目，计算总价，维护库存,清空购物车
			List<Cart> cartList=cartMapper.selectByCartIds(cartIds);
			BigDecimal sum=new BigDecimal("0.00");
			for (Cart cart : cartList) {
				sum=sum.add(cart.getPrice().multiply(BigDecimal.valueOf(cart.getNum())));
			}
			if(sum.doubleValue()==0) {
				throw new  Exception("购物车不存在");
			}
			Order order=new Order(null, SnUtils.getOrderSn(), userId, sum, null,
					null, null, 1,
					null, new Date());
			//这个方法需要修改为返回订单id
			orderMapper.insert(order);
			Integer orderId=order.getId();
			OrderItems orderItem=null;
			List<OrderItems> orderItemList=new ArrayList<OrderItems>();
			//生成订单条目
			for (Cart cart : cartList) {
				orderItem=new OrderItems(null, cart.getGoods(), cart.getNum(), cart.getPrice(), orderId);
				orderItemList.add(orderItem);
				cartMapper.deleteByPrimaryKey(cart.getId());
				orderItemsMapper.insert(orderItem);
				//维护库存
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id", new ObjectId(cart.getGoods()));
				BasicDBObject BasicDBObject=new BasicDBObject(map);
				Goods goods=goodsDao.findOne(BasicDBObject, "goods");
				if(goods.getNum()==null||goods.getNum()<cart.getNum()) {
					throw new  Exception("库存不足");
				}
				goods.setNum(goods.getNum()-cart.getNum());
				try {
					//执行维护库存的操作
					goodsDao.remove(map, "goods");
					goodsDao.insert(goods, "goods");
				} catch (Exception e) {
					throw new  Exception("维护库存失败："+e.getMessage());
				}
			}
			returnMessage.setResultCode(0);
			returnMessage.setBean(order);
			return returnMessage;
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return returnMessage;
		}
		
		
	}
	public PageBean<Order> selectOrderByUserId(Integer userId, int pageCode, int pageSize) {
		 //查看全部订单 
		PageBean<Order> pageBean=new PageBean<Order>(pageCode, pageSize);
		int count =orderMapper.countOrderByUserId(userId);
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Order> orderList= orderMapper.selectOrdersByUserId(userId,offset,length);
		pageBean.setBeanList(orderList);
		return pageBean;
	}
	public ReturnMessage<Order> updateOrderStatus(Integer orderId, Integer status) {
		ReturnMessage<Order> returnMessage=new ReturnMessage<Order>();
		try {
			Order order=orderMapper.selectByPrimaryKey(orderId);
			if(status==10) {
				if(order.getStatus()!=1) {
					throw new Exception("已经支付订单无法取消");
				}
			}
			orderMapper.updateOrderStatus(orderId,status);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("修改订单状态成功");
			return returnMessage;
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("修改订单状态失败:"+e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return returnMessage;
		}
		
	}
	public ReturnMessage<Order> deleteOrderByOrderId(Integer orderId) {
		ReturnMessage<Order> returnMessage=new ReturnMessage<Order>();
		
		try {
			Order order = orderMapper.selectByPrimaryKey(orderId);
			if(order.getStatus()!=10) {
				if(order.getStatus()!=1) {
					if(order.getStatus()!=11) {
						throw new Exception("订单无法删除");
					}
				}
			}
			orderItemsMapper.deleteByOrderId(orderId);
			orderMapper.deleteByPrimaryKey(orderId);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("删除订单成功");
			return returnMessage;
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("删除订单失败"+e.getMessage());
			return returnMessage;
		}
		
	}
	public PageBean<Order> selectOrderByUserIdAndStatus(Integer userId, Integer status, int pageCode, int pageSize) {
		int count=orderMapper.countOrdersByUserIdAndStatus(userId, status);
		PageBean<Order> pageBean=new PageBean<Order>(pageCode, pageSize,count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Order> orderList=orderMapper.selectOrdersByUserIdAndStatus(userId, offset, length,status);
		pageBean.setBeanList(orderList);
		return pageBean;
	}
	public PageBean<Order> selectOrderByStatus(Integer status, int pageCode, int pageSize) {
		int count=orderMapper.countOrdersByStatus(status);
		PageBean<Order> pageBean=new PageBean<Order>(pageCode, pageSize,count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Order> orderList=orderMapper.selectOrdersByStatus(offset, length,status);
		pageBean.setBeanList(orderList);
		return pageBean;
	}
	public PageBean<Order> selectOrderByUserIdAndDate(Integer userId, int pageCode, int pageSize,
			Date Begin, Date end) {
		int count=orderMapper.countOrdersByDate(userId,Begin,end);
		PageBean<Order> pageBean=new PageBean<Order>(pageCode, pageSize,count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Order> orderList=orderMapper.selectOrdersByDate(userId,Begin,end,offset, length);
		pageBean.setBeanList(orderList);
		return pageBean;
	}
	
	
	public PageBean<OrderWithOrderItem> selectOrderWithOrderItemByStatus(Integer status, int pageCode, int pageSize) {
		int count=orderMapper.countOrdersByStatus(status);
		PageBean<OrderWithOrderItem> pageBean=new PageBean<OrderWithOrderItem>(pageCode, pageSize,count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<OrderWithOrderItem> orderWithOrderItemList= orderMapper.selectOrderWithOrderItemByStatus(status,offset,length);
		orderWithOrderItemList=getOrderWithOrderItemList(orderWithOrderItemList);
		pageBean.setBeanList(orderWithOrderItemList);
		return pageBean;
	}
	
	/**
	 * 通过用户id和订单状态查询全部信息
	 * @param userId
	 * @param status
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	public PageBean<OrderWithOrderItem> selectOrderWithOrderItemByUserIdAndStatus(Integer userId, Integer status, int pageCode, int pageSize) {
		int count=orderMapper.countOrdersByUserIdAndStatus(userId, status);
		PageBean<OrderWithOrderItem> pageBean=new PageBean<OrderWithOrderItem>(pageCode, pageSize,count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<OrderWithOrderItem> orderWithOrderItemList= orderMapper.selectOrderWithOrderItemByUserIdAndStatus(userId,status,offset,length);
		getOrderWithOrderItemList(orderWithOrderItemList);
		pageBean.setBeanList(orderWithOrderItemList);
		return pageBean;
	}
	/**
	 * 处理订单获取订单详情和详情中的商品信息
	 * @param orderWithOrderItemList
	 * @return
	 */
	private List<OrderWithOrderItem> getOrderWithOrderItemList(List<OrderWithOrderItem> orderWithOrderItemList) {
		for (OrderWithOrderItem orderWithOrderItem : orderWithOrderItemList) {
			List<OrderItemsWithGoods> orderItemsWithGoodsList=orderItemsMapper.
					selectOrderItemsWithGoodsByOrderId(orderWithOrderItem.getOrderId(), 0, 100);
			Map<String,Object> map=new HashMap<String,Object>();
			for (OrderItemsWithGoods orderItemsWithGoods : orderItemsWithGoodsList) {
				//去document查找信息
				map.clear();
				map.put("_id",new ObjectId(orderItemsWithGoods.getGoods()));
				BasicDBObject BasicDBObject=new BasicDBObject(map);
				Goods goods=goodsDao.findOne(BasicDBObject, "goods");
				orderItemsWithGoods.setGoodsAll(goods);
				
			}
			orderWithOrderItem.setOrderItemsWithGoodsList(orderItemsWithGoodsList);
			orderWithOrderItem.setStore(storeMapper.selectByPrimaryKey(orderWithOrderItem.getStoresId()));
		}
		return orderWithOrderItemList;
	}
	@Resource(name="storeMapper")
	private StoreMapper storeMapper;
	public PageBean<OrderWithOrderItem> selectOrderWithOrderItemByUser(Integer userId, int pageCode, int pageSize) {
		int count =orderMapper.countOrderByUserId(userId);
		PageBean<OrderWithOrderItem> pageBean=new PageBean<OrderWithOrderItem>(pageCode, pageSize, count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<OrderWithOrderItem> OrderWithOrderItemList= orderMapper.selectOrderWithOrderItemByUserId(userId,offset,length);
		getOrderWithOrderItemList(OrderWithOrderItemList);
		
		pageBean.setBeanList(OrderWithOrderItemList);
		return pageBean;
	}
	public OrderWithOrderItem selectOrderWithOrderItemById(Integer orderId) {
		OrderWithOrderItem orderWithOrderItem=orderMapper.selectOrderWithOrderItemByorderId(orderId);
		List<OrderItemsWithGoods> orderItemsWithGoodsList=orderItemsMapper.
				selectOrderItemsWithGoodsByOrderId(orderWithOrderItem.getOrderId(), 1, 100);
		Map<String,Object> map=new HashMap<String,Object>();
		for (OrderItemsWithGoods orderItemsWithGoods : orderItemsWithGoodsList) {
			//去document查找信息
			map.clear();
			map.put("_id",new ObjectId(orderItemsWithGoods.getGoods()));
			BasicDBObject BasicDBObject=new BasicDBObject(map);
			Goods goods=goodsDao.findOne(BasicDBObject, "goods");
			if(goods==null) {
				goods=new Goods();
				goods.setDescription("商品或许已经下架");
			}
			orderItemsWithGoods.setGoodsAll(goods);
		}
		orderWithOrderItem.setOrderItemsWithGoodsList(orderItemsWithGoodsList);
		orderWithOrderItem.setStore(storeMapper.selectByPrimaryKey(orderWithOrderItem.getStoresId()));
		return orderWithOrderItem;
	}
	
	
	/**
	 * 通过店铺，为每个店铺生成一份订单
	 * @param userId
	 * @param cartIds
	 * @return
	 */
	public ReturnMessage<Order> addToOrderByCartIdsAndUserIdWithStore(Integer userId, Integer[] cartIds) {
		List<Order> orderList=new ArrayList<Order>();
		//1.计算总价，生成订单，生成订订单条目，计算总价，维护库存,清空购物车
		List<Cart> cartList=cartMapper.selectByCartIds(cartIds);
		List<StoreCart> storeCartList = convertCart(cartList);
		try {
			for (StoreCart storeCart : storeCartList) {
				Order order=creatOrderWithStore(storeCart,userId);
				orderList.add(order);
			}
			return ResultUtils.success(orderList);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	/**
	 * 给传来的店铺的全部购物车创建订单
	 * @param storeCart
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Order creatOrderWithStore(StoreCart storeCart,Integer userId) throws Exception {
		BigDecimal sum=new BigDecimal("0.00");
		List<CartWithGoods> cartWihtGoodsList = storeCart.getCartWihtGoodsList();
		for (CartWithGoods cartWithGoods : cartWihtGoodsList) {
			sum=sum.add(cartWithGoods.getCart().getPrice()
					.multiply(BigDecimal.valueOf(cartWithGoods.getCart().getNum())));
		}
		if(sum.doubleValue()==0) {
			throw new  Exception("购物车不存在");
		}
		Order order=new Order(null, SnUtils.getOrderSn(), userId, sum, storeCart.getStoreId(),
				null, null, 1,
				null, new Date());
		//这个方法需要修改为返回订单id
		orderMapper.insert(order);
		Integer orderId=order.getId();
		OrderItems orderItem=null;
		List<OrderItems> orderItemList=new ArrayList<OrderItems>();
		//生成订单条目
		for (CartWithGoods cartWithGoods : cartWihtGoodsList) {
			orderItem=new OrderItems(null, cartWithGoods.getCart().getGoods(), 
					cartWithGoods.getCart().getNum(), cartWithGoods.getCart().getPrice(), orderId);
			orderItemList.add(orderItem);
			//清空购物车
			cartMapper.deleteByPrimaryKey(cartWithGoods.getCart().getId());
			orderItemsMapper.insert(orderItem);
			//维护库存
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("_id", new ObjectId(cartWithGoods.getCart().getGoods()));
			BasicDBObject BasicDBObject=new BasicDBObject(map);
			Goods goods=goodsDao.findOne(BasicDBObject, "goods");
			if(goods.getNum()==null||goods.getNum()<cartWithGoods.getCart().getNum()) {
				throw new  Exception("库存不足");
			}
			goods.setNum(goods.getNum()-cartWithGoods.getCart().getNum());
			try {
				//执行维护库存的操作
				goodsDao.remove(map, "goods");
				goodsDao.insert(goods, "goods");
			} catch (Exception e) {
				throw new  Exception("维护库存失败："+e.getMessage());
			}
		}
		return order;
		
	
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
			CartWithGoods cartWithGoods=new CartWithGoods();
			cartWithGoods.setCart(cart);
			cartWithGoods.setGoods(goods);
			cartWithGoodsList.add(cartWithGoods);
			Integer storeId=storeMapper.selectStoreIdByName(goods.getStore());
			if(storeId==null) {
				storeId=new Random().nextInt(100);
			}
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
	/*public Boolean updateOrderStatusWithOrderItems(String id,Integer status) {
		Integer orderId=Integer.parseInt(id);
		this.orderMapper.updateOrderStatus(orderId, status);
		List<OrderItemsWithGoods> listOrderItems=this.orderItemsMapper.selectOrderItemsWithGoodsByOrderId(orderId, 1, 30);
		for (OrderItemsWithGoods orderItemsWithGoods : listOrderItems) {
			this.orderItemsMapper.updateOrderItemsStatus(orderItemsWithGoods.getId(),status);
		}
		
		return null;
	}*/
	
	
}
