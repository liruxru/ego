package com.ego.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;

import com.ego.dao.impl.GoodsDao;
import com.ego.document.Goods;
import com.ego.mapper.ImgMapper;
import com.ego.mapper.po.Img;
import com.ego.service.GoodsService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.ego.utils.SearchGoods;

public class GoodsServiceImpl implements GoodsService {

	public static final String DB_NAME = "goods";

	@Resource(name = "goodsDao")
	private GoodsDao goodsDao;
	@Resource(name = "imgMapper")
	private ImgMapper imgMapper;

	public ImgMapper getImgMapper() {
		return imgMapper;
	}
	public void setImgMapper(ImgMapper imgMapper) {
		this.imgMapper = imgMapper;
	}
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	public PageBean<Goods> selectGoodsBySearchGoods(SearchGoods searchGoods) {
		PageBean<Goods> pageBean = null;
		try {
			pageBean = goodsDao.findAllBySearchGoods(searchGoods, DB_NAME);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public ReturnMessage<Goods> addGoods(Goods goods, List<String> newImgs, List<String> imgs) {
		ReturnMessage<Goods> rm = new ReturnMessage<Goods>();
		goods.setCreatedate(new Date());
		goods.setLock(0);
		goods.setJinprice(new BigDecimal(goods.getJinprice().toString()).setScale(2, BigDecimal.ROUND_DOWN));
		goods.setPrice(new BigDecimal(goods.getPrice().toString()).setScale(2, BigDecimal.ROUND_DOWN));
		goods.setSale(new BigDecimal(goods.getSale().toString()).setScale(2, BigDecimal.ROUND_DOWN));
		
		String f_id = goodsDao.insert(goods, DB_NAME);
		if (!"1".equals(f_id)) {
			if (newImgs != null || imgs != null) {
				Img img = new Img();
				for (int i = 0; i < newImgs.size(); i++) {
					img = new Img(null, newImgs.get(i), imgs.get(i), goods.get_id(), null);
					this.imgMapper.insertSelective(img);
				}
			}
			rm.setMessage(f_id);
			rm.setResultCode(0);
			return rm;
		} else {
			rm.setResultCode(1);
			rm.setMessage("插入失败");
			rm.setBean(goods);
			return rm;
		}
	}

	public ReturnMessage<Goods> upodateGoodsLockById(String GoodsId, Integer lock) {
		ReturnMessage<Goods> rm = new ReturnMessage<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(GoodsId));
		Goods goods = new Goods();
		goods = goodsDao.findOne(map, DB_NAME);
		Date createdate = goods.getCreatedate();
		goodsDao.remove(map, DB_NAME);
		goods.setLock(lock);
		goods.setModifidate(new Date());
		goods.setCreatedate(createdate);
		goodsDao.insert(goods, DB_NAME);
		rm.setResultCode(0);
		return rm;
	}

	public ReturnMessage<Goods> upodateGoodsLockByIds(String[] goodsIds, Integer lock) {
		ReturnMessage<Goods> rm = new ReturnMessage<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		Goods goods = new Goods();
		for (int i = 0; i < goodsIds.length; i++) {
			goods = findone(goodsIds[i]);
			map.put("_id", new ObjectId(goodsIds[i]));
			goodsDao.remove(map, DB_NAME);
			goods.setLock(lock);
			goods.setModifidate(new Date());
			goodsDao.insert(goods, DB_NAME);
		}
		rm.setResultCode(0);
		return rm;
	}

	public ReturnMessage<Goods> updateGoods(Goods goods) {
		ReturnMessage<Goods> rm = new ReturnMessage<Goods>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(goods.get_id()));
		Goods g = new Goods();
		g = goodsDao.findOne(map, DB_NAME);
		goodsDao.remove(map, DB_NAME);
		goods.set_id(g.get_id());
		if (goods.getCoverimg() == null) {
			goods.setCoverimg(g.getCoverimg());
		}
		addGoods(goods, null, null);
		goods.setCreatedate(g.getCreatedate());
		goods.setModifidate(new Date());
		rm.setResultCode(0);
		return rm;
	}

	public Goods findone(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(id));
		return goodsDao.findOne(map, DB_NAME);
	}

	public PageBean<Goods> findAll(SearchGoods searchGoods) {
		PageBean<Goods> page = new PageBean<Goods>();
		try {
			page = goodsDao.findAll(searchGoods.getCurrentPage(), searchGoods.getPageSize(), searchGoods.getLock(),
					DB_NAME);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return page;
	}

	public PageBean<Goods> findAllByTypes(SearchGoods searchGoods) {
		PageBean<Goods> page = new PageBean<Goods>();
		try {
			page = goodsDao.findAllByTypes(searchGoods.getTypename(), searchGoods.getCurrentPage(),
					searchGoods.getPageSize(), searchGoods.getLock(), DB_NAME);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return page;
	}

	public PageBean<Goods> findAllByStores(SearchGoods searchGoods) {
		PageBean<Goods> page = new PageBean<Goods>();
		page = goodsDao.findAllByStores(searchGoods.getStorename(), searchGoods.getCurrentPage(),
				searchGoods.getPageSize(), searchGoods.getLock(), DB_NAME);
		return page;
	}

	public PageBean<Goods> warringGoods(SearchGoods searchGoods) {
		PageBean<Goods> pageBean = null;
		try {
			pageBean = goodsDao.findAllBySearchGoods(searchGoods, DB_NAME);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return pageBean;
	}
	/**********************************************************************************/
	public List<Img> selectImgsByGoodsId(String id) {
		return imgMapper.selectImgsByGoodsId(id);
	}

}
