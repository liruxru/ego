package com.ego.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ego.document.Goods;
import com.ego.document.LiuLan;
import com.ego.utils.DBObjectConversionBean;
import com.ego.utils.PageBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class LiuLanDao {
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public PageBean<LiuLan> findAllByUserId(String userId, Integer currentPage, Integer pageSize, String collectionName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		int count = collection.find().count();
		PageBean<LiuLan> page = new PageBean<LiuLan>(currentPage, pageSize, count, null);
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		// query.skip((currentPage - 1) * pageSize);
		// query.limit(pageSize);
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		List<LiuLan> liuLanList = new ArrayList<LiuLan>();
		if (!ls.isEmpty()) {
			LiuLan liuLan = DBObjectConversionBean.dbObjectToBean(ls.get(0), new LiuLan());
			List<Goods> goodListJieShouZhuanHuanJieGuo = new ArrayList<Goods>();
			// 获取没有被转换的 goodlist
			List<DBObject> lss = (List<DBObject>) ls.get(0).get("goodList");
			for (DBObject dbObject : lss) {
				goodListJieShouZhuanHuanJieGuo.add(DBObjectConversionBean.dbObjectToBean(dbObject, new Goods()));
			}
			liuLan.setGoodList(goodListJieShouZhuanHuanJieGuo);
			liuLanList.add(liuLan);
			page.setBeanList(liuLanList);
		}
		return page;
	}

	public boolean insert(LiuLan liulan, String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		List<LiuLan> lsLiuLan = new ArrayList<LiuLan>(10000);
		lsLiuLan.add(liulan);
		try {
			mongoTemplate.insert(lsLiuLan, collectionName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void remove(Map<String, Object> map, String collectionName) {
		BasicDBObject query = new BasicDBObject(map);
		mongoTemplate.remove(query, collectionName);
	}

	public LiuLan findOne(Map<String, Object> map, String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(map.get("userId")));
		LiuLan liuLan = null;
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		if (!ls.isEmpty()) {
			try {
				liuLan = DBObjectConversionBean.dbObjectToBean(ls.get(0), new LiuLan());
				List<Goods> goodListJieShouZhuanHuanJieGuo = new ArrayList<Goods>();
				// 获取没有被转换的 goodlist
				List<DBObject> lss = (List<DBObject>) ls.get(0).get("goodList");
				for (DBObject dbObject : lss) {
					goodListJieShouZhuanHuanJieGuo.add(DBObjectConversionBean.dbObjectToBean(dbObject, new Goods()));
				}
				liuLan.setGoodList(goodListJieShouZhuanHuanJieGuo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return liuLan;
	}

}
