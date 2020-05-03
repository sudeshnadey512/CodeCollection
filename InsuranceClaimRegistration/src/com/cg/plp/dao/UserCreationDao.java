package com.cg.plp.dao;

import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.UserRole;

public interface UserCreationDao {
	boolean checkUserUnique(String userName) throws InsuranceException;
	Object insertUserCreationDetails(UserRole userRole) throws InsuranceException;

}
