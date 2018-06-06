package com.ego.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.types.ObjectId;

import com.ego.dao.impl.PingJiaDao;
import com.ego.document.Description;
import com.ego.document.PingJia;
import com.ego.document.po.PingJiaWithDescription;
import com.ego.mapper.OrderItemsMapper;
import com.ego.service.PingJiaService;
import com.ego.utils.DescriptionConvertUtil;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;
import com.mongodb.BasicDBObject;
public class PingJiaServiceImpl implements PingJiaService {
	@Resource(name="orderItemsMapper")
	private  OrderItemsMapper orderItemsMapper;
	public OrderItemsMapper getOrderItemsMapper() {
		return orderItemsMapper;
	}
	public void setOrderItemsMapper(OrderItemsMapper orderItemsMapper) {
		this.orderItemsMapper = orderItemsMapper;
	}
	@Resource(name="pingJiaDao")
	private PingJiaDao pinJiaDao;
	public PingJiaDao getPinJiaDao() {
		return pinJiaDao;
	}
	public void setPinJiaDao(PingJiaDao pinJiaDao) {
		this.pinJiaDao = pinJiaDao;
	}
	public ReturnMessage<PingJia> addPingJia(Integer userId, Integer orderItemId, String description) {
		 ReturnMessage<PingJia> returnMessage=new ReturnMessage<PingJia>();
		try {
			//0表示可以回复
			PingJia pingJia=new PingJia(null, orderItemId, userId, null, new Date(),0, null);
			String goodsId=orderItemsMapper.selectByPrimaryKey(orderItemId).getGoods();
			pingJia.setGoodsId(goodsId);
			//1代表第一次评价  -1 表示回复1
			Description descrip=new Description("1", description, new Date());
			LinkedHashMap<String, String> pin_hui=new LinkedHashMap<String, String>();
			pin_hui.put(descrip.toString(), null);
			pingJia.setPin_hui(pin_hui);
			pinJiaDao.insert(pingJia, "pingjia");
			returnMessage.setMessage("评价成功");
			returnMessage.setResultCode(0);
			return returnMessage;
		} catch (Exception e) {
			returnMessage.setMessage("评价失败"+e.getMessage());
			returnMessage.setResultCode(1);
			return returnMessage;
		}
		
	}


	public ReturnMessage<PingJia> addZhuiPing(String pingJiaId, String description) {
		 ReturnMessage<PingJia> returnMessage=new ReturnMessage<PingJia>();
			try {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id", new ObjectId(pingJiaId));
				BasicDBObject BasicDBObject=new BasicDBObject(map);
				PingJia pingJia=pinJiaDao.findOne(BasicDBObject, "pingjia");
				if(pingJia.getPin_hui().size()>=2) {
					throw new Exception("已经评价两次，无法添加追评");
				}
				pingJia.setCode(0);//将状态设置为可回复
				//2代表第2次评价  -2 表示回复2
				Description descrip=new Description("2", description, new Date());
				pingJia.getPin_hui().put(descrip.toString(), null);
				pinJiaDao.remove(map, "pingjia");
				pinJiaDao.insert(pingJia, "pingjia");
				returnMessage.setMessage("追加评价成功");
				returnMessage.setResultCode(0);
				return returnMessage;
			} catch (Exception e) {
				returnMessage.setMessage("追加评价失败"+e.getMessage());
				returnMessage.setResultCode(1);
				return returnMessage;
			}
			
	}
	
	public ReturnMessage<PingJia> PingJiaHuiFu(String pingJiaId, String description, String descriptionId) {
		 ReturnMessage<PingJia> returnMessage=new ReturnMessage<PingJia>();
			try {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id", new ObjectId(pingJiaId));
				PingJia pingJia=pinJiaDao.findOne(map, "pingjia");
				if(pingJia==null) {
					throw new Exception("要回复的评价不存在");
				}
				LinkedHashMap<String, String> pin_hui = pingJia.getPin_hui();
				Set<Entry<String, String>> entrySet = pin_hui.entrySet();
				Description descrip=new Description("-"+descriptionId, description, new Date());
				for (Entry<String, String> entry : entrySet) {
					if(entry.getKey().substring(0,entry.getKey().indexOf(",")).equals("id="+descriptionId)) {
						if(entry.getValue()!=null) {
							throw new Exception("评价已经回复，不要重复回复");
						}
						entry.setValue(descrip.toString());
					}
				} 
				pingJia.setCode(1);//将状态设置为已回复
				pinJiaDao.remove(map, "pingjia");
				pinJiaDao.insert(pingJia, "pingjia");
				returnMessage.setMessage("回复评价成功");
				returnMessage.setResultCode(0);
				return returnMessage;
			} catch (Exception e) {
				returnMessage.setMessage("回复评价失败"+e.getMessage());
				returnMessage.setResultCode(1);
				return returnMessage;
			}
			
	}
	
	public PageBean<PingJiaWithDescription> selectPingjia(int pageCode,int pageSize) {
		int count=pinJiaDao.count("pingjia");
		PageBean<PingJiaWithDescription> page=new PageBean<PingJiaWithDescription>(pageCode, pageSize, count);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		List<PingJia> pingJiaList;
		try {
			pingJiaList = pinJiaDao.selectPingjia(offset,length);
			//将数据进行转换，把String类型的评价和回复封装成实体类
			page.setBeanList(DescriptionConvertUtil.convert(pingJiaList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public PageBean<PingJiaWithDescription> selectPingjiaByGoodsId(String goodsId,int pageCode,int pageSize) {
		int count=pinJiaDao.countByGoods(goodsId,"pingjia");
		PageBean<PingJiaWithDescription> page=new PageBean<PingJiaWithDescription>(pageCode, pageSize, count);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		List<PingJia> pingJiaList;
		try {
			pingJiaList = pinJiaDao.selectPingjiaByGoodsId(goodsId,offset,length);
			//将数据进行转换，把String类型的评价和回复封装成实体类
			page.setBeanList(DescriptionConvertUtil.convert(pingJiaList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public PageBean<PingJiaWithDescription>  selectWeiHuiFuPingjia(int pageCode,int pageSize) {
		int count=pinJiaDao.countWeiHuiFuPingjia("pingJia");
		PageBean<PingJiaWithDescription> page=new PageBean<PingJiaWithDescription>(pageCode, pageSize, count);
		//PageBean<PingJia> page=new PageBean<PingJia>(pageCode, pageSize, count);
		int offset=page.getPageStart();
		int length=page.getPageEnd();
		List<PingJia> pingJiaList;
		try {
			pingJiaList = pinJiaDao.selectWeiHuiFuPingjia(offset,length);
			page.setBeanList(DescriptionConvertUtil.convert(pingJiaList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	public ReturnMessage<PingJia> updatePingjiaToHuiFu(String pingJiaId) {
		 ReturnMessage<PingJia> returnMessage=new ReturnMessage<PingJia>();
			try {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("_id", new ObjectId(pingJiaId));
				BasicDBObject BasicDBObject=new BasicDBObject(map);
				PingJia pingJia=pinJiaDao.findOne(BasicDBObject, "pingjia");
				pingJia.setCode(1);//将状态设置为已经回复//意思是不回复直接修改状态
				pinJiaDao.remove(map, "pingjia");
				pinJiaDao.insert(pingJia, "pingjia");
				returnMessage.setMessage("设置已经回复成功");
				returnMessage.setResultCode(0);
				return returnMessage;
			} catch (Exception e) {
				returnMessage.setMessage("设置已经回复失败:"+e.getMessage());
				returnMessage.setResultCode(1);
				return returnMessage;
			}
			
	}
	/**
	 * 通过订单条目id查看评价
	 */
	public PingJiaWithDescription selectPingJiaByOrderItem(Integer orderItemId) {
		 try {
			PingJia pingJia = pinJiaDao.selectPingJiaByOrderItem(orderItemId);
			if(pingJia==null) {
				return null;
			}
			List<PingJia> pingJiaList=new ArrayList<PingJia>();
			pingJiaList.add(pingJia);
			List<PingJiaWithDescription> convert = DescriptionConvertUtil.convert(pingJiaList);
			for (PingJiaWithDescription pingJiaWithDescription : convert) {
				return pingJiaWithDescription;
			}
		 }  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	




}
