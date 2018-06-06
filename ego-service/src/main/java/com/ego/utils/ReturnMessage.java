package com.ego.utils;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
@Component("returnmessage")
public class ReturnMessage<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer resultCode;
	private String message;
	private T bean;
	private List<T> listBean;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public ReturnMessage(Integer resultCode, String message, T bean, List<T> listBean) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.bean = bean;
		this.listBean = listBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public List<T> getListBean() {
		return listBean;
	}

	public void setListBean(List<T> listBean) {
		this.listBean = listBean;
	}

	public ReturnMessage() {
		super();
	}

}
