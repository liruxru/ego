package com.ego.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ego.dao.MongBase;
import com.ego.document.Goods;
import com.ego.document.LiuLan;
import com.ego.document.PingJia;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.utils.DBObjectConversionBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class PingJiaDao implements MongBase<PingJia> {
	private MongoTemplate mongoTemplate;
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public PingJia findOne(Map<String,Object> map, String collectionName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(map.get("_id")));
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		PingJia pingJia = null;
		if (!ls.isEmpty()) {
			pingJia = DBObjectConversionBean.dbObjectToBean(ls.get(0), new PingJia());
		}
		return pingJia;
	}
	public void update(Map<String, Object> params, String collectionName) {
	}
	public void createCollection(String collectionName) {
	}
	public void dropCollection(String collectionName) {
	}
	public Integer count(String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		int count=collection.find().count();// 结果集
		return count;
	}
	
	/***
	 * 插入Pingjia
	 */
	public boolean insert(PingJia pingJia, String collectionName) {
		List<PingJia> lsPingJia = new ArrayList<PingJia>(10000);
		lsPingJia.add(pingJia);
		mongoTemplate.insert(lsPingJia, collectionName);
		return true;
	}
	/**
	 * 删除评价
	 */
	public void remove(Map<String, Object> map, String collectionName) {
		BasicDBObject query=new BasicDBObject(map);
		mongoTemplate.remove(query, collectionName);
	}
	/**
	 * 通过商品统计全部评价
	 * @param goodsId
	 * @param string
	 * @return
	 */
	public int countByGoods(String goodsId, String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("goodsId").is(goodsId));
		int count=collection.find(query.getQueryObject()).count();// 结果集
		return count;
	}
	/**
	 * 通过商品遍历全部评价
	 * @param goodsId
	 * @param offset
	 * @param length
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public List<PingJia> selectPingjiaByGoodsId(String goodsId, int offset, int length) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection("pingjia");
		Query query = new Query();
		query.addCriteria(Criteria.where("goodsId").is(goodsId));
		query.skip(offset);
		query.limit(length);
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		List<PingJia> pingJiaList = new ArrayList<PingJia>();
		if (!ls.isEmpty()) {
			for (int i = 0; i < ls.size(); i++) {
				pingJiaList.add(DBObjectConversionBean.dbObjectToBean(ls.get(i), new PingJia()));
			}
		}
		return pingJiaList;
	}
	public List<PingJia> selectPingjia(int offset, int length) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection("pingjia");
//		Criteria criteria = new Criteria();
//		Query query = new Query(criteria);
	   Query query = new Query();
		query.skip(offset);
		query.limit(length);
//		List<PingJia> pingJiaList = mongoTemplate.find(query, PingJia.class);
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		List<PingJia> pingJiaList = new ArrayList<PingJia>();
		if (!ls.isEmpty()) {
			PingJia pingjia=DBObjectConversionBean.dbObjectToBean(ls.get(0), new PingJia());
			List<PingJia> zhuan=new ArrayList<PingJia>();
			List<Object> lss=(List<Object>) ls.get(0).get("pin_hui");
			for (Object object : lss) {
				
			}
		}
		return pingJiaList;
	}
	public int countWeiHuiFuPingjia(String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		//统计需要回复状态的评价
		query.addCriteria(Criteria.where("code").is(0));
		int count=collection.find(query.getQueryObject()).count();// 结果集
		return count;
	}
	public List<PingJia> selectWeiHuiFuPingjia(int offset, int length) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection("pingjia");
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(0));
		query.skip(offset);
		query.limit(length);
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		List<PingJia> pingJiaList = new ArrayList<PingJia>();
		if (!ls.isEmpty()) {
			for (int i = 0; i < ls.size(); i++) {
				pingJiaList.add(DBObjectConversionBean.dbObjectToBean(ls.get(i), new PingJia()));
			}
		}
		return pingJiaList;
	}
	public PingJia selectPingJiaByOrderItem(Integer orderItemId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection("pingjia");
		Query query = new Query();
		query.addCriteria(Criteria.where("orderItemId").is(orderItemId));
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		PingJia pingJia = null;
		if (!ls.isEmpty()) {
			pingJia = DBObjectConversionBean.dbObjectToBean(ls.get(0), new PingJia());
		}
		return pingJia;
	}
}
