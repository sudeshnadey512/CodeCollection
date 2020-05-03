package com.cg.plp.dao;

import java.util.List;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.PolicyDetails;

public interface ValidatePolicyNumberServiceDao {

	List<PolicyDetails> validatePolicyNumber() throws InsuranceException;

}
