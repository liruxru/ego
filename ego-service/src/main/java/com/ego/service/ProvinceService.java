package com.ego.service;

import java.util.List;

import com.ego.mapper.po.Province;

public interface ProvinceService {
	/**
	 * 遍历所有省份
	 * @return
	 */
	List<Province> findAllprovince();
	/**
	 * 通过id获取省份
	 * @param provinceId
	 * @return
	 */
	Province selectProvinceById(Integer provinceId);
}
