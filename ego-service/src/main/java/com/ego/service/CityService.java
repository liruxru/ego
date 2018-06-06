package com.ego.service;

import java.util.List;

import com.ego.mapper.po.City;

public interface CityService {
	/**
	 * 通过省份代码查找所有城市
	 * @param cityCode
	 * @return
	 */
	List<City> selectCityByProvinceCode(String ProvinceCode);
	City selectCityById(Integer cityId);
}
