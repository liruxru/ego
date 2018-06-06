package com.ego.document;

import java.io.Serializable;
import java.util.Date;

public class CollectStores implements Serializable {
    private Integer _id;

    private Integer stores;

    private String storesimg;

    private String storesname;

    private Integer users;

    private Date createdate;

    private Date moditidate;

    private static final long serialVersionUID = 1L;

	public CollectStores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectStores(Integer _id, Integer stores, String storesimg, String storesname, Integer users,
			Date createdate, Date moditidate) {
		super();
		this._id = _id;
		this.stores = stores;
		this.storesimg = storesimg;
		this.storesname = storesname;
		this.users = users;
		this.createdate = createdate;
		this.moditidate = moditidate;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public Integer getStores() {
		return stores;
	}

	public void setStores(Integer stores) {
		this.stores = stores;
	}

	public String getStoresimg() {
		return storesimg;
	}

	public void setStoresimg(String storesimg) {
		this.storesimg = storesimg;
	}

	public String getStoresname() {
		return storesname;
	}

	public void setStoresname(String storesname) {
		this.storesname = storesname;
	}

	public Integer getUsers() {
		return users;
	}

	public void setUsers(Integer users) {
		this.users = users;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModitidate() {
		return moditidate;
	}

	public void setModitidate(Date moditidate) {
		this.moditidate = moditidate;
	}

	@Override
	public String toString() {
		return "CollectStores [_id=" + _id + ", stores=" + stores + ", storesimg=" + storesimg + ", storesname="
				+ storesname + ", users=" + users + ", createdate=" + createdate + ", moditidate=" + moditidate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((moditidate == null) ? 0 : moditidate.hashCode());
		result = prime * result + ((stores == null) ? 0 : stores.hashCode());
		result = prime * result + ((storesimg == null) ? 0 : storesimg.hashCode());
		result = prime * result + ((storesname == null) ? 0 : storesname.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		CollectStores other = (CollectStores) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (createdate == null) {
			if (other.createdate != null)
				return false;
		} else if (!createdate.equals(other.createdate))
			return false;
		if (moditidate == null) {
			if (other.moditidate != null)
				return false;
		} else if (!moditidate.equals(other.moditidate))
			return false;
		if (stores == null) {
			if (other.stores != null)
				return false;
		} else if (!stores.equals(other.stores))
			return false;
		if (storesimg == null) {
			if (other.storesimg != null)
				return false;
		} else if (!storesimg.equals(other.storesimg))
			return false;
		if (storesname == null) {
			if (other.storesname != null)
				return false;
		} else if (!storesname.equals(other.storesname))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
    

}