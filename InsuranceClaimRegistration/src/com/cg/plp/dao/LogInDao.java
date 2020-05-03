package com.cg.plp.dao;

import com.cg.plp.exception.InsuranceException;

public interface LogInDao {

	String logInValidate(String user,String pass) throws InsuranceException;

}
