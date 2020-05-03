package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.plp.dao.QueryMapper;
import com.cg.plp.dao.ValidatePolicyNumberServiceDao;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.utility.JdbcUtility;

public class ValidatePolicyNumberServiceImplDao implements ValidatePolicyNumberServiceDao {
	static Logger logger = Logger.getLogger(ValidatePolicyNumberServiceImplDao.class);
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public List<PolicyDetails> validatePolicyNumber() throws InsuranceException {
		
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet = null;
		List<PolicyDetails> list = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.policyNumberValidator);
			logger.info("connection established..");
			
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Long policyNumber1=resultSet.getLong(1);
				
				PolicyDetails detailedView = new PolicyDetails();
				detailedView.setPolicyNumber(policyNumber1);
				list.add(detailedView);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new InsuranceException("problem occured while creating statement"+e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close resultset");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close statement");
			}
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("unable to close connection");
			}
		}
		return list;
		
	}
	}


