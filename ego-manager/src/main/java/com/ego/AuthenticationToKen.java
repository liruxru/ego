package com.ego;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义令牌
 *
 */
public class AuthenticationToKen extends UsernamePasswordToken{
	private static final long serialVersionUID = 1L;
	private boolean isValid;
	public AuthenticationToKen(String loginName,String password,boolean isValid) {
		super(loginName, password);
		this.isValid=isValid;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
