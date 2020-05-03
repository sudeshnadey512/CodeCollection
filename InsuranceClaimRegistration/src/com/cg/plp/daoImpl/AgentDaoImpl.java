package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.plp.dao.AgentDao;
import com.cg.plp.dao.QueryMapper;
import com.cg.plp.exception.InsuranceException;

import com.cg.plp.utility.JdbcUtility;

public class AgentDaoImpl implements AgentDao{
	
	static Logger logger = Logger.getLogger(ViewClaimUserDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;


	@Override
	public List<Long> viewUserUnderAgent(String userId) throws InsuranceException {
		
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet = null;
		List<Long> list = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.getUserUnderAgent);
			logger.info("connection established..");
			statement.setString(1,userId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				
				Long accountNumber = resultSet.getLong(1);
				
				
				list.add(accountNumber);
			
			}
			
			

		} catch (SQLException e1) {
			System.err.println();
		}
		return list;
		
		
	}


	@Override
	public String getUserName(Long accountNumber) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet = null;
		List<Long> list = new ArrayList<>();
		String userName="";

		try {
			statement = connection.prepareStatement(QueryMapper.getUserName);
			logger.info("connection established..");
			statement.setLong(1,accountNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				
				 userName = resultSet.getString(1);
				
				

			
			}
			
			

		} catch (SQLException e1) {
			System.err.println();
		}
		return userName;
		
	}

}
