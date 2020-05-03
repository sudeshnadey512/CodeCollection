package com.cg.plp.model;

import java.io.Serializable;

public class ClaimQuestion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer questionId;
	private String questionDesc;
	private String questionAns;
	private String questionAns1;
	private String questionAns2;
	private String questionAns3;
	private String questionAns4;
	private Long PolicyNumber;
	private String policyType;
	public ClaimQuestion() {
		
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public String getQuestionAns() {
		return questionAns;
	}
	public void setQuestionAns(String questionAns) {
		this.questionAns = questionAns;
	}
	public String getQuestionAns1() {
		return questionAns1;
	}
	public void setQuestionAns1(String questionAns1) {
		this.questionAns1 = questionAns1;
	}
	public String getQuestionAns2() {
		return questionAns2;
	}
	public void setQuestionAns2(String questionAns2) {
		this.questionAns2 = questionAns2;
	}
	public String getQuestionAns3() {
		return questionAns3;
	}
	public void setQuestionAns3(String questionAns3) {
		this.questionAns3 = questionAns3;
	}
	public String getQuestionAns4() {
		return questionAns4;
	}
	public void setQuestionAns4(String questionAns4) {
		this.questionAns4 = questionAns4;
	}
	public Long getPolicyNumber() {
		return PolicyNumber;
	}
	public void setPolicyNumber(Long policyNumber) {
		PolicyNumber = policyNumber;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ClaimQuestion(Integer questionId, String questionDesc, String questionAns, String questionAns1,
			String questionAns2, String questionAns3, String questionAns4, Long policyNumber, String policyType) {
		super();
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		this.questionAns = questionAns;
		this.questionAns1 = questionAns1;
		this.questionAns2 = questionAns2;
		this.questionAns3 = questionAns3;
		this.questionAns4 = questionAns4;
		PolicyNumber = policyNumber;
		this.policyType = policyType;
	}
	@Override
	public String toString() {
		return "ClaimQuestion [questionId=" + questionId + ", questionDesc=" + questionDesc + ", questionAns="
				+ questionAns + ", questionAns1=" + questionAns1 + ", questionAns2=" + questionAns2 + ", questionAns3="
				+ questionAns3 + ", questionAns4=" + questionAns4 + ", PolicyNumber=" + PolicyNumber + ", policyType="
				+ policyType + "]";
	}
	
	

}
