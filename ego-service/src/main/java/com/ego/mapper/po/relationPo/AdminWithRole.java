package com.ego.mapper.po.relationPo;

import java.io.Serializable;

public class AdminWithRole  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String loginname;
    private String password;
    private String rolename;
	public AdminWithRole(Integer id, String loginname, String password, String rolename) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.rolename = rolename;
	}
	public AdminWithRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		AdminWithRole other = (AdminWithRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginname == null) {
			if (other.loginname != null)
				return false;
		} else if (!loginname.equals(other.loginname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AdminWithRole [id=" + id + ", loginname=" + loginname + ", password=" + password + ", rolename="
				+ rolename + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
    


}
