package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.City;
@Repository("cityMapper")
public interface CityMapper {
    int insert(City record);

    int insertSelective(City record);
    @Select("select * from city where provinceCode=#{provinceCode}")
	List<City> selectAreaByProvinceCode(String provinceCode);
    @Select("select * from city where id=#{0}")
	City selectCityById(Integer cityId);
}