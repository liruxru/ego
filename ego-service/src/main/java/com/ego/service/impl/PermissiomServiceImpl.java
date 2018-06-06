package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.mapper.PermissionMapper;
import com.ego.mapper.po.Order;
import com.ego.mapper.po.Permission;
import com.ego.service.PermissiomService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public class PermissiomServiceImpl implements PermissiomService {
	@Resource(name="permissionMapper")
	private PermissionMapper permissionMapper;
	public PermissionMapper getPermissionMapper() {
		return permissionMapper;
	}
	public void setPermissionMapper(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}
	//添加全权限
	public ReturnMessage<Permission> addPermission(Permission permission) {
		 ReturnMessage<Permission> returnMessage=new ReturnMessage<Permission>();
		 try {
			 if(permission.getUrl()==null) {
				 throw new Exception("角色路径不能为空");
			 }
			 if(permission.getPername()==null) {
				 throw new Exception("角色描述不能为空");
			 }
			 List<Permission> permissionList=permissionMapper.selectPermissionAll(1, 1000);
			 for (Permission permission2 : permissionList) {
				if(permission2.getUrl().equals(permission.getUrl())) {
					throw new Exception("存在相同的权限");
				}
				if(permission2.getPername().equals(permission.getPername())) {
					throw new Exception("权限描述不能相同");
				}
			}
			permissionMapper.insert(permission);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("添加角色成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("添加权限失败"+e.getMessage());
		}
		 return returnMessage;
	}
	//删除权限，同时要移除角色下的这个权限
	public ReturnMessage<Permission> deletePermissionById(Integer PermissionId) {
		 ReturnMessage<Permission> returnMessage=new ReturnMessage<Permission>();
		 try {
			 permissionMapper.deleteRole_Per(PermissionId);
			permissionMapper.deleteByPrimaryKey(PermissionId);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("权限删除成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("权限删除失败"+e.getMessage());
		}
		 return returnMessage;
	}
	public ReturnMessage<Permission> updatePermission(Permission permission) {
		 ReturnMessage<Permission> returnMessage=new ReturnMessage<Permission>();
		 try {
			 if("".equals(permission.getUrl())) {
				 permission.setUrl(null);
			 }
			permissionMapper.updateByPrimaryKeySelective(permission);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("权限修改成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("权限修改失败"+e.getMessage());
		}
		 return returnMessage;
	}
	public PageBean<Permission> selectAllPermission(int pageCode, int pageSize) {
		PageBean<Permission> pageBean=new PageBean<Permission>(pageCode, pageSize);
		int count =permissionMapper.countPermission();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Permission> perList= permissionMapper.selectPermissionAll(offset, length);
		pageBean.setBeanList(perList);
		return pageBean;
	}
	public PageBean<Permission> selectAllPermissionByRoleId(Integer roleId, int pageCode, int pageSize) {
		PageBean<Permission> pageBean=new PageBean<Permission>(pageCode, pageSize);
		int count =permissionMapper.countPermission();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Permission> perList= permissionMapper.selectPermissionAllByRoleId(roleId,offset, length);
		pageBean.setBeanList(perList);
		return pageBean;
	}
	
}
