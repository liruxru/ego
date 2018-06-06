package com.ego.mapper;

import org.springframework.stereotype.Repository;

import com.ego.mapper.po.ThOrder;
@Repository("thOrderMapper")
public interface ThOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThOrder record);

    int insertSelective(ThOrder record);

    ThOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThOrder record);

    int updateByPrimaryKey(ThOrder record);
}