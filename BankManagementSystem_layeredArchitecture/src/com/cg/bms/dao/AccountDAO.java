package com.cg.bms.dao;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Account;

public interface AccountDAO {

	
	//String insertDetails(Account account)throws BMSException;

	Object createAccountTable(Account account)throws BMSException;

}
