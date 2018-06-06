package com.ego.service;

import java.util.List;

import com.ego.mapper.po.Area;

public interface AreaService {
	/**
	 * 通过城市代码查找所有区
	 * @param cityCode
	 * @return
	 */
	List<Area> selectAreaByCityCode(String cityCode);
	Area selectAreaById(Integer areaId);
}
