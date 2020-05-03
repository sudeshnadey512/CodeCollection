package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;

public interface ClaimDetailsService {

	List<ClaimQuestion> claimQuestionsView() throws InsuranceException;

	List<PolicyDetails> claimAnswerView() throws InsuranceException;

}
