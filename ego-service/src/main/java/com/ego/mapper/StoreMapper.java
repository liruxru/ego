package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Store;
@Repository("storeMapper")
public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    @Select("select count(id) from stores")
	int countStore();
    @Select("select * from stores limit #{0},#{1}")
	List<Store> selectStoresAll(int offset, int length);
    @Select("select id from stores where name=#{0}")
	Integer selectStoreIdByName(String storeName);
    @Select("select * from stores where name=#{0}")
	Store selectStoreIdByStoreName(String storeName);
}