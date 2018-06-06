package com.ego.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ego.mapper.po.Admin;
import com.ego.mapper.po.relationPo.AdminWithRole;
@Repository("adminMapper")
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    @Select("select * from admins where loginname=#{loginname}")
	Admin getAdminByLoginName(String loginName);
    @Select("select distinct p.url from permission p,admins_roles ra,roles_pers rp "
    		+ " where ra.admins=#{adminId} and rp.roles=ra.roles and p.id=rp.pers ")
	List<String> listPer(Integer id);
    @Delete("delete from admins_roles where admins=#{adminId}")
	void deleteAdmins_roleswithAdminId(Integer adminId);
    @Update("update admins_roles set roles=#{1} where admins=#{0}")
	void updateAdmins_roleswithAdminId(Integer adminId, Integer roleId);

	void insertBackId(Admin admin);
	@Insert("insert into admins_roles values(#{0},#{1}) ")
	void addAdmin_role(int adminId, Integer roleId);
	@Select("select distinct a.id as id,"
			+ "a.loginname as loginname, a.password as password,r.rolename as rolename"
			+ " from admins a left join admins_roles ar on(ar.admins=a.id) left join roles r on (ar.roles=r.id)  where a.id=#{0}")
	AdminWithRole selectAdminWithRoleByAdminId(Integer adminId);
	@Select("select a.id as id,"
			+ "a.loginname as loginname, a.password as password,r.rolename as rolename"
			+ " from admins a left join admins_roles ar on(ar.admins=a.id) "
			+ "left join roles r on (ar.roles=r.id) "
			+ " where a.loginname=#{0} and a.password=#{1} ")
	AdminWithRole selectAdminWithRoleByLoginnameAndPassword(String loginname,String password);
}

