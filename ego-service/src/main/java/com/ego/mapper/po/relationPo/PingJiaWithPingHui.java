package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.util.List;

public class PingJiaWithPingHui implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pingJiaId;
	private List<PingHuiWithRole> pingHuiWithRoleList;
	public PingJiaWithPingHui(String pingJiaId, List<PingHuiWithRole> pingHuiWithRoleList) {
		super();
		this.pingJiaId = pingJiaId;
		this.pingHuiWithRoleList = pingHuiWithRoleList;
	}
	public PingJiaWithPingHui() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PingJiaWithPingHui [pingJiaId=" + pingJiaId + ", pingHuiWithRoleList=" + pingHuiWithRoleList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pingHuiWithRoleList == null) ? 0 : pingHuiWithRoleList.hashCode());
		result = prime * result + ((pingJiaId == null) ? 0 : pingJiaId.hashCode());
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
		PingJiaWithPingHui other = (PingJiaWithPingHui) obj;
		if (pingHuiWithRoleList == null) {
			if (other.pingHuiWithRoleList != null)
				return false;
		} else if (!pingHuiWithRoleList.equals(other.pingHuiWithRoleList))
			return false;
		if (pingJiaId == null) {
			if (other.pingJiaId != null)
				return false;
		} else if (!pingJiaId.equals(other.pingJiaId))
			return false;
		return true;
	}
	public String getPingJiaId() {
		return pingJiaId;
	}
	public void setPingJiaId(String pingJiaId) {
		this.pingJiaId = pingJiaId;
	}
	public List<PingHuiWithRole> getPingHuiWithRoleList() {
		return pingHuiWithRoleList;
	}
	public void setPingHuiWithRoleList(List<PingHuiWithRole> pingHuiWithRoleList) {
		this.pingHuiWithRoleList = pingHuiWithRoleList;
	}
	
}
