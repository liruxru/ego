package com.ego.mapper.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class Order implements Serializable {
    private Integer id;

    private String sn;

    private Integer user;

    private BigDecimal sum;

    private Integer stores;

    private Integer sendstatus;

    private Integer getstatus;

    private Integer status;

    private Date modifidate;

    private Date createdate;

    private static final long serialVersionUID = 1L;
    
  

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, String sn, Integer user, BigDecimal sum, Integer stores, Integer sendstatus,
			Integer getstatus, Integer status, Date modifidate, Date createdate) {
		super();
		this.id = id;
		this.sn = sn;
		this.user = user;
		this.sum = sum;
		this.stores = stores;
		this.sendstatus = sendstatus;
		this.getstatus = getstatus;
		this.status = status;
		this.modifidate = modifidate;
		this.createdate = createdate;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getStores() {
        return stores;
    }

    public void setStores(Integer stores) {
        this.stores = stores;
    }

    public Integer getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(Integer sendstatus) {
        this.sendstatus = sendstatus;
    }

    public Integer getGetstatus() {
        return getstatus;
    }

    public void setGetstatus(Integer getstatus) {
        this.getstatus = getstatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getModifidate() {
        return modifidate;
    }

    public void setModifidate(Date modifidate) {
        this.modifidate = modifidate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSn() == null ? other.getSn() == null : this.getSn().equals(other.getSn()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getSum() == null ? other.getSum() == null : this.getSum().equals(other.getSum()))
            && (this.getStores() == null ? other.getStores() == null : this.getStores().equals(other.getStores()))
            && (this.getSendstatus() == null ? other.getSendstatus() == null : this.getSendstatus().equals(other.getSendstatus()))
            && (this.getGetstatus() == null ? other.getGetstatus() == null : this.getGetstatus().equals(other.getGetstatus()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getModifidate() == null ? other.getModifidate() == null : this.getModifidate().equals(other.getModifidate()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSn() == null) ? 0 : getSn().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getSum() == null) ? 0 : getSum().hashCode());
        result = prime * result + ((getStores() == null) ? 0 : getStores().hashCode());
        result = prime * result + ((getSendstatus() == null) ? 0 : getSendstatus().hashCode());
        result = prime * result + ((getGetstatus() == null) ? 0 : getGetstatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getModifidate() == null) ? 0 : getModifidate().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
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
        sb.append(", user=").append(user);
        sb.append(", sum=").append(sum);
        sb.append(", stores=").append(stores);
        sb.append(", sendstatus=").append(sendstatus);
        sb.append(", getstatus=").append(getstatus);
        sb.append(", status=").append(status);
        sb.append(", modifidate=").append(modifidate);
        sb.append(", createdate=").append(createdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}