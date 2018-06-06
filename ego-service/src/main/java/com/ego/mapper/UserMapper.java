package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ego.mapper.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Select("select * from users where loginname=#{loginName}")
   	User getUserByLoginName(String loginName);
    @Select("select * from users limit #{0} , #{1}")
   	List<User> selectAllUser(Integer start, Integer length);
    @Select("select count(id) from users")
   	int countUser();
    @Select("select * from users where phone=#{phone}")
   	User getUserByLoginPhone(String phone);

}