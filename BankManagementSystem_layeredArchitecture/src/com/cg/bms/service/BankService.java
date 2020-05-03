package com.cg.bms.service;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Account;

public interface BankService {


	boolean validateFields(Account account)throws BMSException;
	
	//String insertDetails(Account account) throws BMSException;

	Object createAccountTable(Account account) throws BMSException;


}
