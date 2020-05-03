package com.cg.plp.serviceImpl;

import com.cg.plp.dao.LogInDao;
import com.cg.plp.daoImpl.LogInDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.service.LogInService;

public class LogInServiceImpl implements LogInService {
	LogInDao logIndao= new LogInDaoImpl();

	@Override
	public String logInValidate(String user,String pass) throws InsuranceException {
		
		return logIndao.logInValidate(user,pass);
	}

}
