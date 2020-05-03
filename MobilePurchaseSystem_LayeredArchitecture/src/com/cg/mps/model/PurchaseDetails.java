package com.cg.mps.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PurchaseDetails implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = -8079645506649705420L;
		Integer purchaseId;
		String cName;
		String mailId;
		String phoneNo;
		LocalDate purchaseDate;
		Integer mobileId;
		
		public PurchaseDetails() {
			// TODO Auto-generated constructor stub
		}

		public PurchaseDetails(Integer purchaseId, String cName, String mailId, String phoneNo, LocalDate purchaseDate,
				Integer mobileId) {
			super();
			this.purchaseId = purchaseId;
			this.cName = cName;
			this.mailId = mailId;
			this.phoneNo = phoneNo;
			this.purchaseDate = purchaseDate;
			this.mobileId = mobileId;
		}

		public Integer getPurchaseId() {
			return purchaseId;
		}

		public void setPurchaseId(Integer purchaseId) {
			this.purchaseId = purchaseId;
		}

		public String getcName() {
			return cName;
		}

		public void setcName(String cName) {
			this.cName = cName;
		}

		public String getMailId() {
			return mailId;
		}

		public void setMailId(String mailId) {
			this.mailId = mailId;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public LocalDate getPurchaseDate() {
			return purchaseDate;
		}

		public void setPurchaseDate(LocalDate purchaseDate) {
			this.purchaseDate = purchaseDate;
		}

		public Integer getMobileId() {
			return mobileId;
		}

		public void setMobileId(Integer mobileId) {
			this.mobileId = mobileId;
		}

		@Override
		public String toString() {
			return "PurchaseDetails [purchaseId=" + purchaseId + ", cName=" + cName + ", mailId=" + mailId
					+ ", phoneNo=" + phoneNo + ", purchaseDate=" + purchaseDate + ", mobileId=" + mobileId + "]";
		}
		
		
		
}
