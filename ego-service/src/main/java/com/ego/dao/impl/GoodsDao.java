package com.ego.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ego.dao.MongBase;
import com.ego.utils.DBObjectConversionBean;
import com.ego.utils.PageBean;
import com.ego.utils.SearchGoods;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ego.document.Goods;

public class GoodsDao implements MongBase<Goods> {

	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 单查goods
	 */
	public Goods findOne(Map<String, Object> map, String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(map.get("_id")));
		DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		List<DBObject> ls = cursor.toArray();// 进行转换
		Goods goods = null;
		try {
			if (!ls.isEmpty()) {
				goods = DBObjectConversionBean.dbObjectToBean(ls.get(0), new Goods());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return goods;
	}

	/***
	 * 全查goods
	 */
	public PageBean<Goods> findAll(int pageCode, int pageSize, int lock, String collectionName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// DB db = mongoTemplate.getDb().getSisterDB("ego_mongodb");
		System.out.println(pageCode + "++" + pageSize + "++" + lock);
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		int count = collection.find().count();
		Criteria criteria = new Criteria();
		if (lock != 3) {
			// query.addCriteria(Criteria.where("lock").is("lock"));
			criteria.and("lock").is(lock);
		}
		Query query = new Query(criteria);
		query.skip((pageCode - 1) * pageSize);
		query.limit(pageSize);
		// DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		// List<DBObject> ls = cursor.toArray();// 进行转换
		List<Goods> goodsList = mongoTemplate.find(query, Goods.class);
		// int count=(int) mongoTemplate.count(query, Goods.class);
		// if (!ls.isEmpty()) {
		// for (int i = 0; i < ls.size(); i++) {
		// goodsList.add(DBObjectConversionBean.dbObjectToBean(ls.get(i), new Goods()));
		// }
		// page.setBeanList(goodsList);
		// }
		// System.out.println("分页数量"+goodsList.size());
		PageBean<Goods> page = new PageBean<Goods>(pageCode, pageSize, count, goodsList);
		return page;
	}

	/***
	 * 类型全查goods findAllByTypes
	 */
	public PageBean<Goods> findAllByTypes(String type, int pageCode, int pageSize, int lock, String collectionName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		int count = collection.find().count();
		Criteria criteria = new Criteria();
		// System.out.println(type);
		criteria.and("type").is(type);
		if (lock == 0 || lock == 1) {
			criteria.and("lock").is(lock);
		}
		Query query = new Query(criteria);
		query.skip((pageCode - 1) * pageSize);
		query.limit(pageSize);
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = mongoTemplate.find(query, Goods.class);
		PageBean<Goods> page = new PageBean<Goods>(pageCode, pageSize, count, goodsList);
		return page;
	}

	/***
	 * 店鋪全查goods findAllByStores
	 */
	public PageBean<Goods> findAllByStores(String storename, int pageCode, int pageSize, Integer lock,
			String collectionName) {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		int count = collection.find().count();
		Criteria criteria = new Criteria();
		criteria.and("store").is(storename);
		if (lock == 0 || lock == 1) {
			criteria.and("lock").is(lock);
		}
		Query query = new Query(criteria);
		query.skip((pageCode - 1) * pageSize);
		query.limit(pageSize);
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = mongoTemplate.find(query, Goods.class);
		PageBean<Goods> page = new PageBean<Goods>(pageCode, pageSize, count, goodsList);
		return page;
	}

	/**
	 * 多条件查询 findAllBySearchGoods
	 */
	@SuppressWarnings("unlikely-arg-type")
	public PageBean<Goods> findAllBySearchGoods(SearchGoods searchGoods, String collectionName)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		// int count = collection.find().count();
		Criteria criteria = new Criteria();
		if (searchGoods.getName() != null && !"".equals(searchGoods.getName())) {
			criteria.and("name").is(searchGoods.getName());
		}
		if (searchGoods.getFullName() != null && !"".equals(searchGoods.getFullName())) {
			criteria.orOperator(Criteria.where("fullname").regex(".*?" + searchGoods.getFullName() + ".*"));
//			 query.addCriteria(Criteria.where("fullname").regex(".*?\\" +searchGoods.getFullName().toString() + ".*"));
		}
		if (searchGoods.getTypename() != null && !"".equals(searchGoods.getTypename())) {
			criteria.and("type").is(searchGoods.getTypename());
		}
		if (searchGoods.getStorename() != null && !"".equals(searchGoods.getStorename())) {
			criteria.and("store").is(searchGoods.getStorename());
		}
		if (searchGoods.getMinNum()!=null && !"".equals(searchGoods.getMinNum())) {
			criteria.and("num").lte(searchGoods.getMinNum());
		}
		if (searchGoods.getMinPrice() != null && searchGoods.getMinPrice() != new BigDecimal(0)
				&& searchGoods.getMaxPrice() != null && searchGoods.getMinPrice() != new BigDecimal(0)) {
			criteria.and("price").gte(searchGoods.getMinPrice()).lte(searchGoods.getMaxPrice());
		} else {
			if (searchGoods.getMinPrice() != null && searchGoods.getMinPrice() != new BigDecimal(0)) {
				criteria.and("price").gte(searchGoods.getMinPrice());
			}
			if (searchGoods.getMaxPrice() != null && searchGoods.getMinPrice() != new BigDecimal(0)) {
				criteria.and("price").lte(searchGoods.getMaxPrice());
			}
		}
		if (searchGoods.getLock() != null && !"".equals(searchGoods.getLock()) && searchGoods.getLock() != 3) {
			criteria.and("lock").is(searchGoods.getLock());
		}

		Query query = new Query(criteria);
		// if (searchGoods.getShengXu() != null && !"".equals(searchGoods.getShengXu()))
		// {
		//// query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"price")));
		// query.with(new Sort(Sort.Direction.DESC, "price"));
		//// query.with(new Sort(new Order(Direction.ASC,"price")));
		// }
		// if (searchGoods.getJiangXu() != null && !"".equals(searchGoods.getJiangXu()))
		// {
		// query.with(new Sort(new Order(Sort.Direction.DESC,"price")));
		// }
		List<Goods> goodsList = new ArrayList<Goods>();
		int count = mongoTemplate.find(query, Goods.class).size();
		goodsList = mongoTemplate.find(query, Goods.class);
		if (searchGoods.getShengXu() != null && !"".equals(searchGoods.getShengXu())) {
			Collections.sort(goodsList, new Comparator<Goods>() {
				public int compare(Goods o1, Goods o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
			});
		}
		if (searchGoods.getJiangXu() != null && !"".equals(searchGoods.getJiangXu())) {
			Collections.sort(goodsList, new Comparator<Goods>() {
				public int compare(Goods o1, Goods o2) {
					return o2.getPrice().compareTo(o1.getPrice());
				}
			});
		}
		List<Goods> pageGoodsList = new ArrayList<Goods>();
		int start = (searchGoods.getCurrentPage() - 1) * searchGoods.getPageSize();
		int end = searchGoods.getPageSize();
		for (int i = start; i < goodsList.size() && i < end + start; i++) {
			pageGoodsList.add(goodsList.get(i));
		}

		// query.skip((searchGoods.getCurrentPage() - 1) * searchGoods.getPageSize());
		// query.limit(searchGoods.getPageSize());

		// DBCursor cursor = collection.find();// 结果集
		// int count=cursor.size();
		// cursor.sort(new BasicDBObject("price",-1));
		// DBCursor cursor = collection.find(query.getQueryObject());// 结果集
		// List<DBObject> ls = cursor.toArray();// 进行转换
		// List<Goods> goodsList = new ArrayList<Goods>();
		// PageBean<Goods> page = new PageBean<Goods>(searchGoods.getCurrentPage(),
		// searchGoods.getPageSize(), count,goodsList);
		// if (!ls.isEmpty()) {
		// for (int i = 0; i < ls.size(); i++) {
		// goodsList.add(DBObjectConversionBean.dbObjectToBean(ls.get(i), new Goods()));
		// }
		// page.setBeanList(goodsList);
		// }
		PageBean<Goods> page = new PageBean<Goods>(searchGoods.getCurrentPage(), searchGoods.getPageSize(), count,
				pageGoodsList);
		return page;
	}

	/***
	 * 插入goods
	 */
	public String insert(Goods goods, String collectionName) {
		// DB db = mongoTemplate.getDb().getSisterDB("ego_mongodb");
		DBCollection collection = mongoTemplate.getCollection(collectionName);
		List<Goods> lsGoods = new ArrayList<Goods>(10000);
		lsGoods.add(goods);
		try {
			mongoTemplate.insert(lsGoods, collectionName);
			return lsGoods.get(0).get_id();
		} catch (Exception e) {
			e.printStackTrace();
			return "1";
		}
	}

	public void update(Map<String, Object> params, String collectionName) {

	}

	public void createCollection(String collectionName) {

	}

	public void remove(Map<String, Object> map, String collectionName) {
		BasicDBObject query = new BasicDBObject(map);
		mongoTemplate.remove(query, collectionName);
	}

	public void dropCollection(String collectionName) {
		this.mongoTemplate.dropCollection("lsGoods");
	}

	public Integer count(String collectionName) {
		DBCollection collection = mongoTemplate.getCollection("lsGoods");
		Integer count = collection.find().count();
		return count;
	}

}
