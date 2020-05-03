package com.cg.plp.model;

import java.io.Serializable;

public class Claim implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long claimNumber;
private String claimReason;
private String accidentLocationStreet;
private String accidentCity;
private String accidentState;
private Integer accidentZip;
private String claimType;
private Long policyNumber;
private Long accountNumber;
	
public Claim() {
	
}

public Claim(Long claimNumber, String claimReason, String accidentLocationStreet, String accidentCity,
		String accidentState, Integer accidentZip, String claimType, Long policyNumber) {
	super();
	this.claimNumber = claimNumber;
	this.claimReason = claimReason;
	this.accidentLocationStreet = accidentLocationStreet;
	this.accidentCity = accidentCity;
	this.accidentState = accidentState;
	this.accidentZip = accidentZip;
	this.claimType = claimType;
	this.policyNumber = policyNumber;
}



public Claim(String claimReason, String accidentLocationStreet, String accidentCity, String accidentState,
		Integer accidentZip, String claimType, Long policyNumber) {
	super();
	this.claimReason = claimReason;
	this.accidentLocationStreet = accidentLocationStreet;
	this.accidentCity = accidentCity;
	this.accidentState = accidentState;
	this.accidentZip = accidentZip;
	this.claimType = claimType;
	this.policyNumber = policyNumber;
}

public Long getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(Long accountNumber) {
	this.accountNumber = accountNumber;
}

public Long getClaimNumber() {
	return claimNumber;
}

public void setClaimNumber(Long claimNumber) {
	this.claimNumber = claimNumber;
}

public String getClaimReason() {
	return claimReason;
}

public void setClaimReason(String claimReason) {
	this.claimReason = claimReason;
}

public String getAccidentLocationStreet() {
	return accidentLocationStreet;
}

public void setAccidentLocationStreet(String accidentLocationStreet) {
	this.accidentLocationStreet = accidentLocationStreet;
}

public String getAccidentCity() {
	return accidentCity;
}

public void setAccidentCity(String accidentCity) {
	this.accidentCity = accidentCity;
}

public String getAccidentstate() {
	return accidentState;
}

public void setAccidentstate(String accidentState) {
	this.accidentState = accidentState;
}

public Integer getAccidentZip() {
	return accidentZip;
}

public void setAccidentZip(Integer accidentZip) {
	this.accidentZip = accidentZip;
}

public String getClaimType() {
	return claimType;
}

public void setClaimType(String claimType) {
	this.claimType = claimType;
}

public Long getPolicyNumber() {
	return policyNumber;
}

public void setPolicyNumber(Long policyNumber) {
	this.policyNumber = policyNumber;
}

@Override
public String toString() {
	return "Claim [" + (claimNumber != null ? "claimNumber=" + claimNumber + ", " : "")
			+ (claimReason != null ? "claimReason=" + claimReason + ", " : "")
			+ (accidentLocationStreet != null ? "accidentLocationStreet=" + accidentLocationStreet + ", " : "")
			+ (accidentCity != null ? "accidentCity=" + accidentCity + ", " : "")
			+ (accidentState != null ? "accidentstate=" + accidentState + ", " : "")
			+ (accidentZip != null ? "accidentZip=" + accidentZip + ", " : "")
			+ (claimType != null ? "claimType=" + claimType + ", " : "")
			+ (policyNumber != null ? "policyNumber=" + policyNumber : "") + "]";
}


}
