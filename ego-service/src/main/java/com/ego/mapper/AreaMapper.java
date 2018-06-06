package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Area;
@Repository("areaMapper")
public interface AreaMapper {
    int insert(Area record);
    int insertSelective(Area record);
    @Select("select * from area where citycode=#{0}")
	List<Area> selectAreaByCityCode(String cityCode);
    @Select("select * from area where id=#{0}")
	Area selectAreaById(Integer areaId);
}