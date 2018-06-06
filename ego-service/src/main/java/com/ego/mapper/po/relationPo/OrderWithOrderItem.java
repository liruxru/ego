package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ego.mapper.po.OrderItemsWithGoods;
import com.ego.mapper.po.Store;

public class OrderWithOrderItem  implements Serializable {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Integer orderId;
	    private String sn;
	    private Integer user;
	    private BigDecimal sum;
	    private Integer storesId;
	    private Integer status;
	    private Date createdate;
	    private Date modifidate;
	    private Store store;
	    private List<OrderItemsWithGoods> orderItemsWithGoodsList;
		public Integer getOrderId() {
			return orderId;
		}
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
		public String getSn() {
			return sn;
		}
		public void setSn(String sn) {
			this.sn = sn;
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
		public Integer getStoresId() {
			return storesId;
		}
		public void setStoresId(Integer storesId) {
			this.storesId = storesId;
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
		public Store getStore() {
			return store;
		}
		public void setStore(Store store) {
			this.store = store;
		}
		public List<OrderItemsWithGoods> getOrderItemsWithGoodsList() {
			return orderItemsWithGoodsList;
		}
		public void setOrderItemsWithGoodsList(List<OrderItemsWithGoods> orderItemsWithGoodsList) {
			this.orderItemsWithGoodsList = orderItemsWithGoodsList;
		}
		public OrderWithOrderItem() {
			super();
			// TODO Auto-generated constructor stub
		}
		public OrderWithOrderItem(Integer orderId, String sn, Integer user, BigDecimal sum, Integer storesId,
				Integer status, Date createdate, Date modifidate, Store store,
				List<OrderItemsWithGoods> orderItemsWithGoodsList) {
			super();
			this.orderId = orderId;
			this.sn = sn;
			this.user = user;
			this.sum = sum;
			this.storesId = storesId;
			this.status = status;
			this.createdate = createdate;
			this.modifidate = modifidate;
			this.store = store;
			this.orderItemsWithGoodsList = orderItemsWithGoodsList;
		}
		@Override
		public String toString() {
			return "OrderWithOrderItem [orderId=" + orderId + ", sn=" + sn + ", user=" + user + ", sum=" + sum
					+ ", storesId=" + storesId + ", status=" + status + ", createdate=" + createdate + ", modifidate="
					+ modifidate + ", store=" + store + ", orderItemsWithGoodsList=" + orderItemsWithGoodsList + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((orderItemsWithGoodsList == null) ? 0 : orderItemsWithGoodsList.hashCode());
			result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
			result = prime * result + ((modifidate == null) ? 0 : modifidate.hashCode());
			result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
			result = prime * result + ((sn == null) ? 0 : sn.hashCode());
			result = prime * result + ((store == null) ? 0 : store.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((storesId == null) ? 0 : storesId.hashCode());
			result = prime * result + ((sum == null) ? 0 : sum.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
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
			OrderWithOrderItem other = (OrderWithOrderItem) obj;
			if (orderItemsWithGoodsList == null) {
				if (other.orderItemsWithGoodsList != null)
					return false;
			} else if (!orderItemsWithGoodsList.equals(other.orderItemsWithGoodsList))
				return false;
			if (createdate == null) {
				if (other.createdate != null)
					return false;
			} else if (!createdate.equals(other.createdate))
				return false;
			if (modifidate == null) {
				if (other.modifidate != null)
					return false;
			} else if (!modifidate.equals(other.modifidate))
				return false;
			if (orderId == null) {
				if (other.orderId != null)
					return false;
			} else if (!orderId.equals(other.orderId))
				return false;
			if (sn == null) {
				if (other.sn != null)
					return false;
			} else if (!sn.equals(other.sn))
				return false;
			if (store == null) {
				if (other.store != null)
					return false;
			} else if (!store.equals(other.store))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (storesId == null) {
				if (other.storesId != null)
					return false;
			} else if (!storesId.equals(other.storesId))
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
			return true;
		}
	    
}
