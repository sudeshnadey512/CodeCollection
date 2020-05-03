package com.cg.plp.serviceImpl;

import java.util.List;

import com.cg.plp.dao.AgentDao;
import com.cg.plp.daoImpl.AgentDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.service.AgentService;

public class AgentServiceImpl implements AgentService{
	
	AgentDao agentDao = new AgentDaoImpl();

	@Override
	public List<Long> viewUserUnderAgent(String userId) throws InsuranceException{
		return agentDao.viewUserUnderAgent(userId);
		
	}

	@Override
	public String getUserName(Long accountNumber) throws InsuranceException {
		
		return agentDao.getUserName(accountNumber);
	}

}
