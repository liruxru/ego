package com.ego.service;

import com.ego.mapper.po.Type;
import com.ego.utils.PageBean;

public interface TypeService {
	/**
	 * 查看全部类型
	 * @return
	 */
	PageBean<Type> selectAllType(int pageCode, int pageSize);
}
