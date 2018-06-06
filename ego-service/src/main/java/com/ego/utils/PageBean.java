package com.ego.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
public class PageBean<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageCode;//页码
	private int pageSize;//每页展示的数量
	private int  count;//查询到的总数量
	private List<T> beanList;
	
	public PageBean(int pageCode, int pageSize) {
		super();
		this.pageCode = pageCode;
		this.pageSize = pageSize;
	}
	public PageBean(int pageCode, int pageSize, int count) {
		super();
		this.pageCode = pageCode;
		this.pageSize = pageSize;
		this.count=count;
	}
	public PageBean() {
		// TODO Auto-generated constructor stub
	}
	//计算总页数
	public int getPageCount() {
		return count%pageSize==0? count/pageSize:count/pageSize+1;
	}
	//计算起始位置
	public int getPageStart() {
		return pageSize*(pageCode-1);
	}
	//计算结束为止
	public int getPageEnd() {
		return pageSize;
	}
	
	
	public PageBean(int pageCode, int pageSize, int count, List<T> beanList) {
		super();
		this.pageCode = pageCode;
		this.pageSize = pageSize;
		this.count = count;
		this.beanList = beanList;
	}
	
	
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	
	

}
