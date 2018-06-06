package com.ego.mapper.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cart implements Serializable {
    private Integer id;

    private Integer user;

    private String goods;

    private BigDecimal price;

    private Integer num;

    private String coverimg;

    private String goodname;

    private Date createdate;

    private Date modifidate;

    private static final long serialVersionUID = 1L;
    public Cart() {
		// TODO Auto-generated constructor stub
	}
    

    public Cart(Integer id, Integer user, String goods, BigDecimal price, Integer num, String coverimg, String goodname,
			Date createdate, Date modifidate) {
		super();
		this.id = id;
		this.user = user;
		this.goods = goods;
		this.price = price;
		this.num = num;
		this.coverimg = coverimg;
		this.goodname = goodname;
		this.createdate = createdate;
		this.modifidate = modifidate;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg == null ? null : coverimg.trim();
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname == null ? null : goodname.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifidate() {
        return modifidate;
    }

    public void setModifidate(Date modifidate) {
        this.modifidate = modifidate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Cart other = (Cart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getGoods() == null ? other.getGoods() == null : this.getGoods().equals(other.getGoods()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getCoverimg() == null ? other.getCoverimg() == null : this.getCoverimg().equals(other.getCoverimg()))
            && (this.getGoodname() == null ? other.getGoodname() == null : this.getGoodname().equals(other.getGoodname()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getModifidate() == null ? other.getModifidate() == null : this.getModifidate().equals(other.getModifidate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getGoods() == null) ? 0 : getGoods().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getCoverimg() == null) ? 0 : getCoverimg().hashCode());
        result = prime * result + ((getGoodname() == null) ? 0 : getGoodname().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        result = prime * result + ((getModifidate() == null) ? 0 : getModifidate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", goods=").append(goods);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", coverimg=").append(coverimg);
        sb.append(", goodname=").append(goodname);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifidate=").append(modifidate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}