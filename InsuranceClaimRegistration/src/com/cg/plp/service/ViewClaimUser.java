package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;

public interface ViewClaimUser {

	List<Claim> viewClaimByPolicyNumber(Long policyNumber1) throws InsuranceException;

	List<ClaimQuestion> claimQuestionView(Long policyNumber1) throws InsuranceException;

	List<PolicyDetails> claimAnswer(Long policyNumber1)throws InsuranceException;

}
