package com.ego.mapper.po;

import java.io.Serializable;

public class Img implements Serializable {
    private Integer id;

    private String uuidname;

    private String filename;

    private String goods;

    private Integer stores;

    private static final long serialVersionUID = 1L;
    

    public Img() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	public Img(Integer id, String uuidname, String filename, String goods, Integer stores) {
		super();
		this.id = id;
		this.uuidname = uuidname;
		this.filename = filename;
		this.goods = goods;
		this.stores = stores;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuidname() {
        return uuidname;
    }

    public void setUuidname(String uuidname) {
        this.uuidname = uuidname == null ? null : uuidname.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public Integer getStores() {
        return stores;
    }

    public void setStores(Integer stores) {
        this.stores = stores;
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
        Img other = (Img) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUuidname() == null ? other.getUuidname() == null : this.getUuidname().equals(other.getUuidname()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getGoods() == null ? other.getGoods() == null : this.getGoods().equals(other.getGoods()))
            && (this.getStores() == null ? other.getStores() == null : this.getStores().equals(other.getStores()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUuidname() == null) ? 0 : getUuidname().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getGoods() == null) ? 0 : getGoods().hashCode());
        result = prime * result + ((getStores() == null) ? 0 : getStores().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuidname=").append(uuidname);
        sb.append(", filename=").append(filename);
        sb.append(", goods=").append(goods);
        sb.append(", stores=").append(stores);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}