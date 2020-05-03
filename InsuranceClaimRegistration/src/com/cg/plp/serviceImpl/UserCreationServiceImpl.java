package com.cg.plp.serviceImpl;

import java.util.regex.Pattern;

import com.cg.plp.dao.UserCreationDao;
import com.cg.plp.daoImpl.UserCreationDaoImpl;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.UserRole;
import com.cg.plp.service.UserCreationService;

public class UserCreationServiceImpl implements UserCreationService {
	UserCreationDao userCreation = new UserCreationDaoImpl();

	@Override
	public boolean checkUserUnique(String userName) throws InsuranceException {

		boolean checkFlag = userCreation.checkUserUnique(userName);
		return checkFlag;
	}

	@Override
	public Object insertUserCreationDetails(UserRole newUser) throws InsuranceException {
		return userCreation.insertUserCreationDetails(newUser);

	}

	public boolean validatePassword(String password) {
		String passwordRegEx = "[A-Z]{1}[A-Za-z0-9@._*#]{3,8}";
		boolean passwordFlag = Pattern.matches(passwordRegEx, password);
		return passwordFlag;

	}

	public boolean validateUserName(String userName) {
		String userNameRegEx = "[a-z]{3,20}";
		boolean userNameFlag = Pattern.matches(userNameRegEx, userName);
		if (userNameFlag) {

			return true;

		} else {
			return false;
		}

	}

}
