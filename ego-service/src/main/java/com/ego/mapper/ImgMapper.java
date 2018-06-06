package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Img;
@Repository("imgMapper")
public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
    @Select("select * from imgs  where goods=#{0}")
	List<Img> selectImgsByGoodsId(String id);
}