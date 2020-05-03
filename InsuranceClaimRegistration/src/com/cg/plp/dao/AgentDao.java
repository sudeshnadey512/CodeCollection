package com.cg.plp.dao;

import java.util.List;

import com.cg.plp.exception.InsuranceException;

public interface AgentDao {

	List<Long> viewUserUnderAgent(String userId) throws InsuranceException;

	String getUserName(Long accountNumber) throws InsuranceException;

}
