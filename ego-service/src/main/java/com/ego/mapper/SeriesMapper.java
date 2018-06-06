package com.ego.mapper;

import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Series;
@Repository("seriesMapper")
public interface SeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Series record);

    int insertSelective(Series record);

    Series selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);
}