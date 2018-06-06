package com.ego.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.ego.mapper.UserMapper;
import com.ego.mapper.po.User;
import com.ego.service.UserService;
import com.ego.utils.PageBean;
import com.ego.utils.ReturnMessage;

public class UserServiceImpl implements UserService {
	@Resource(name="returnmessage")
	ReturnMessage<User> returnmessage;
	private UserMapper userMapper;
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUserByLoginName(User userVo) {
		User userDo = userMapper.getUserByLoginName(userVo.getLoginname());
		if(userDo==null||!userVo.getLoginpwd().equals(userDo.getLoginpwd()))
		return null;
		BeanUtils.copyProperties(userDo, userVo);
		return userVo;
	}
	
	public ReturnMessage<User> updateUser(User user) {
		try {
			userMapper.updateByPrimaryKeySelective(user);
			returnmessage.setResultCode(0);
		} catch (Exception e) {
			returnmessage.setResultCode(1);
			returnmessage.setBean(user);
			returnmessage.setMessage(e.getMessage());
		}
		 return returnmessage;
		
	}
	/**
	 * 用户注册
	 */
	public ReturnMessage<User> addUser(User user) {
		try {
			userMapper.insert(user);
			returnmessage.setResultCode(0);
		} catch (Exception e) {
			returnmessage.setResultCode(1);
			returnmessage.setBean(user);
			returnmessage.setMessage(e.getMessage());
		}
		return returnmessage;
	}

	public ReturnMessage<User> delectUserById(Integer userId) {
		try {
			userMapper.deleteByPrimaryKey(userId);
			returnmessage.setResultCode(0);
		} catch (Exception e) {
			returnmessage.setResultCode(1);
			returnmessage.setMessage(e.getMessage());
		}
		return returnmessage;
	}

	public PageBean<User> selectAllUser(int pageCode, int pageSize) {
		PageBean<User> page=new PageBean<User>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		int start=page.getPageStart();
		int length=page.getPageSize();
		int count=userMapper.countUser();
		page.setCount(count);
		List<User> userList=userMapper.selectAllUser(start,length);
		page.setBeanList(userList);
		return page;
	}

	public User getUserByLoginName(String loginname) {
		return userMapper.getUserByLoginName(loginname);
	}
	public User selectByPrimaryKey(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}
	public User getUserByLoginPhone(String phone) {
		return userMapper.getUserByLoginPhone(phone);
	}


}
