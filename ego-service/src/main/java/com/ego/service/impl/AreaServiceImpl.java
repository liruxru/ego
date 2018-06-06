package com.ego.service.impl;

import java.util.List;

import com.ego.mapper.AreaMapper;
import com.ego.mapper.po.Area;
import com.ego.service.AreaService;

public class AreaServiceImpl implements AreaService {
	private AreaMapper areaMapper;
	public AreaMapper getAreaMapper() {
		return areaMapper;
	}
	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}
	public List<Area> selectAreaByCityCode(String cityCode) {		
		return areaMapper.selectAreaByCityCode(cityCode);
	}
	public Area selectAreaById(Integer areaId) {
		// TODO Auto-generated method stub
		return areaMapper.selectAreaById(areaId);
	}
	
}
