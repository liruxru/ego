package com.ego.utils;

import java.io.Serializable;
import java.math.BigDecimal;

public class SearchGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	private String _id;
	private String name;
	private String fullName;
	private Integer minNum;
	private Integer maxNum;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private String typename;
	private String storename;
	private int pageSize; // 页面大小
	private int currentPage; // 当前页
	private Integer shengXu;
	private Integer jiangXu;
	private Integer lock;
	public SearchGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getMinNum() {
		return minNum;
	}
	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getShengXu() {
		return shengXu;
	}
	public void setShengXu(Integer shengXu) {
		this.shengXu = shengXu;
	}
	public Integer getJiangXu() {
		return jiangXu;
	}
	public void setJiangXu(Integer jiangXu) {
		this.jiangXu = jiangXu;
	}
	public Integer getLock() {
		return lock;
	}
	public void setLock(Integer lock) {
		this.lock = lock;
	}

	public SearchGoods(String _id, String name, String fullName, Integer minNum, Integer maxNum, BigDecimal minPrice,
			BigDecimal maxPrice, String typename, String storename, int pageSize, int currentPage, Integer shengXu,
			Integer jiangXu, Integer lock) {
		super();
		this._id = _id;
		this.name = name;
		this.fullName = fullName;
		this.minNum = minNum;
		this.maxNum = maxNum;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.typename = typename;
		this.storename = storename;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.shengXu = shengXu;
		this.jiangXu = jiangXu;
		this.lock = lock;
	}

	@Override
	public String toString() {
		return "SearchGoods [_id=" + _id + ", name=" + name + ", fullName=" + fullName + ", minNum=" + minNum
				+ ", maxNum=" + maxNum + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", typename=" + typename
				+ ", storename=" + storename + ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", shengXu="
				+ shengXu + ", jiangXu=" + jiangXu + ", lock=" + lock + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + currentPage;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((jiangXu == null) ? 0 : jiangXu.hashCode());
		result = prime * result + ((lock == null) ? 0 : lock.hashCode());
		result = prime * result + ((maxNum == null) ? 0 : maxNum.hashCode());
		result = prime * result + ((maxPrice == null) ? 0 : maxPrice.hashCode());
		result = prime * result + ((minNum == null) ? 0 : minNum.hashCode());
		result = prime * result + ((minPrice == null) ? 0 : minPrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pageSize;
		result = prime * result + ((shengXu == null) ? 0 : shengXu.hashCode());
		result = prime * result + ((storename == null) ? 0 : storename.hashCode());
		result = prime * result + ((typename == null) ? 0 : typename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchGoods other = (SearchGoods) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (jiangXu == null) {
			if (other.jiangXu != null)
				return false;
		} else if (!jiangXu.equals(other.jiangXu))
			return false;
		if (lock == null) {
			if (other.lock != null)
				return false;
		} else if (!lock.equals(other.lock))
			return false;
		if (maxNum == null) {
			if (other.maxNum != null)
				return false;
		} else if (!maxNum.equals(other.maxNum))
			return false;
		if (maxPrice == null) {
			if (other.maxPrice != null)
				return false;
		} else if (!maxPrice.equals(other.maxPrice))
			return false;
		if (minNum == null) {
			if (other.minNum != null)
				return false;
		} else if (!minNum.equals(other.minNum))
			return false;
		if (minPrice == null) {
			if (other.minPrice != null)
				return false;
		} else if (!minPrice.equals(other.minPrice))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (shengXu == null) {
			if (other.shengXu != null)
				return false;
		} else if (!shengXu.equals(other.shengXu))
			return false;
		if (storename == null) {
			if (other.storename != null)
				return false;
		} else if (!storename.equals(other.storename))
			return false;
		if (typename == null) {
			if (other.typename != null)
				return false;
		} else if (!typename.equals(other.typename))
			return false;
		return true;
	}
}
