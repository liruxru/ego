package com.ego.document;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="goodscollect")
public class GoodsCollect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Indexed(unique=true)
	private String _id;
    private	String goodsId;
	private List<Integer> userIds;//所有收藏人的id
	private Long countPeopleNum;//收藏人数量
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public Long getCountPeopleNum() {
		return countPeopleNum;
	}
	public GoodsCollect(String _id, String goodsId, List<Integer> userIds, Long countPeopleNum) {
		super();
		this._id = _id;
		this.goodsId = goodsId;
		this.userIds = userIds;
		this.countPeopleNum = countPeopleNum;
	}
	public GoodsCollect() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GoodsCollect [_id=" + _id + ", goodsId=" + goodsId + ", userIds=" + userIds + ", countPeopleNum="
				+ countPeopleNum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((countPeopleNum == null) ? 0 : countPeopleNum.hashCode());
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		result = prime * result + ((userIds == null) ? 0 : userIds.hashCode());
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
		GoodsCollect other = (GoodsCollect) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (countPeopleNum == null) {
			if (other.countPeopleNum != null)
				return false;
		} else if (!countPeopleNum.equals(other.countPeopleNum))
			return false;
		if (goodsId == null) {
			if (other.goodsId != null)
				return false;
		} else if (!goodsId.equals(other.goodsId))
			return false;
		if (userIds == null) {
			if (other.userIds != null)
				return false;
		} else if (!userIds.equals(other.userIds))
			return false;
		return true;
	}
	public void setCountPeopleNum(Long countPeopleNum) {
		this.countPeopleNum = countPeopleNum;
	}
	
	
}
