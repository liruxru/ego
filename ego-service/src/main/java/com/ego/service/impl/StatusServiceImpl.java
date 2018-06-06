package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.StatusMapper;
import com.ego.mapper.po.Status;
import com.ego.service.StatusService;

public class StatusServiceImpl implements StatusService {

	@Resource(name="statusMapper")
	private StatusMapper statusMapper;
	public StatusMapper getStatusMapper() {
		return statusMapper;
	}
	public void setStatusMapper(StatusMapper statusMapper) {
		this.statusMapper = statusMapper;
	}

	public List<Status> findAllStatus() {
		
		return this.statusMapper.findAllStatus();
	}

}
