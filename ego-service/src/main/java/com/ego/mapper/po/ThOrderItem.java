package com.ego.mapper.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ThOrderItem implements Serializable {
    private Integer id;

    private String sn;

    private String goods;

    private Integer num;

    private String snorders;

    private String yuanyin;

    private BigDecimal sum;

    private Integer status;

    private Integer shenhestatus;

    private Integer store;

    private Integer user;

    private Date creadedate;

    private Date modifidate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSnorders() {
        return snorders;
    }

    public void setSnorders(String snorders) {
        this.snorders = snorders == null ? null : snorders.trim();
    }

    public String getYuanyin() {
        return yuanyin;
    }

    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin == null ? null : yuanyin.trim();
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShenhestatus() {
        return shenhestatus;
    }

    public void setShenhestatus(Integer shenhestatus) {
        this.shenhestatus = shenhestatus;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getCreadedate() {
        return creadedate;
    }

    public void setCreadedate(Date creadedate) {
        this.creadedate = creadedate;
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
        ThOrderItem other = (ThOrderItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSn() == null ? other.getSn() == null : this.getSn().equals(other.getSn()))
            && (this.getGoods() == null ? other.getGoods() == null : this.getGoods().equals(other.getGoods()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getSnorders() == null ? other.getSnorders() == null : this.getSnorders().equals(other.getSnorders()))
            && (this.getYuanyin() == null ? other.getYuanyin() == null : this.getYuanyin().equals(other.getYuanyin()))
            && (this.getSum() == null ? other.getSum() == null : this.getSum().equals(other.getSum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getShenhestatus() == null ? other.getShenhestatus() == null : this.getShenhestatus().equals(other.getShenhestatus()))
            && (this.getStore() == null ? other.getStore() == null : this.getStore().equals(other.getStore()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getCreadedate() == null ? other.getCreadedate() == null : this.getCreadedate().equals(other.getCreadedate()))
            && (this.getModifidate() == null ? other.getModifidate() == null : this.getModifidate().equals(other.getModifidate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSn() == null) ? 0 : getSn().hashCode());
        result = prime * result + ((getGoods() == null) ? 0 : getGoods().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getSnorders() == null) ? 0 : getSnorders().hashCode());
        result = prime * result + ((getYuanyin() == null) ? 0 : getYuanyin().hashCode());
        result = prime * result + ((getSum() == null) ? 0 : getSum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getShenhestatus() == null) ? 0 : getShenhestatus().hashCode());
        result = prime * result + ((getStore() == null) ? 0 : getStore().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getCreadedate() == null) ? 0 : getCreadedate().hashCode());
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
        sb.append(", sn=").append(sn);
        sb.append(", goods=").append(goods);
        sb.append(", num=").append(num);
        sb.append(", snorders=").append(snorders);
        sb.append(", yuanyin=").append(yuanyin);
        sb.append(", sum=").append(sum);
        sb.append(", status=").append(status);
        sb.append(", shenhestatus=").append(shenhestatus);
        sb.append(", store=").append(store);
        sb.append(", user=").append(user);
        sb.append(", creadedate=").append(creadedate);
        sb.append(", modifidate=").append(modifidate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public ThOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThOrderItem(Integer id, String sn, String goods, Integer num, String snorders, String yuanyin,
			BigDecimal sum, Integer status, Integer shenhestatus, Integer store, Integer user, Date creadedate,
			Date modifidate) {
		super();
		this.id = id;
		this.sn = sn;
		this.goods = goods;
		this.num = num;
		this.snorders = snorders;
		this.yuanyin = yuanyin;
		this.sum = sum;
		this.status = status;
		this.shenhestatus = shenhestatus;
		this.store = store;
		this.user = user;
		this.creadedate = creadedate;
		this.modifidate = modifidate;
	}
    
}