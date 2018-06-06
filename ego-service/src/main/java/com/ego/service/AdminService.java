package com.ego.service;

import java.util.List;

import com.ego.mapper.po.Admin;
import com.ego.mapper.po.relationPo.AdminWithRole;
import com.ego.utils.ReturnMessage;

public interface AdminService {
	/**
	 * 管理员登录
	 * @param loginName
	 */
	Admin getAdminByLoginName(String loginName);
	/**
	 * @param adminId  管理员id
	 */
	List<String> listPer(Integer id);
	/**
	 * 修改管理员密码,登录日期等
	 * @param admin
	 * @return
	 */
	ReturnMessage<Admin>  updateAdmin(Admin admin);
	/**
	 * 添加管理员，不添加角色
	 * @param admin
	 * @return
	 */
	ReturnMessage<Admin>  addAdmin(Admin admin);
	/**
	 * 删除管理员
	 * @param admin
	 * @return
	 */
	ReturnMessage<Admin>  deleteAdmin(Integer adminId);
	/**
	 * 修改管理员角色
	 * @param admin
	 * @return
	 */
	ReturnMessage<Admin>  updateAdmin_role(Integer adminId,Integer roleId);
	/**
	 * 添加管理员带角色 新增管理员,返回主键，建立角色管理员关系
	 * @param admin 管理员对象
	 * @param roleId 角色id
	 * @return
	 */
	ReturnMessage<Admin>  addAdmin_role(Admin admin,Integer roleId);
	/**
	 * 通过登录名登录同时获取角色名
	 * @param admin (loginName 登录名,和密码    或者只通过管理员id查找角色名)
	 * @return  如果返回值为空 说明登录名密码错误或者这个id的管理员不存在或者他没有角色
	 */
	AdminWithRole getAdminWithRoleByAdmin(Admin admin);
}
