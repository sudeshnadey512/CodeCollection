package com.cg.plp.service;

import java.util.List;
import java.util.Map;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.Policy;

public interface ClaimHandlerService {

	long getAccountNumber(String userName) throws InsuranceException;

	List<Policy> getPolicyNumber(long accountNumber) throws InsuranceException;

	List<ClaimQuestion> selectQuestion(long policyNumber) throws InsuranceException;

	boolean validateZip(int accidentZip) throws InsuranceException;

	boolean validateAccidentState(String accidentState) throws InsuranceException;

	boolean validateAccidentCity(String accidentCity) throws InsuranceException;

	boolean validateAccidentLocation(String accidentLocation) throws InsuranceException;

	boolean validateClaimReason(String claimReason) throws InsuranceException;

	long insertDetails(Claim handler)throws InsuranceException;

	void insertAnswer(long policyNumber, int questionId, String answer)throws InsuranceException;

	Map<Long, Long> getPolicyNumberClaimNumber(String userName) throws InsuranceException;

}
