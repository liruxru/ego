package com.ego.document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document()
public class LiuLan implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _id;
	private List<Goods> goodList;
	private String userId;
	private Date createDate;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<Goods> getGoodList() {
		return goodList;
	}
	public void setGoodList(List<Goods> goodList) {
		this.goodList = goodList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public LiuLan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LiuLan(String _id, List<Goods> goodList, String userId, Date createDate) {
		super();
		this._id = _id;
		this.goodList = goodList;
		this.userId = userId;
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "LiuLan [_id=" + _id + ", goodList=" + goodList + ", userId=" + userId + ", createDate=" + createDate
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((goodList == null) ? 0 : goodList.hashCode());
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
		LiuLan other = (LiuLan) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (goodList == null) {
			if (other.goodList != null)
				return false;
		} else if (!goodList.equals(other.goodList))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
