package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Permission;
@Repository("permissionMapper")
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    @Delete("delete from roles_pers where pers=#{0}")
	void deleteRole_Per(Integer permissionId);
    @Select("select count(id) from permission")
	int countPermission();
    @Select("select * from permission limit #{0},#{1}")
	List<Permission> selectPermissionAll(int pageCode, int pageSize);
    @Select("select distinct p.* from permission p,roles_pers rp  where p.id=rp.pers and rp.roles=#{0}  limit #{1},#{2}")
	List<Permission> selectPermissionAllByRoleId(Integer roleId, int offset, int length);
}