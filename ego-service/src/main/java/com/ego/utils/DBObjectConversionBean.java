package com.ego.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.mongodb.DBObject;

public class DBObjectConversionBean {
	/**
	 * 
	 * 将返回的DBObject 转换为javaBean
	 */
	public static <T> T dbObjectToBean(DBObject dbObject, T bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (bean == null) {
			return null;
		}
		// 获取对象类的属性域
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			 // 获取变量的属性名
			String varName = field.getName();
			
			Object object = dbObject.get(varName);
			if (object != null) {
				BeanUtils.setProperty(bean, varName, object);
			}
		}
		return bean;
	}
}
