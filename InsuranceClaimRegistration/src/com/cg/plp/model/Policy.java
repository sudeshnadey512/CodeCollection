package com.cg.plp.model;

import java.io.Serializable;

public class Policy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long policyNumber;
	private Double policyPremium;
	private Long accountNumber;
	private String policyType;

	public Policy() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Policy [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", accountNumber="
				+ accountNumber + ", policyType=" + policyType + "]";
	}


	public Policy(Long policyNumber, Double policyPremium, Long accountNumber, String policyType) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.policyType = policyType;
	}



	public Long getPolicyNumber() {
		return policyNumber;
	}



	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}



	public Double getPolicyPremium() {
		return policyPremium;
	}



	public void setPolicyPremium(Double policyPremium) {
		this.policyPremium = policyPremium;
	}



	public Long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getPolicyType() {
		return policyType;
	}



	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}



}
