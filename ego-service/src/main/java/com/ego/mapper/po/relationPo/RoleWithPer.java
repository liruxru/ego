package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.util.List;

import com.ego.mapper.po.Permission;

public class RoleWithPer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String rolename;
	private String descripation;
	private List<Permission> perList;
	public RoleWithPer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleWithPer(Integer roleId, String rolename, String descripation, List<Permission> perList) {
		super();
		this.roleId = roleId;
		this.rolename = rolename;
		this.descripation = descripation;
		this.perList = perList;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDescripation() {
		return descripation;
	}
	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}
	public List<Permission> getPerList() {
		return perList;
	}
	public void setPerList(List<Permission> perList) {
		this.perList = perList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RoleWithPer [roleId=" + roleId + ", rolename=" + rolename + ", descripation=" + descripation
				+ ", perList=" + perList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripation == null) ? 0 : descripation.hashCode());
		result = prime * result + ((perList == null) ? 0 : perList.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleWithPer other = (RoleWithPer) obj;
		if (descripation == null) {
			if (other.descripation != null)
				return false;
		} else if (!descripation.equals(other.descripation))
			return false;
		if (perList == null) {
			if (other.perList != null)
				return false;
		} else if (!perList.equals(other.perList))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}
	
	
	    
}
