package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Type;
@Repository("typeMapper")
public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
    @Select("select count(id) from types")
	int countType();
    @Select("select * from types limit #{0},#{1}")
	List<Type> selectTypeAll(int offset, int length);
	
	   
}