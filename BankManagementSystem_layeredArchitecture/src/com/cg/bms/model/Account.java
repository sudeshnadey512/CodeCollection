package com.cg.bms.model;

import java.io.Serializable;

public class Account implements Serializable {

	private Long custId;
	private String accountNo;
	private String ifscCode;
	private Double balance;
	private String customerName;
	private String city;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(Long custId, String accountNo, String ifscCode, Double balance, String customerName, String city) {
		super();
		this.custId = custId;
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.balance = balance;
		this.customerName = customerName;
		this.city = city;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
