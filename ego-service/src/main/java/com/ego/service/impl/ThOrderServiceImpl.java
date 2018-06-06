package com.ego.service.impl;

import javax.annotation.Resource;

import com.ego.mapper.ThOrderMapper;
import com.ego.service.ThOrderService;

public class ThOrderServiceImpl implements ThOrderService{
	@Resource(name="thOrderMapper")
	private ThOrderMapper thOrderMapper;
	public ThOrderMapper getThOrderMapper() {
		return thOrderMapper;
	}
	public void setThOrderMapper(ThOrderMapper thOrderMapper) {
		this.thOrderMapper = thOrderMapper;
	}
	
}
