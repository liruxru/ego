package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.TypeMapper;
import com.ego.mapper.po.Store;
import com.ego.mapper.po.Type;
import com.ego.service.TypeService;
import com.ego.utils.PageBean;

public class TypeServiceImpl implements TypeService {
	@Resource(name="typeMapper")
	private TypeMapper typeMapper;
	public TypeMapper getTypeMapper() {
		return typeMapper;
	}
	public void setTypeMapper(TypeMapper typeMapper) {
		this.typeMapper = typeMapper;
	}
	public PageBean<Type> selectAllType(int pageCode, int pageSize) {
		PageBean<Type> pageBean=new PageBean<Type>(pageCode, pageSize);
		int count =typeMapper.countType();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Type> storeList= typeMapper.selectTypeAll(offset, length);
		pageBean.setBeanList(storeList);
		return pageBean;
	}
	
}
