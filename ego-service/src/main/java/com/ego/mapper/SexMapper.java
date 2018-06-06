package com.ego.mapper;

import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Sex;
@Repository("sexMapper")
public interface SexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sex record);

    int insertSelective(Sex record);

    Sex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sex record);

    int updateByPrimaryKey(Sex record);
}