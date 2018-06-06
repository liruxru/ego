package com.ego.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.mapper.RoleMapper;
import com.ego.mapper.po.Role;
import com.ego.mapper.po.relationPo.RoleWithPer;
import com.ego.service.RoleService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public class RoleServiceImpl implements RoleService {
	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	// 新增一个角色,带来这个角色的所有权限
	public ReturnMessage<Role> addRole(Role role, Integer[] PerIds) {
		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		try {
			// 需要返回主键
			roleMapper.insertBackId(role);
			Integer roleId=role.getId();
			// 添加关系
			System.out.println("RoleServiceImpl"+roleId);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("perIds", PerIds);
			roleMapper.insertRole_per(map);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("添加角色成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("添加角色失败"+e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnMessage;
		
	}

	public ReturnMessage<Role> deleteRoleByRoleId(Integer roleId) {
		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		try {
			// 删除角色权限关系
			roleMapper.deleteRole_per(roleId);
			// 删除角色管理员
			roleMapper.deleteRole_admin(roleId);
			roleMapper.deleteByPrimaryKey(roleId);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("删除角色成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage("删除角色失败" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnMessage;
	}

	public ReturnMessage<Role> updateRole(Integer roleId, Integer[] PerIds) {
		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		try {
			// 删除角色权限关系
			roleMapper.deleteRole_per(roleId);
			// 添加关系
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("perIds", PerIds);
			roleMapper.insertRole_per(map);
			returnMessage.setResultCode(0);
			returnMessage.setMessage("修改角色成功");
		} catch (Exception e) {
			returnMessage.setResultCode(1);
			returnMessage.setMessage(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnMessage;
	}

	public PageBean<Role> selectAllRole(int pageCode, int pageSize) {
		PageBean<Role> pageBean=new PageBean<Role>(pageCode, pageSize);
		int count =roleMapper.countRole();
		pageBean.setCount(count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Role> roleList= roleMapper.selectRoleAll(offset, length);
		pageBean.setBeanList(roleList);
		return pageBean;
	}

	public PageBean<RoleWithPer> selectAllRoleWithPer(int pageCode, int pageSize) {
		int count =roleMapper.countRole();
		PageBean<RoleWithPer> pageBean=new PageBean<RoleWithPer>(pageCode, pageSize, count);
		int offset=pageBean.getPageStart();
		int length=pageBean.getPageEnd();
		List<Role> roleList= roleMapper.selectRoleAll(offset, length);
		List<RoleWithPer> roleWithPerList=new ArrayList<RoleWithPer>();
		RoleWithPer roleWithPer=null;
		for (Role role : roleList) {
			roleWithPer=new RoleWithPer(role.getId(), role.getRolename(), 
					role.getDescripation(), roleMapper.selectPerByRoleId(role.getId()));
			roleWithPerList.add(roleWithPer);
		}
		/*List<RoleWithPer> roleWithPerList= roleMapper.selectRoleWithPer(offset, length);*/
		pageBean.setBeanList(roleWithPerList);
		return pageBean;
	}

	
}
