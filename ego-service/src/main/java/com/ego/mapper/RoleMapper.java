package com.ego.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Permission;
import com.ego.mapper.po.Role;
import com.ego.mapper.po.relationPo.RoleWithPer;
@Repository("roleMapper")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
	void insertRole_per(Map map);
	@Delete("delete from roles_pers where roles=#{0}")
	void deleteRole_per(Integer roleId);
	@Delete("delete from admins_roles where roles=#{0}")
	void deleteRole_admin(Integer roleId);
	@Select("select count(id) from roles")
	int countRole();
	@Select("select * from roles limit #{0},#{1}")
	List<Role> selectRoleAll(int pageCode, int pageSize);
	
	void insertBackId(Role role);
	
/*	private Integer roleId;
	private String rolename;
	private String descripation;
	private Integer perId;
	private String pername;
	private String url;*/
	@Select("select r.id as roleId, r.rolename as rolename, r.descripation as descripation,"
			+ " p.id as perId,p.pername as pername,p.url as url   "
			+ "from roles r left join roles_pers rp on(r.id=rp.roles) "
			+ " left join permission p  on(p.id=rp.pers)  limit #{0},#{1}")
	List<RoleWithPer> selectRoleWithPer(int offset, int length);
	@Select("select * from permission p "
			+ "left join  roles_pers rp on(rp.pers=p.id) "
			+ " left join roles r on(r.id=rp.roles) "
			+ " where r.id=#{roleId} ")
	List<Permission> selectPerByRoleId(Integer roleId);
}