package com.ego.mapper.po;

import java.io.Serializable;

public class Status implements Serializable {
    private Integer id;

    private String statusname;

    private static final long serialVersionUID = 1L;
    public Status() {
		// TODO Auto-generated constructor stub
	}
    

    public Status(Integer id, String statusname) {
		super();
		this.id = id;
		this.statusname = statusname;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname == null ? null : statusname.trim();
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
        Status other = (Status) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStatusname() == null ? other.getStatusname() == null : this.getStatusname().equals(other.getStatusname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStatusname() == null) ? 0 : getStatusname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", statusname=").append(statusname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}