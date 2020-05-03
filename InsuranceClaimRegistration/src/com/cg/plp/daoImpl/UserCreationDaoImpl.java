package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.cg.plp.dao.QueryMapper;
import com.cg.plp.dao.UserCreationDao;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.UserRole;
import com.cg.plp.utility.JdbcUtility;

public class UserCreationDaoImpl implements UserCreationDao {
	
	static Logger logger = Logger.getLogger(UserCreationDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	
	/**
	 * method name : checkUserUnique 
	 * argument : userName 
	 * return type : boolean
	 * author : Capgemini date : 15-02-2019
	 * 
	 * description : These method will take the courseId as an argument and
	 * returns true or false to the user
	 */
	
	
	
	
	
	@Override
	public boolean checkUserUnique(String userName) throws InsuranceException{
		logger.info("in DAO impl class");
		logger.info("user details: " );
		ResultSet resultSet = null;
		boolean result=false;
		

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		
		try {
			
			statement=connection.prepareStatement(QueryMapper.checkUserCreationQuery);
			statement.setString(1, userName);
			
			
			resultSet=statement.executeQuery();
			logger.info("connection established..");
			while(resultSet.next()) {
				
				String uname=resultSet.getString(1);
				
				if(uname.equals(userName))
					result=true;
			}
			
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.info("rollback cant be performed");
			}
			logger.error(e.getMessage());
			throw new InsuranceException("unable to create the statement,Unique constraint violated" + e);

		}
		finally {

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close statement object" + e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close connection object");
			}
		}

		
		
		return result;
	}

	
	@Override
	public Object insertUserCreationDetails(UserRole user) throws InsuranceException {
		int result;
		logger.info("in DAO impl class");
		logger.info("Course details: " + user);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		try {

			statement = connection.prepareStatement(QueryMapper.insertUserCreationQuery);
			logger.info("connection established..");

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getRoleCode());
			

			result=statement.executeUpdate();
			logger.info("statement executed, course record inserted");
			connection.commit();

		} catch (SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.info("rollback cant be performed");
			}
			logger.error(e.getMessage());
			
			throw new InsuranceException("unable to create the statement" + e.getMessage());
			
		} finally {

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close statement object" + e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close connection object");
			}
		}

		return result;

	}

	
	
	
	
	
	
}
