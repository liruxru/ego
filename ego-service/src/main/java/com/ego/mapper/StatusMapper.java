package com.ego.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Status;
@Repository("statusMapper")
public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);
    
	List<Status> findAllStatus();
}