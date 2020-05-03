package com.cg.plp.dao;

import java.util.List;
import java.util.Map;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.Policy;

public interface ClaimHandlerDao {

	long getAccountNumber(String userName) throws InsuranceException;

	List<Policy> getPolicyNumber(long accountNumber)throws InsuranceException;

	List<ClaimQuestion> selectQuestion(long policyNumber)throws InsuranceException;

	long insertDetails(Claim handler)throws InsuranceException;

	void insertAnswer(long policyNumber, int questionId, String answer)throws InsuranceException;

	Map<Long, Long> getPolicyNumberClaimNumber(String userName)throws InsuranceException;

}
