package com.ego.mapper.po;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ego.document.Goods;

public class OrderItemsWithGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// 订单详情id
	private String goods; // 商品id
	private Integer num;// 购买数量
	private BigDecimal price;// 购买价格
	private Goods goodsAll;

	public OrderItemsWithGoods() {
		// TODO Auto-generated constructor stub
	}

	public OrderItemsWithGoods(Integer id, String goods, Integer num, BigDecimal price, Goods goodsAll) {
		super();
		this.id = id;
		this.goods = goods;
		this.num = num;
		this.price = price;
		this.goodsAll = goodsAll;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Goods getGoodsAll() {
		return goodsAll;
	}

	public void setGoodsAll(Goods goodsAll) {
		this.goodsAll = goodsAll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderItemsWithGoods [id=" + id + ", goods=" + goods + ", num=" + num + ", price=" + price
				+ ", goodsAll=" + goodsAll + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		result = prime * result + ((goodsAll == null) ? 0 : goodsAll.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		OrderItemsWithGoods other = (OrderItemsWithGoods) obj;
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.equals(other.goods))
			return false;
		if (goodsAll == null) {
			if (other.goodsAll != null)
				return false;
		} else if (!goodsAll.equals(other.goodsAll))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
