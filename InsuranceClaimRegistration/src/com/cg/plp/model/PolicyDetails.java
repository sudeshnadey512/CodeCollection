package com.cg.plp.model;

import java.io.Serializable;

public class PolicyDetails implements Serializable{
private Long policyNumber;
private String questionId;
private String answer;
public PolicyDetails() {
	
}

public PolicyDetails(Long policyNumber, String questionId, String answer) {
	super();
	this.policyNumber = policyNumber;
	this.questionId = questionId;
	this.answer = answer;
}

public Long getPolicyNumber() {
	return policyNumber;
}
public void setPolicyNumber(Long policyNumber) {
	this.policyNumber = policyNumber;
}
public String getQuestionId() {
	return questionId;
}
public void setQuestionId(String questionId) {
	this.questionId = questionId;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}

@Override
public String toString() {
	return "PloicyDetails [policyNumber=" + policyNumber + ", questionId=" + questionId + ", answer=" + answer + "]";
}

}
