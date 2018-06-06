package com.ego.service;

import com.ego.mapper.po.User;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public interface UserService {
	
	/**
	 * 通过id获取用户信息
	 * @param key
	 */
	User selectByPrimaryKey(Integer id);
	
	/**
	 * 用户登录
	 * @param loginName
	 */
	User getUserByLoginName(User userVo);
	/**
	 * 校验用户名
	 * @param loginName
	 */
	User getUserByLoginName(String loginname);
	
	/**
	 * 修改用户密码,登录日期等
	 * @param user
	 * @return
	 */
	ReturnMessage<User>  updateUser(User user);
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	ReturnMessage<User>  addUser(User user);
	/**
	 * 通过用户id删除用户
	 * @param userId
	 * @return
	 */
	ReturnMessage<User> delectUserById(Integer userId);
	/**
	 * 查看全部会员
	 * @return
	 */
	PageBean<User> selectAllUser(int pageCode,int pageSize);
	/**
	 * 通过手机号查询是否已经被注册过 getUserByLoginPhone
	 * @param phone
	 * @return
	 */
	User getUserByLoginPhone(String phone);
}
