package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Province;
@Repository("provinceMapper")
public interface ProvinceMapper {
    int insert(Province record);

    int insertSelective(Province record);
    @Select("select * from province")
	List<Province> selectAll();
    @Select("select * from province where id=#{0}")
	Province selectProvinceById(Integer provinceId);
}