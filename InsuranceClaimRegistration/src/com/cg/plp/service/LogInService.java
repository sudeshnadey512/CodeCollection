package com.cg.plp.service;

import com.cg.plp.exception.InsuranceException;

public interface LogInService {

	String logInValidate(String user,String pass) throws InsuranceException;

	

}
