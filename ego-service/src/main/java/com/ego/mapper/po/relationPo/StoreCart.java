package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.util.List;



public class StoreCart implements  Serializable {

	private static final long serialVersionUID = 1L;
	private Integer storeId;//店铺id
	private String storeName;//店铺名
	private List<CartWithGoods> cartWihtGoodsList;//店铺购物车集合
	public StoreCart(Integer storeId, String storeName, List<CartWithGoods> cartWihtGoodsList) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.cartWihtGoodsList = cartWihtGoodsList;
	}
	public StoreCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public List<CartWithGoods> getCartWihtGoodsList() {
		return cartWihtGoodsList;
	}
	public void setCartWihtGoodsList(List<CartWithGoods> cartWihtGoodsList) {
		this.cartWihtGoodsList = cartWihtGoodsList;
	}
	@Override
	public String toString() {
		return "StoreCart [storeId=" + storeId + ", storeName=" + storeName + ", cartWihtGoodsList=" + cartWihtGoodsList
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartWihtGoodsList == null) ? 0 : cartWihtGoodsList.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
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
		StoreCart other = (StoreCart) obj;
		if (cartWihtGoodsList == null) {
			if (other.cartWihtGoodsList != null)
				return false;
		} else if (!cartWihtGoodsList.equals(other.cartWihtGoodsList))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		return true;
	}
	
	
	
	
}
