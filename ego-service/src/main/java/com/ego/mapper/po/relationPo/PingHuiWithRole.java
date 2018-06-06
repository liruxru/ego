package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.util.Date;

import com.ego.document.Description;

public class PingHuiWithRole implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String role;
	private Description  description;
	public PingHuiWithRole(String role, Description description) {
		super();
		this.role = role;
		this.description = description;
	}
	public PingHuiWithRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PingHuiWithRole [role=" + role + ", description=" + description + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		PingHuiWithRole other = (PingHuiWithRole) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
}
