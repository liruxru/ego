package com.ego.mapper.po;

import java.io.Serializable;
import java.util.Date;

public class ThOrder implements Serializable {
    private Integer id;

    private String sn;

    private Integer order;

    private Integer store;

    private Integer user;

    private Integer sum;

    private Integer shenhestatus;

    private Integer status;

    private Date createdate;

    private Date modifidate;

    private static final long serialVersionUID = 1L;
    

    public ThOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThOrder(Integer id, String sn, Integer order, Integer store, Integer user, Integer sum, Integer shenhestatus,
			Integer status, Date createdate, Date modifidate) {
		super();
		this.id = id;
		this.sn = sn;
		this.order = order;
		this.store = store;
		this.user = user;
		this.sum = sum;
		this.shenhestatus = shenhestatus;
		this.status = status;
		this.createdate = createdate;
		this.modifidate = modifidate;
	}

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getShenhestatus() {
        return shenhestatus;
    }

    public void setShenhestatus(Integer shenhestatus) {
        this.shenhestatus = shenhestatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        ThOrder other = (ThOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSn() == null ? other.getSn() == null : this.getSn().equals(other.getSn()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
            && (this.getStore() == null ? other.getStore() == null : this.getStore().equals(other.getStore()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getSum() == null ? other.getSum() == null : this.getSum().equals(other.getSum()))
            && (this.getShenhestatus() == null ? other.getShenhestatus() == null : this.getShenhestatus().equals(other.getShenhestatus()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()))
            && (this.getModifidate() == null ? other.getModifidate() == null : this.getModifidate().equals(other.getModifidate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSn() == null) ? 0 : getSn().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
        result = prime * result + ((getStore() == null) ? 0 : getStore().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getSum() == null) ? 0 : getSum().hashCode());
        result = prime * result + ((getShenhestatus() == null) ? 0 : getShenhestatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", sn=").append(sn);
        sb.append(", order=").append(order);
        sb.append(", store=").append(store);
        sb.append(", user=").append(user);
        sb.append(", sum=").append(sum);
        sb.append(", shenhestatus=").append(shenhestatus);
        sb.append(", status=").append(status);
        sb.append(", createdate=").append(createdate);
        sb.append(", modifidate=").append(modifidate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}