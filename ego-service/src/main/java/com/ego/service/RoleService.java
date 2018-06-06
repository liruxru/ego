package com.ego.service;

import com.ego.mapper.po.Role;
import com.ego.mapper.po.relationPo.RoleWithPer;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface RoleService {
	/**
	 * 添加角色
	 * @param permission
	 * @return
	 */
	ReturnMessage<Role> addRole(Role role,Integer[] PerIds);
	/**
	 * 删除角色
	 * @param permission
	 * @return
	 */
	ReturnMessage<Role> deleteRoleByRoleId(Integer roleId);
	/**
	 * 更新角色,通过角色id 和他的全部权限的id
	 * @param permission
	 * @return
	 */
	ReturnMessage<Role> updateRole(Integer roleId, Integer[] PerIds);
	/**
	 * 查看全部角色
	 * @param permission
	 * @return
	 */
	PageBean<Role> selectAllRole(int pageCode, int pageSize);
	/**
	 * 查找全部角色he他们的权限
	 * @param pageCode
	 * @param pageSize
	 * @return
	 */
	
	PageBean<RoleWithPer> selectAllRoleWithPer(int pageCode, int pageSize);
}
