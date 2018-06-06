package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.ProvinceMapper;
import com.ego.mapper.po.Province;
import com.ego.service.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService {
	@Resource(name="provinceMapper")
	private ProvinceMapper provinceMapper;
	public ProvinceMapper getProvinceMapper() {
		return provinceMapper;
	}
	public void setProvinceMapper(ProvinceMapper provinceMapper) {
		this.provinceMapper = provinceMapper;
	}
	public List<Province> findAllprovince() {
		
		return provinceMapper.selectAll();
	}
	public Province selectProvinceById(Integer provinceId) {
		
		return provinceMapper.selectProvinceById(provinceId);
	}
	
}
