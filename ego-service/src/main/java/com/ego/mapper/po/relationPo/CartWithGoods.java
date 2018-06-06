package com.ego.mapper.po.relationPo;

import java.io.Serializable;

import com.ego.document.Goods;
import com.ego.mapper.po.Cart;

public class CartWithGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private Goods goods;
	private Cart cart;
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public CartWithGoods(Goods goods, Cart cart) {
		super();
		this.goods = goods;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CartWithGoods [goods=" + goods + ", cart=" + cart + "]";
	}

	public CartWithGoods() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
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
		CartWithGoods other = (CartWithGoods) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.equals(other.goods))
			return false;
		return true;
	}
	

}
