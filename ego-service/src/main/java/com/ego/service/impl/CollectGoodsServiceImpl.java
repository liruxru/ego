package com.ego.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.dao.impl.CollectGoodsDao;
import com.ego.dao.impl.GoodsDao;
import com.ego.document.CollectGoods;
import com.ego.document.Goods;
import com.ego.document.GoodsCollect;
import com.ego.document.po.GoodCollectWithGoods;
import com.ego.service.CollectGoodsService;
import com.ego.utils.PageBean;
import com.ego.utils.ResultUtils;
import com.ego.utils.ReturnMessage;
import com.mongodb.BasicDBObject;

public class CollectGoodsServiceImpl implements CollectGoodsService{
	public static final String DB_USER_COLLECT="goodscollect";
	public static final String DB_GOODS_COLLECT="collectgoods";
	public static final String ADD="add";
	public static final String REMOVE="remove";
	
	@Resource(name="collectGoodsDao")
	private CollectGoodsDao collectGoodsDao;
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	
	/**
	 * 用户添加商品收藏   用户表加一个商品   商品表加一个用户  同时 数量+1
	 */
	public ReturnMessage<CollectGoods> addGoodsToCollect(Integer userId, String goodsId) {
		try {
			updateCollectGoods(goodsId, userId,ADD);
			updateGoodsCollect(userId, goodsId,ADD);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	
	/**
	 * 用户移除商品收藏   用户减少一个商品   商品表减少这个用户  同时 数量-1
	 * 
	 */
	public ReturnMessage<CollectGoods> deleteGoodsFromCollect(Integer userId, String goodsId) {
		try {
			updateCollectGoods(goodsId,userId,REMOVE);
			updateGoodsCollect(userId, goodsId,REMOVE);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	
	public PageBean<Goods> selectGoodsFromCollectByUserId(Integer userId, int pageCode, int pageSize) {
		 PageBean<Goods> page=new PageBean<Goods>(pageCode, pageSize);
		try {
			CollectGoods collectGoods = getUsercollectGoods(userId);
			if(collectGoods!=null) {
				List<String> goodsIds = collectGoods.getGoodsIds();
				//使用数组的方法分页
				int count=goodsIds.size();
				page.setCount(count);
				int offset=page.getPageStart();
				int length=page.getPageEnd();
				List<Goods> goodsList=new ArrayList<Goods>();
				for (int i = offset;i<goodsIds.size()&&i < offset+length; i++) {
					Map<String,Object> goodsMap=new HashMap<String,Object>();
					goodsMap.put("_id", new ObjectId(goodsIds.get(i)));
					goodsList.add(goodsDao.findOne(goodsMap, "goods"));
				}
				page.setBeanList(goodsList);
			}else {
				//如果为空，给一个空的集合
				List<Goods> goodsList=new ArrayList<Goods>();
				page.setBeanList(goodsList);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * 统计这个商品被收藏的数量
	 */
	public Long countUserByCollectGoodsId(String  goodsId) {
		Map<String,String> goodsMap=new HashMap<String,String>();
		goodsMap.put("goodsId", goodsId);
		try {
			GoodsCollect goodsCollect=collectGoodsDao.findOneByGoods(goodsMap, DB_USER_COLLECT);
			if(goodsCollect!=null) {
				List<Integer> userIds = goodsCollect.getUserIds();
				return (long) userIds.size();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}
	/**
	 * 查看所有商品被收藏的数量，进行排序
	 */
	public PageBean<GoodCollectWithGoods> selectGoodsCollect(int pageCode,int pageSize) {
		int count=goodsDao.count("goods");
		PageBean<GoodCollectWithGoods> page=new PageBean<GoodCollectWithGoods>(pageCode, pageSize, count);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		try {
			List<GoodsCollect> goodsCollectList = collectGoodsDao.selectGoodsCollect(offset,length);
			List<GoodCollectWithGoods> goodCollectWithGoodsList=new ArrayList<GoodCollectWithGoods>();
			for (GoodsCollect goodsCollect : goodsCollectList) {
				GoodCollectWithGoods goodCollectWithGoods=new GoodCollectWithGoods();
				BeanUtils.copyProperties(goodsCollect, goodCollectWithGoods);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id", new ObjectId(goodsCollect.getGoodsId()));
				BasicDBObject BasicDBObject=new BasicDBObject(map);
				goodCollectWithGoods.setGoods(goodsDao.findOne(BasicDBObject, "goods"));
				goodCollectWithGoodsList.add(goodCollectWithGoods);
			}
			page.setBeanList(goodCollectWithGoodsList);
		}catch (Exception e) {
			page.setBeanList(new ArrayList<GoodCollectWithGoods>());
			page.setCount(0);
			e.printStackTrace();
		}
		return page;
	}
	
	
	
	
	
	
	
	/**
	 * 更新商品收藏的集合   添加移除里边的用户  改变收藏的数量
	 * @param userId
	 * @param goodsId
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void updateGoodsCollect(Integer userId, String goodsId,String tag)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String,String> goodsMap=new HashMap<String,String>();
		goodsMap.put("goodsId", goodsId);
		GoodsCollect goodsCollect=collectGoodsDao.findOneByGoods(goodsMap, DB_USER_COLLECT);
		if(goodsCollect==null) {
			List<Integer> userIds=new ArrayList<Integer>();
			userIds.add(userId);
			//新建这个集合，默认初始收藏量为1
			goodsCollect=new GoodsCollect(null, goodsId, userIds, 1L);
			collectGoodsDao.insert(goodsCollect, DB_USER_COLLECT);
		}
		if(goodsCollect!=null) {
			//修改这个集合
			List<Integer> userIds = goodsCollect.getUserIds();
			if(REMOVE.equals(tag)) {
				userIds.remove(userId);
				goodsCollect.setCountPeopleNum(goodsCollect.getCountPeopleNum()-1);
			}else if(ADD.equals(tag)) {
				userIds.add(userId);
				goodsCollect.setCountPeopleNum(goodsCollect.getCountPeopleNum()+1);
			}
			goodsCollect.setUserIds(userIds);
			Map<String,Object> goodsCollectMap=new HashMap<String,Object>();
			goodsCollectMap.put("_id", new ObjectId(goodsCollect.get_id()));
			collectGoodsDao.removeByGoods(goodsCollectMap, DB_USER_COLLECT);
			collectGoodsDao.insert(goodsCollect, DB_USER_COLLECT);
		}
	}
	/**
	 * 获取用户的集合
	 * @param userId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private CollectGoods getUsercollectGoods(Integer userId)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("userId",userId);
		CollectGoods collectGoods=collectGoodsDao.findOne(map, DB_GOODS_COLLECT);
		return collectGoods;
	}

	/**
	 * 更新用户收藏集合
	 * @param goodsId
	 * @param collectGoods
	 * @throws Exception
	 */
	private void updateCollectGoods(String gid,Integer userId,String tag) throws Exception {
		CollectGoods collectGoods =new CollectGoods();
		collectGoods=getUsercollectGoods(userId);
		
		if(REMOVE.equals(tag)) {
			if(collectGoods==null) {
				throw new Exception("该用户没有收藏的商品");
			}else {
				List<String> goodsIds=new ArrayList<String>();
				goodsIds= collectGoods.getGoodsIds();
				Iterator<String> ite=goodsIds.iterator();
				while(ite.hasNext()) {
					if(ite.next().equals(gid)) {
						ite.remove();
					}
				}
				collectGoods.setGoodsIds(goodsIds);
				Map<String,Object> collectGoodsMap=new HashMap<String,Object>();
				collectGoodsMap.put("_id", new ObjectId(collectGoods.get_id()));
				collectGoodsDao.remove(collectGoodsMap, DB_GOODS_COLLECT);
				collectGoods.set_id(collectGoods.get_id());
				collectGoodsDao.insert(collectGoods, DB_GOODS_COLLECT);
			}
		}else if(ADD.equals(tag)) {
			if (collectGoods!=null) {
				List<String> gs=new ArrayList<String>();
				gs = collectGoods.getGoodsIds();
				for (String id : gs) {
					if(gid.equals(id)) {
						throw new Exception("已经收藏请不要重复收藏");
					}
				}
				gs.add(gid);
				CollectGoods co=new CollectGoods(collectGoods.get_id(), userId, gs);
				Map<String,Object> collectGoodsMap=new HashMap<String,Object>();
				collectGoodsMap.put("_id", new ObjectId(collectGoods.get_id()));
				collectGoodsDao.remove(collectGoodsMap, DB_GOODS_COLLECT);
				collectGoodsDao.insert(co, DB_GOODS_COLLECT);
			}else {
				List<String> goodsIds=new ArrayList<String>();
				goodsIds.add(gid);
				collectGoods=new CollectGoods(null, userId, goodsIds);
				collectGoodsDao.insert(collectGoods, DB_GOODS_COLLECT);
			}
		}
	}
	
}
