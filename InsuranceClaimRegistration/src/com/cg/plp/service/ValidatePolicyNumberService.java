package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.PolicyDetails;

public interface ValidatePolicyNumberService {

	List<PolicyDetails> validatePolicyNumber() throws InsuranceException;

}
