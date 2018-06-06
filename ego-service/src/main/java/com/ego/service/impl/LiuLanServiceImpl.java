package com.ego.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;

import com.ego.dao.impl.LiuLanDao;
import com.ego.document.Goods;
import com.ego.document.LiuLan;
import com.ego.service.LiuLanService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchLiulan;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class LiuLanServiceImpl implements LiuLanService {

	public static final String DB_NAME = "liulan";

	@Resource(name = "liuLanDao")
	private LiuLanDao liuLanDao;

	public LiuLanDao getLiuLanDao() {
		return liuLanDao;
	}

	public void setLiuLanDao(LiuLanDao liuLanDao) {
		this.liuLanDao = liuLanDao;
	}

	public PageBean<LiuLan> selectLiuLanByUserId(SearchLiulan searchLiulan) {
		PageBean<LiuLan> pageBean = new PageBean<LiuLan>();
		try {
			pageBean = liuLanDao.findAllByUserId(searchLiulan.getUserId(), searchLiulan.getCurrentPage(),
					searchLiulan.getPageSize(), DB_NAME);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public ReturnMessage<LiuLan> insertLiulanByUserId(String userId, Goods goods) {
		ReturnMessage<LiuLan> rm = new ReturnMessage<LiuLan>();
		LiuLan liu = null;
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		liu =liuLanDao.findOne(m, DB_NAME);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Goods> lsOld = new ArrayList<Goods>();
		if (liu != null) {
			map.put("_id", new ObjectId(liu.get_id()));
			lsOld = liu.getGoodList();
			Iterator<Goods> ite = lsOld.iterator();
			while (ite.hasNext()) {
				if (ite.next().getName().equals(goods.getName())) {
					ite.remove();
				}
			}
			liuLanDao.remove(map, DB_NAME);
			lsOld.add(goods);
			liu = new LiuLan(liu.get_id(), lsOld, userId, new Date());
			liuLanDao.insert(liu, DB_NAME);
			return rm;
		} else {
			lsOld.add(goods);
			liu = new LiuLan(null, lsOld, userId, new Date());
			liuLanDao.insert(liu, DB_NAME);
			return rm;
		}
	}

	public LiuLan findone(String userid, String collectionName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userid);
		return liuLanDao.findOne(map, DB_NAME);
	}

	public ReturnMessage<LiuLan> deleteLiuLanByUserId(String userId, Goods goods) {
		ReturnMessage<LiuLan> rm = new ReturnMessage<LiuLan>();
		LiuLan liu = new LiuLan();
		liu = findone(userId, DB_NAME);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(liu.get_id()));
		
		List<Goods> lsOld = new ArrayList<Goods>();
		lsOld = liu.getGoodList();
		Iterator<Goods> ite = lsOld.iterator();
		while (ite.hasNext()) {
			if (ite.next().getName().equals(goods.getName())) {
				ite.remove();
			}
		}
		liuLanDao.remove(map, DB_NAME);
		liuLanDao.insert(new LiuLan(liu.get_id(), lsOld, userId, new Date()), DB_NAME);
		return rm;
	}

}
