package com.ego.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ego.dao.MongBase;
import com.ego.document.CollectGoods;
import com.ego.document.GoodsCollect;
import com.ego.utils.DBObjectConversionBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import net.sf.json.JSONArray;

public class CollectGoodsDao implements MongBase<CollectGoods> {
	private MongoTemplate mongoTemplate;
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public void update(Map<String, Object> params, String collectionName) {
	}

	public void createCollection(String collectionName) {
	}

	public void remove(Map<String, Object> params, String collectionName) {
		BasicDBObject query=new BasicDBObject(params);
		mongoTemplate.remove(query, collectionName);
	}

	public void dropCollection(String collectionName) {
	}

	public Integer count(String collectionName) {
		return null;
	}

	public CollectGoods findOne(Map<String, Integer> map, String collectionName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("user").is(map.get("userId")));
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		CollectGoods collectGoods = null;
		List<String> goodsIds=new ArrayList<String>();
		if (!ls.isEmpty()) {
			collectGoods = DBObjectConversionBean.dbObjectToBean(ls.get(0), new CollectGoods());
			Object object = ls.get(0).get("goodsIds");
			JSONArray jsonArray=JSONArray.fromObject(object);
			Object[] os=jsonArray.toArray();
			for (Object object2 : os) {
				goodsIds.add(object2.toString());
			}
			collectGoods.setGoodsIds(goodsIds);
		}
		return collectGoods;
	}
	
	public void removeByUser(Map<String, Object> map, String collectionName) {
		BasicDBObject query=new BasicDBObject(map);
		mongoTemplate.remove(query, collectionName);
		
	}
	public void insert(CollectGoods collectGoods, String collectionName) throws Exception {
		List<CollectGoods> lsCollectGoods = new ArrayList<CollectGoods>(10000);
		lsCollectGoods.add(collectGoods);
		mongoTemplate.insert(lsCollectGoods, collectionName);
	}
	/**
	 * 这个查询以商品为猪脚 查询收藏该商品的用户
	 * @param goodsMap
	 * @param string
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public GoodsCollect findOneByGoods(Map<String, String> goodsMap, String collectionName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("goodsId").is(goodsMap.get("goodsId")));
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		GoodsCollect goodsCollect = null;
		List<Integer> userIds=new ArrayList<Integer>();
		if (!ls.isEmpty()) {
			goodsCollect = DBObjectConversionBean.dbObjectToBean(ls.get(0), new GoodsCollect());
			Object object = ls.get(0).get("userIds");
			JSONArray jsonArray=JSONArray.fromObject(object);
			Object[] os=jsonArray.toArray();
			for (Object object2 : os) {
				userIds.add(Integer.valueOf(object2.toString()));
			}
			goodsCollect.setUserIds(userIds);
		}
		return goodsCollect;
	}
	/**
	 * 这个方法插入 goodsCollect集合  以商品为猪脚 查询收藏该商品的用户
	 * @param goodsCollect
	 * @param collectionName
	 */
	public boolean insert(GoodsCollect goodsCollect, String collectionName){
		List<GoodsCollect> lsCollectGoods = new ArrayList<GoodsCollect>(10000);
		lsCollectGoods.add(goodsCollect);
		mongoTemplate.insert(lsCollectGoods, collectionName);
		return true;
	}
	/**
	 * 这个方法移除  goodsCollect集合  在重新插入 更新
	 * @param goodsMap
	 * @param string
	 */
	public void removeByGoods(Map<String, Object> map, String collectionName) {
		BasicDBObject query=new BasicDBObject(map);
		mongoTemplate.remove(query, collectionName);
	}
	/**
	 * 这个方法查看每个商品被收藏的数量从多到少排序
	 * @param offset
	 * @param length
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<GoodsCollect> selectGoodsCollect(int offset, int length) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection("goodscollect");
		DBCursor cursor = collection.find().sort(new BasicDBObject("countPeopleNum",-1)).skip(offset).limit(length);// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		List<GoodsCollect> goodsCollectList = new ArrayList<GoodsCollect>();
		if (!ls.isEmpty()) {
			for (int i = 0; i < ls.size(); i++) {
				goodsCollectList.add(DBObjectConversionBean.dbObjectToBean(ls.get(i), new GoodsCollect()));
			}
		}
		return goodsCollectList;
	}
}
