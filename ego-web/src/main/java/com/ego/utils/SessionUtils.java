package com.ego.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.ego.exception.ParamException;
import com.ego.mapper.po.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* zhaoran
*/
public class SessionUtils implements Serializable{
	
	private RedisTemplate<String, Object> redisTemplate;
	
	
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public User getUserInfo(String jSessionId) throws ParamException, JsonParseException, JsonMappingException, IOException {
		if(jSessionId==null||"".equals(jSessionId)) {
			throw new ParamException();
		}
		ObjectMapper om=new ObjectMapper();
		String strJson=(String) this.redisTemplate.opsForValue().get(jSessionId);
		User user=null;
		if(strJson!=null&&!"".equals(strJson)) {
			user=om.readValue(strJson, User.class);
		}
		return user;
	}
	
	public boolean setUserInfo(String jSessionId,User User) throws ParamException,RuntimeException, JsonProcessingException {
		boolean bln=false;
		if(jSessionId==null||"".equals(jSessionId)) {
			throw new ParamException();
		}
		if(User==null) {
			throw new ParamException();
		}
		try {
			ObjectMapper om=new ObjectMapper();
			String jsonstr= om.writeValueAsString(User);
			redisTemplate.opsForValue().set(jSessionId, jsonstr, 30,TimeUnit.MINUTES);
			bln=true;
		} catch (RuntimeException e) {
			throw e;
		}
		return bln;
	}
	
	public boolean removeUserInfo(String jSessionId) throws ParamException,RuntimeException {
		boolean bln=false;
		if(jSessionId==null||"".equals(jSessionId)) {
			throw new ParamException();
		}
		try {
			this.redisTemplate.delete(jSessionId);
			bln=true;
		} catch (RuntimeException e) {
			throw e;
		}
		return bln;
	}
}
