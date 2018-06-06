package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ThandUserGood implements Serializable {
	    private Integer id;

	    private String sn;

	    private String goods;
	    
	    private String fullname;
	    
	    private String goodsdescription;
	    
	    private BigDecimal goodsprice;
	    
	    private String goodscoverimg;

	    private Integer num;

	    private String snorders;

	    private String yuanyin;

	    private BigDecimal sum;

	    private Integer status;

	    private Integer shenhestatus;

	    private Integer store;
	    
	    private String storeName;

	    private Integer user;
	    
	    private String username;

	    private Date creadedate;

	    private Date modifidate;

	    private static final long serialVersionUID = 1L;

		public ThandUserGood() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ThandUserGood(Integer id, String sn, String goods, String fullname, String goodsdescription,
				BigDecimal goodsprice, String goodscoverimg, Integer num, String snorders, String yuanyin,
				BigDecimal sum, Integer status, Integer shenhestatus, Integer store, String storeName, Integer user,
				String username, Date creadedate, Date modifidate) {
			super();
			this.id = id;
			this.sn = sn;
			this.goods = goods;
			this.fullname = fullname;
			this.goodsdescription = goodsdescription;
			this.goodsprice = goodsprice;
			this.goodscoverimg = goodscoverimg;
			this.num = num;
			this.snorders = snorders;
			this.yuanyin = yuanyin;
			this.sum = sum;
			this.status = status;
			this.shenhestatus = shenhestatus;
			this.store = store;
			this.storeName = storeName;
			this.user = user;
			this.username = username;
			this.creadedate = creadedate;
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
			this.sn = sn;
		}

		public String getGoods() {
			return goods;
		}

		public void setGoods(String goods) {
			this.goods = goods;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public String getGoodsdescription() {
			return goodsdescription;
		}

		public void setGoodsdescription(String goodsdescription) {
			this.goodsdescription = goodsdescription;
		}

		public BigDecimal getGoodsprice() {
			return goodsprice;
		}

		public void setGoodsprice(BigDecimal goodsprice) {
			this.goodsprice = goodsprice;
		}

		public String getGoodscoverimg() {
			return goodscoverimg;
		}

		public void setGoodscoverimg(String goodscoverimg) {
			this.goodscoverimg = goodscoverimg;
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
			this.snorders = snorders;
		}

		public String getYuanyin() {
			return yuanyin;
		}

		public void setYuanyin(String yuanyin) {
			this.yuanyin = yuanyin;
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

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public Integer getUser() {
			return user;
		}

		public void setUser(Integer user) {
			this.user = user;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "ThandUserGood [id=" + id + ", sn=" + sn + ", goods=" + goods + ", fullname=" + fullname
					+ ", goodsdescription=" + goodsdescription + ", goodsprice=" + goodsprice + ", goodscoverimg="
					+ goodscoverimg + ", num=" + num + ", snorders=" + snorders + ", yuanyin=" + yuanyin + ", sum="
					+ sum + ", status=" + status + ", shenhestatus=" + shenhestatus + ", store=" + store
					+ ", storeName=" + storeName + ", user=" + user + ", username=" + username + ", creadedate="
					+ creadedate + ", modifidate=" + modifidate + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((creadedate == null) ? 0 : creadedate.hashCode());
			result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
			result = prime * result + ((goods == null) ? 0 : goods.hashCode());
			result = prime * result + ((goodscoverimg == null) ? 0 : goodscoverimg.hashCode());
			result = prime * result + ((goodsdescription == null) ? 0 : goodsdescription.hashCode());
			result = prime * result + ((goodsprice == null) ? 0 : goodsprice.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((modifidate == null) ? 0 : modifidate.hashCode());
			result = prime * result + ((num == null) ? 0 : num.hashCode());
			result = prime * result + ((shenhestatus == null) ? 0 : shenhestatus.hashCode());
			result = prime * result + ((sn == null) ? 0 : sn.hashCode());
			result = prime * result + ((snorders == null) ? 0 : snorders.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((store == null) ? 0 : store.hashCode());
			result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
			result = prime * result + ((sum == null) ? 0 : sum.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
			result = prime * result + ((yuanyin == null) ? 0 : yuanyin.hashCode());
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
			ThandUserGood other = (ThandUserGood) obj;
			if (creadedate == null) {
				if (other.creadedate != null)
					return false;
			} else if (!creadedate.equals(other.creadedate))
				return false;
			if (fullname == null) {
				if (other.fullname != null)
					return false;
			} else if (!fullname.equals(other.fullname))
				return false;
			if (goods == null) {
				if (other.goods != null)
					return false;
			} else if (!goods.equals(other.goods))
				return false;
			if (goodscoverimg == null) {
				if (other.goodscoverimg != null)
					return false;
			} else if (!goodscoverimg.equals(other.goodscoverimg))
				return false;
			if (goodsdescription == null) {
				if (other.goodsdescription != null)
					return false;
			} else if (!goodsdescription.equals(other.goodsdescription))
				return false;
			if (goodsprice == null) {
				if (other.goodsprice != null)
					return false;
			} else if (!goodsprice.equals(other.goodsprice))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (modifidate == null) {
				if (other.modifidate != null)
					return false;
			} else if (!modifidate.equals(other.modifidate))
				return false;
			if (num == null) {
				if (other.num != null)
					return false;
			} else if (!num.equals(other.num))
				return false;
			if (shenhestatus == null) {
				if (other.shenhestatus != null)
					return false;
			} else if (!shenhestatus.equals(other.shenhestatus))
				return false;
			if (sn == null) {
				if (other.sn != null)
					return false;
			} else if (!sn.equals(other.sn))
				return false;
			if (snorders == null) {
				if (other.snorders != null)
					return false;
			} else if (!snorders.equals(other.snorders))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (store == null) {
				if (other.store != null)
					return false;
			} else if (!store.equals(other.store))
				return false;
			if (storeName == null) {
				if (other.storeName != null)
					return false;
			} else if (!storeName.equals(other.storeName))
				return false;
			if (sum == null) {
				if (other.sum != null)
					return false;
			} else if (!sum.equals(other.sum))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			if (yuanyin == null) {
				if (other.yuanyin != null)
					return false;
			} else if (!yuanyin.equals(other.yuanyin))
				return false;
			return true;
		}

}
