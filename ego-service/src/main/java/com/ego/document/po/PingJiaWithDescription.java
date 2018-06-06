package com.ego.document.po;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

import com.ego.document.Description;

public class PingJiaWithDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _id;			//评价的id
	private Integer orderItemId;//订单条目id
	private Integer userId;	//用户id
	private String username;//用户名
	private String goodsId;	//商品id
	private String goodsfullname;//商品全名
	private Date createdate;  //第一次评价日期
	private Integer code;  //是否可以回复
	private LinkedHashMap<String,String> pin_hui;//评价加回复
	private LinkedHashMap<Description,Description> pin_hui_Entiy;//评价和回复的实体
	public PingJiaWithDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PingJiaWithDescription(String _id, Integer orderItemId, Integer userId, String username, String goodsId,
			String goodsfullname, Date createdate, Integer code, LinkedHashMap<String, String> pin_hui,
			LinkedHashMap<Description, Description> pin_hui_Entiy) {
		super();
		this._id = _id;
		this.orderItemId = orderItemId;
		this.userId = userId;
		this.username = username;
		this.goodsId = goodsId;
		this.goodsfullname = goodsfullname;
		this.createdate = createdate;
		this.code = code;
		this.pin_hui = pin_hui;
		this.pin_hui_Entiy = pin_hui_Entiy;
	}
	@Override
	public String toString() {
		return "PingJiaWithDescription [_id=" + _id + ", orderItemId=" + orderItemId + ", userId=" + userId
				+ ", username=" + username + ", goodsId=" + goodsId + ", goodsfullname=" + goodsfullname
				+ ", createdate=" + createdate + ", code=" + code + ", pin_hui=" + pin_hui + ", pin_hui_Entiy="
				+ pin_hui_Entiy + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		result = prime * result + ((goodsfullname == null) ? 0 : goodsfullname.hashCode());
		result = prime * result + ((orderItemId == null) ? 0 : orderItemId.hashCode());
		result = prime * result + ((pin_hui == null) ? 0 : pin_hui.hashCode());
		result = prime * result + ((pin_hui_Entiy == null) ? 0 : pin_hui_Entiy.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		PingJiaWithDescription other = (PingJiaWithDescription) obj;
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
		if (goodsfullname == null) {
			if (other.goodsfullname != null)
				return false;
		} else if (!goodsfullname.equals(other.goodsfullname))
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
		if (pin_hui_Entiy == null) {
			if (other.pin_hui_Entiy != null)
				return false;
		} else if (!pin_hui_Entiy.equals(other.pin_hui_Entiy))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsfullname() {
		return goodsfullname;
	}
	public void setGoodsfullname(String goodsfullname) {
		this.goodsfullname = goodsfullname;
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
	public LinkedHashMap<Description, Description> getPin_hui_Entiy() {
		return pin_hui_Entiy;
	}
	public void setPin_hui_Entiy(LinkedHashMap<Description, Description> pin_hui_Entiy) {
		this.pin_hui_Entiy = pin_hui_Entiy;
	}
	
}
