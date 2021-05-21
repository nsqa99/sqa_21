/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author nsqa
 */
public class ElecInfo {

	public ElecInfo() {
	}
	
	

	public ElecInfo(long id, String userFullName, String userAddr, String userPhoneNum, String month,
			int consumedAmount, float price, String paymentStatus, long userid, long userAreaId) {
		this.id = id;
		this.userFullName = userFullName;
		this.userAddr = userAddr;
		this.userPhoneNum = userPhoneNum;
		this.month = month;
		this.consumedAmount = consumedAmount;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.userid = userid;
		this.userAreaId = userAreaId;
	}



	private long id;
	private String userFullName;
	private String userAddr;
	private String userPhoneNum;
	private String month;
	private int consumedAmount;
	private float price;
	private String paymentStatus;
	private Date modifiedAt;
	private long userid;
	private long userAreaId;

	public long getUserAreaId() {
		return userAreaId;
	}

	public void setUserAreaId(long userAreaId) {
		this.userAreaId = userAreaId;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getConsumedAmount() {
		return consumedAmount;
	}

	public void setConsumedAmount(int consumedAmount) {
		this.consumedAmount = consumedAmount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	@Override
	public boolean equals(Object obj) {
		ElecInfo other = (ElecInfo) obj;
		return (this.id == other.id && this.consumedAmount == other.consumedAmount && this.month.equals(other.month)
				&& this.paymentStatus.equals(other.paymentStatus) && this.price == other.price
				&& this.userAddr.equals(other.userAddr) && this.userAreaId == other.userAreaId
				&& this.userid == other.userid
				&& this.userFullName.equals(other.userFullName) && this.userPhoneNum.equals(other.userPhoneNum));
	}

}
