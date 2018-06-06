package com.ego.document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="collectgoods")
public class CollectGoods implements Serializable {
 
	private static final long serialVersionUID = 1L;
	@Id
	@Indexed(unique=true)
	private String _id;
    private Integer user;
	private List<String> goodsIds;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public List<String> getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}
	public CollectGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectGoods(String _id, Integer user, List<String> goodsIds) {
		super();
		this._id = _id;
		this.user = user;
		this.goodsIds = goodsIds;
	}
	@Override
	public String toString() {
		return "CollectGoods [_id=" + _id + ", user=" + user + ", GoodsIds=" + goodsIds + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsIds == null) ? 0 : goodsIds.hashCode());
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CollectGoods other = (CollectGoods) obj;
		if (goodsIds == null) {
			if (other.goodsIds != null)
				return false;
		} else if (!goodsIds.equals(other.goodsIds))
			return false;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
 
  
}