package com.ego.service;


import com.ego.mapper.po.Store;
import com.ego.utils.PageBean;

public interface StoreService {
	/**
	 * 查看商店
	 * @return
	 */
	PageBean<Store> selectAllStore(int pageCode, int pageSize);
	/**
	 * 通过店铺id查找店铺
	 * @param storeId
	 * @return
	 */
	Store selectStoreById(Integer storeId);
	Store selectStoreByName(String storeName);
}
