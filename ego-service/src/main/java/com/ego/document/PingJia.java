package com.ego.document;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="pingjia")
public class PingJia implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Indexed(unique=true)
	private String _id;			//评价的id
	private Integer orderItemId;//订单条目id
	private Integer userId;	//用户id
	private String goodsId;	//商品id
	private Date createdate;  //第一次评价日期
	private Integer code;  //是否可以回复
	private LinkedHashMap<String,String> pin_hui;//评价加回复
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public LinkedHashMap<String, String> getPin_hui() {
		return pin_hui;
	}
	public void setPin_hui(LinkedHashMap<String, String> pin_hui) {
		this.pin_hui = pin_hui;
	}
	public PingJia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PingJia(String _id, Integer orderItemId, Integer userId, String goodsId, Date createdate, Integer code,
			LinkedHashMap<String, String> pin_hui) {
		super();
		this._id = _id;
		this.orderItemId = orderItemId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.createdate = createdate;
		this.code = code;
		this.pin_hui = pin_hui;
	}
	@Override
	public String toString() {
		return "PingJia [_id=" + _id + ", orderItemId=" + orderItemId + ", userId=" + userId + ", goodsId=" + goodsId
				+ ", createdate=" + createdate + ", code=" + code + ", pin_hui=" + pin_hui + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		result = prime * result + ((orderItemId == null) ? 0 : orderItemId.hashCode());
		result = prime * result + ((pin_hui == null) ? 0 : pin_hui.hashCode());
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
		PingJia other = (PingJia) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (goodsId == null) {
			if (other.goodsId != null)
				return false;
		} else if (!goodsId.equals(other.goodsId))
			return false;
		if (orderItemId == null) {
			if (other.orderItemId != null)
				return false;
		} else if (!orderItemId.equals(other.orderItemId))
			return false;
		if (pin_hui == null) {
			if (other.pin_hui != null)
				return false;
		} else if (!pin_hui.equals(other.pin_hui))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
}
