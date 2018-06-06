package com.ego.document.po;

import java.io.Serializable;
import java.util.List;

import com.ego.document.Goods;

public class GoodCollectWithGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _id;
    private	String goodsId;
	private List<Integer> userIds;//所有收藏人的id
	private Long countPeopleNum;//收藏人数量
	private Goods goods;
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
	public void setCountPeopleNum(Long countPeopleNum) {
		this.countPeopleNum = countPeopleNum;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "GoodCollectWithGoods [_id=" + _id + ", goodsId=" + goodsId + ", userIds=" + userIds
				+ ", countPeopleNum=" + countPeopleNum + ", goods=" + goods + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((countPeopleNum == null) ? 0 : countPeopleNum.hashCode());
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
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
		GoodCollectWithGoods other = (GoodCollectWithGoods) obj;
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
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.equals(other.goods))
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
	public GoodCollectWithGoods(String _id, String goodsId, List<Integer> userIds, Long countPeopleNum, Goods goods) {
		super();
		this._id = _id;
		this.goodsId = goodsId;
		this.userIds = userIds;
		this.countPeopleNum = countPeopleNum;
		this.goods = goods;
	}
	public GoodCollectWithGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
