package com.ego.mapper.po.relationPo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 管理员首页需要的信息
 * @author 秦健
 *
 */
 
public class AdminMessage implements Serializable{
	//已完成订单的总金额
	private BigDecimal totalSum;
	//完成的订单总数
	private Integer    totalNum;
	//待发货的订单
	private Integer    uncheckNum;
	//评价留言的总数
	private Integer    messageNum;
	//库存警告的条数
	private Integer    warn;
	//没有回复的留言数量
	private Integer   noAnswerNum;

	public AdminMessage(BigDecimal totalSum, Integer totalNum, Integer uncheckNum, Integer messageNum, Integer warn,
			Integer noAnswerNum) {
		super();
		this.totalSum = totalSum;
		this.totalNum = totalNum;
		this.uncheckNum = uncheckNum;
		this.messageNum = messageNum;
		this.warn = warn;
		this.noAnswerNum = noAnswerNum;
	}

	public AdminMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getUncheckNum() {
		return uncheckNum;
	}

	public void setUncheckNum(Integer uncheckNum) {
		this.uncheckNum = uncheckNum;
	}

	public Integer getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(Integer messageNum) {
		this.messageNum = messageNum;
	}

	public Integer getWarn() {
		return warn;
	}

	public void setWarn(Integer warn) {
		this.warn = warn;
	}

	public Integer getNoAnswerNum() {
		return noAnswerNum;
	}

	public void setNoAnswerNum(Integer noAnswerNum) {
		this.noAnswerNum = noAnswerNum;
	}

	@Override
	public String toString() {
		return "AdminMessage [totalSum=" + totalSum + ", totalNum=" + totalNum + ", uncheckNum=" + uncheckNum
				+ ", messageNum=" + messageNum + ", warn=" + warn + ", noAnswerNum=" + noAnswerNum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageNum == null) ? 0 : messageNum.hashCode());
		result = prime * result + ((noAnswerNum == null) ? 0 : noAnswerNum.hashCode());
		result = prime * result + ((totalNum == null) ? 0 : totalNum.hashCode());
		result = prime * result + ((totalSum == null) ? 0 : totalSum.hashCode());
		result = prime * result + ((uncheckNum == null) ? 0 : uncheckNum.hashCode());
		result = prime * result + ((warn == null) ? 0 : warn.hashCode());
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
		AdminMessage other = (AdminMessage) obj;
		if (messageNum == null) {
			if (other.messageNum != null)
				return false;
		} else if (!messageNum.equals(other.messageNum))
			return false;
		if (noAnswerNum == null) {
			if (other.noAnswerNum != null)
				return false;
		} else if (!noAnswerNum.equals(other.noAnswerNum))
			return false;
		if (totalNum == null) {
			if (other.totalNum != null)
				return false;
		} else if (!totalNum.equals(other.totalNum))
			return false;
		if (totalSum == null) {
			if (other.totalSum != null)
				return false;
		} else if (!totalSum.equals(other.totalSum))
			return false;
		if (uncheckNum == null) {
			if (other.uncheckNum != null)
				return false;
		} else if (!uncheckNum.equals(other.uncheckNum))
			return false;
		if (warn == null) {
			if (other.warn != null)
				return false;
		} else if (!warn.equals(other.warn))
			return false;
		return true;
	}

}
