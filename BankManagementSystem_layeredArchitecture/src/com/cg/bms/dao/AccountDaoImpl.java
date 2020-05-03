package com.cg.bms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cg.bms.exceptions.BMSException;
import com.cg.bms.model.Account;
import com.cg.bms.utility.JdbcUtility;

public class AccountDaoImpl implements AccountDAO {
	
	Connection connection =null;
	PreparedStatement statement =null;
	
	@Override
	public Object createAccountTable(Account account) throws BMSException {
		connection = JdbcUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.createQuery);
			statement.execute();
			System.out.println("table created");
			
		}catch(SQLException e) {
			
			throw new BMSException("unable to create table");
		}
		return account;
	}




	/*@Override
	public String insertDetails(Account account) throws BMSException {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/*List<Account> list = new ArrayList<>();

	@Override
	public String insertDetails(Account account) throws BMSException {
		String message = "";
		list.add(account);
		if(!list.isEmpty()) {
			message="Successful...";
		}
		return message;
		
	}*/
	

}
