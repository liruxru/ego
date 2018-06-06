package com.ego;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ego.mapper.po.Admin;
import com.ego.service.AdminService;

public class AuthenticationRealm extends AuthorizingRealm{
	
	@Resource(name="adminService")
	private AdminService adminService;

	/**
	 * jian quan  
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal=(Principal) principals.fromRealm(getName()).iterator().next();
		if(principal!=null) {
			//permision quaxian 
			List<String> listPer=this.adminService.listPer(principal.getId());
			if(listPer!=null) {
				//quanxian guanliqi admin quan jiao you shiro guanli
				SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(listPer);
				return authorizationInfo;
			}
		}
		return null;
	}
	
	/**
	 * ren zheng fangfa 
	 * 
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationToKen authenticationToKen=(AuthenticationToKen) token;
		String loginName=authenticationToKen.getUsername();
		String password=new String(authenticationToKen.getPassword());
		if(!authenticationToKen.isValid()) {
			throw new UnsupportedTokenException();
		}
		if(loginName!=null&&!"".equals(loginName)) {
			Admin admin=this.adminService.getAdminByLoginName(loginName);
			if(admin==null) {
				throw new UnknownAccountException();
			}
			if(!admin.getPwd().equals(password)) {
				throw new IncorrectCredentialsException();
			}
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), loginName), password, getName());
		}
		return null;
	}
}
