package com.ego.utils;

import java.util.List;

public class ResultUtils {
	public static <T> ReturnMessage<T>  success(){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(0);
		returnMeassage.setMessage("操作成功");
		return returnMeassage;
	}
	
	public static <T> ReturnMessage<T>  success(T bean){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(0);
		returnMeassage.setMessage("操作成功");
		returnMeassage.setBean(bean);
		return returnMeassage;
	}
	public static <T> ReturnMessage<T>  success(List<T> beanList){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(0);
		returnMeassage.setMessage("操作成功");
		returnMeassage.setListBean(beanList);
		return returnMeassage;
	}
	
	public static <T> ReturnMessage<T>  success(List<T> beanList,T bean){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(0);
		returnMeassage.setMessage("操作成功");
		returnMeassage.setBean(bean);
		returnMeassage.setListBean(beanList);
		return returnMeassage;
	}
	
	public static <T> ReturnMessage<T>  error(Exception e,List<T> beanList,T bean){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(1);
		returnMeassage.setMessage("操作失败"+e);
		returnMeassage.setBean(bean);
		returnMeassage.setListBean(beanList);
		return returnMeassage;
	}
	public static <T> ReturnMessage<T>  error(Exception e,List<T> beanList){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(1);
		returnMeassage.setMessage("操作失败"+e);
		returnMeassage.setListBean(beanList);
		return returnMeassage;
	}
	public static <T> ReturnMessage<T>  error(Exception e,T bean){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(1);
		returnMeassage.setMessage("操作失败"+e);
		returnMeassage.setBean(bean);
		return returnMeassage;
	}
	public static <T> ReturnMessage<T>  error(Exception e){
		ReturnMessage<T> returnMeassage=new ReturnMessage<T>();
		returnMeassage.setResultCode(1);
		returnMeassage.setMessage("操作失败"+e);
		return returnMeassage;
	}
}
