package com.ego.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ego.mapper.AdminMapper;
import com.ego.mapper.po.Admin;
import com.ego.mapper.po.relationPo.AdminWithRole;
import com.ego.service.AdminService;
import com.ego.utils.ResultUtils;
import com.ego.utils.ReturnMessage;

public class AdminServiceImpl implements AdminService {
	@Resource(name="adminMapper")
	private AdminMapper adminMapper;
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	public Admin getAdminByLoginName(String loginName) {
		return adminMapper.getAdminByLoginName(loginName);
	}
	public List<String> listPer(Integer adminId) {
		return adminMapper.listPer(adminId);
	}
	public ReturnMessage<Admin> updateAdmin(Admin admin) {
		try {
			adminMapper.updateByPrimaryKeySelective(admin);
			return ResultUtils.success();
		} catch (Exception e) {
			return ResultUtils.error(e);
		}
		
	}
	public ReturnMessage<Admin> addAdmin(Admin admin) {
		try {
			adminMapper.insert(admin);
			return ResultUtils.success();
		} catch (Exception e) {
			return ResultUtils.error(e);
		}
	}
	public ReturnMessage<Admin> deleteAdmin(Integer adminId) {
		try {
			//删除角色和管理员关系
			adminMapper.deleteAdmins_roleswithAdminId(adminId);
			adminMapper.deleteByPrimaryKey(adminId);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	public ReturnMessage<Admin> updateAdmin_role(Integer adminId, Integer roleId) {
		try {
			Admin admin = adminMapper.selectByPrimaryKey(adminId);
			if(admin==null) {
				throw new Exception("不存在这个管理员");
			}
			adminMapper.updateAdmins_roleswithAdminId(adminId,roleId);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
	}
	public ReturnMessage<Admin> addAdmin_role(Admin admin, Integer roleId) {
		try {
			admin.setCreatedate(new Date());
			adminMapper.insertBackId(admin);
			int adminId=admin.getId();
			adminMapper.addAdmin_role(adminId,roleId);
			return ResultUtils.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.error(e);
		}
		
	}
	public AdminWithRole getAdminWithRoleByAdmin(Admin admin) {
		if(admin.getId()!=null) {
			return adminMapper.selectAdminWithRoleByAdminId(admin.getId());
		}
		if(admin.getLoginname()!=null&&admin.getPassword()!=null) {
			return adminMapper.selectAdminWithRoleByLoginnameAndPassword(admin.getLoginname(),admin.getPassword());
		}
		return null;
	}
	

}
