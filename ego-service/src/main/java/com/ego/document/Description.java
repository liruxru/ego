package com.ego.document;

import java.io.Serializable;
import java.util.Date;

public class Description implements Serializable{
	private static final long serialVersionUID = 1L;
	private String _id;
	private String txt;
	private Date createdate;
	public Description(String _id, String txt, Date createdate) {
		super();
		this._id = _id;
		this.txt = txt;
		this.createdate = createdate;
	}
	public Description() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Date getCreatedate() {
		return createdate;
	}
	@Override
	public String toString() {
		return "id=" + _id + ",txt=" + txt + ",createdate=" + createdate.getTime();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
		result = prime * result + ((txt == null) ? 0 : txt.hashCode());
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
		Description other = (Description) obj;
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
		if (txt == null) {
			if (other.txt != null)
				return false;
		} else if (!txt.equals(other.txt))
			return false;
		return true;
	}
	
	
	
}
