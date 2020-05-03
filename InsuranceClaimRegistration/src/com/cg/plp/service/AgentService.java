package com.cg.plp.service;

import java.util.List;

import com.cg.plp.exception.InsuranceException;

public interface AgentService {

	List<Long> viewUserUnderAgent(String userId) throws InsuranceException;

	String getUserName(Long accountNumber) throws InsuranceException;

}
