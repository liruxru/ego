package com.ego;

import java.io.Serializable;
/**
 * admin shen fen 
 * admin - >Session 
 **/
public class Principal implements Serializable{
	private Integer id;
	private String loginName;
	
	public Principal() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Principal(Integer id, String loginName) {
		super();
		this.id = id;
		this.loginName = loginName;
	}
}
