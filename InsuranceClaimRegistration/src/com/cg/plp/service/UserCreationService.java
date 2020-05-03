package com.cg.plp.service;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.UserRole;

public interface UserCreationService {

	boolean checkUserUnique(String userName) throws InsuranceException;
	
	public boolean validatePassword(String password);
	
	public boolean validateUserName(String userName);
	
	
	Object insertUserCreationDetails(UserRole newUser) throws InsuranceException;
}
