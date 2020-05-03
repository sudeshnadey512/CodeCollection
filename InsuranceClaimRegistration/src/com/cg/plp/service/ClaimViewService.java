package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;

public interface ClaimViewService {

	List<Claim> viewClaimDetails() throws InsuranceException;

	List<Claim> getDetailsByClaimNumber(Long claimNumber1) throws InsuranceException;

	List<ClaimQuestion> claimDetailsView(Long policyNumber1) throws InsuranceException ;

	List<PolicyDetails> claimAnswer(Long policyNumber1) throws InsuranceException;

}
