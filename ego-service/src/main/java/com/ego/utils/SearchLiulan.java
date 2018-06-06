package com.ego.utils;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SearchLiulan implements Serializable {
	private static final long serialVersionUID = -3407558842658529537L;
	private String _id;
	private String userId;
	private int pageSize; // 页面大小
	private int currentPage; // 当前页

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public SearchLiulan(String _id, String userId, int pageSize, int currentPage) {
		super();
		this._id = _id;
		this.userId = userId;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public SearchLiulan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SearchLiulan [_id=" + _id + ", userId=" + userId + ", pageSize=" + pageSize + ", currentPage="
				+ currentPage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + currentPage;
		result = prime * result + pageSize;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		SearchLiulan other = (SearchLiulan) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
