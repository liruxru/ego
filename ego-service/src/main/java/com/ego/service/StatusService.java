package com.ego.service;

import java.util.List;

import com.ego.mapper.po.Status;

public interface StatusService {

	/**
	 * 全查状态
	 */
	List<Status> findAllStatus();
}
