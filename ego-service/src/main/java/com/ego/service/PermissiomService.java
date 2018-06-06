package com.ego.service;

import com.ego.mapper.po.Permission;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface PermissiomService {
	/**
	 * 添加权限
	 * @param permission
	 * @return
	 */
	ReturnMessage<Permission> addPermission(Permission permission);
	/**
	 * 删除权限
	 * @param permission
	 * @return
	 */
	ReturnMessage<Permission> deletePermissionById(Integer PermissionId);
	/**
	 * 更新权限
	 * @param permission
	 * @return
	 */
	ReturnMessage<Permission> updatePermission(Permission permission);
	/**
	 * 查看全部权限
	 * @param permission
	 * @return
	 */
	PageBean<Permission> selectAllPermission(int pageCode, int pageSize);
	/**
	 * 通过角色查看权限
	 * @param permission
	 * @return
	 */
	PageBean<Permission> selectAllPermissionByRoleId(Integer roleId,int pageCode, int pageSize);
}
