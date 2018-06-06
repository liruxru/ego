package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.StoreMapper;
import com.ego.mapper.po.Permission;
import com.ego.mapper.po.Store;
import com.ego.service.StoreService;
import com.ego.utils.PageBean;

public class StoreServiceImpl implements StoreService {
	@Resource(name="storeMapper")
	private StoreMapper  storeMapper;
	public StoreMapper getStoreMapper() {
		return storeMapper;
	}
	public void setStoreMapper(StoreMapper storeMapper) {
		this.storeMapper = storeMapper;
	}
	
	public PageBean<Store> selectAllStore(int pageCode, int pageSize) {
		PageBean<Store> pageBean=new PageBean<Store>(pageCode, pageSize);
		int count =storeMapper.countStore();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Store> storeList= storeMapper.selectStoresAll(offset, length);
		pageBean.setBeanList(storeList);
		return pageBean;
	}
	public Store selectStoreById(Integer storeId) {
		// TODO Auto-generated method stub
		return storeMapper.selectByPrimaryKey(storeId);
	}
	public Store selectStoreByName(String storeName) {
		// TODO Auto-generated method stub
		return storeMapper.selectStoreIdByStoreName(storeName);
	}
	
}
