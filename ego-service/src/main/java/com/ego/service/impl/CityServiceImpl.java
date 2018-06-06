package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.CityMapper;
import com.ego.mapper.po.City;
import com.ego.service.CityService;

public class CityServiceImpl implements CityService {
	@Resource(name="cityMapper")
	private CityMapper cityMapper;
	public CityMapper getCityMapper() {
		return cityMapper;
	}
	public void setCityMapper(CityMapper cityMapper) {
		this.cityMapper = cityMapper;
	}
	public  List<City> selectCityByProvinceCode(String ProvinceCode) {
		
		return cityMapper.selectAreaByProvinceCode(ProvinceCode);
	}
	public City selectCityById(Integer cityId) {
		return cityMapper.selectCityById(cityId);
	}
	
}
