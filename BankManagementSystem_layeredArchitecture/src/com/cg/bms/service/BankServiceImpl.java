package com.cg.bms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.cg.bms.dao.AccountDAO;
import com.cg.bms.dao.AccountDaoImpl;
import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Account;

public class BankServiceImpl implements BankService {

	AccountDAO accountDAO = new AccountDaoImpl();

	@Override
	public boolean validateFields(Account account) throws BMSException {

		boolean validateFlag = false;
		List<String> list = new ArrayList<>();

		if (!validateCustId(account.getCustId())) {
			list.add("cust id should contain exactly 4 digitis \n");
		}

		if (!validateAccountNo(account.getAccountNo())) {
			list.add("acc no should contain exactly 5 digitis \n");
		}
		if (!validateIfscCode(account.getIfscCode())) {
			list.add("First 4 letter should be Capital and last 5 should be a no \n");
		}
		if (!validateBalance(account.getBalance())) {
			list.add("It should be atleast 10,000");
		}
		if (!validateCustomerName(account.getCustomerName())) {
			list.add("First letter should be Capital and length should be total 5 \n");
		}
		if (!validateCity(account.getCity())) {
			list.add("First letter should be Capital and length should be from 5 to 15 and have white space \n");
		}

		if (!list.isEmpty()) {
			throw new BMSException(list + "");
		} else {
			validateFlag = true;

		}
		return validateFlag;
	}

	public boolean validateCustId(Long custId) {
		String custIdRegEx = "[0-9]{4}";
		return Pattern.matches(custIdRegEx, Long.toString(custId));
	}

	public boolean validateAccountNo(String accountNo) {
		String accountNoRegEx = "[0-9]{5}";
		return Pattern.matches(accountNoRegEx, accountNo);
	}

	public boolean validateIfscCode(String ifscCode) {
		String ifscCodeRegEx = "[A-Z]{4}[0-9]{5}";
		return Pattern.matches(ifscCodeRegEx, ifscCode);
	}

	public boolean validateBalance(Double balance) {
		boolean balanceFlag = false;
		if (balance >= 10000) {
			balanceFlag = true;
		}
		return balanceFlag;
	}

	public boolean validateCustomerName(String customerName) {
		String custNameRegEx = "[A-Z]{1}[a-zA-z]{4}";
		return Pattern.matches(custNameRegEx, customerName);
	}

	public boolean validateCity(String city) {
		String cityRegEx = "[A-Z]{1}[a-zA-z ]{5,15}";
		return Pattern.matches(cityRegEx, city);
	}

	/*@Override
	public String insertDetails(Account account) throws BMSException {
		return accountDAO.insertDetails(account);
	}*/

	@Override
	public Object createAccountTable(Account account) throws BMSException {
		return accountDAO.createAccountTable(account);
		
	}

}
