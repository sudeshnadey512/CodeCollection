package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.plp.dao.ClaimHandlerDao;
import com.cg.plp.dao.QueryMapper;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.Policy;
import com.cg.plp.utility.JdbcUtility;

public class ClaimHandlerDaoImpl implements ClaimHandlerDao {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public long getAccountNumber(String userName) throws InsuranceException {
		long accNumber = 0;
		ResultSet resultSet = null;
		connection = JdbcUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.selectAccountNo);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();
			resultSet.next();
			accNumber = resultSet.getLong(1);
		} catch (SQLException e) {
			throw new InsuranceException("Account no cannot be Selected");
		}

		return accNumber;
	}

	@Override
	public List<Policy> getPolicyNumber(long accountNumber) throws InsuranceException {
		List<Policy> list = new ArrayList<>();
		ResultSet resultSet = null;
		
		connection = JdbcUtility.getConnection();
		try {

			statement = connection.prepareStatement(QueryMapper.selectPolicyNo);
			statement.setLong(1, accountNumber);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Policy policy = new Policy();
				policy.setPolicyNumber(resultSet.getLong("policy_number"));
				policy.setPolicyType(resultSet.getString("policy_type"));
				list.add(policy);
			}
			if (list.isEmpty()) {
				System.out.println("List is empty");
			}
		} catch (SQLException e) {
			throw new InsuranceException("Policy No not Selected" +e);
		}

		return list;
	}

	@Override
	public List<ClaimQuestion> selectQuestion(long policyNumber) throws InsuranceException {
		ResultSet resultSet = null;
		List<ClaimQuestion> list = new ArrayList<>();
		connection = JdbcUtility.getConnection();
		
		try {
			statement = connection.prepareStatement(QueryMapper.selectQuestionQuery);
			statement.setLong(1, policyNumber);
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ClaimQuestion question = new ClaimQuestion();
				question.setQuestionDesc(resultSet.getString(1));
				question.setQuestionAns1(resultSet.getString(2));
				question.setQuestionAns2(resultSet.getString(3));
				question.setQuestionAns3(resultSet.getString(4));
				question.setQuestionAns4(resultSet.getString(5));
				
				list.add(question);
					
			}
			if (list.isEmpty()) {
				System.out.println("List is empty");
			}
		} catch (SQLException e) {
			throw new InsuranceException("No Question is there on that policy number" +e);
		}
	
		
		return list;
	}

	@Override
	public long insertDetails(Claim handler) throws InsuranceException {
		long generatedId = 0;
		ResultSet resultSet = null;
		connection = JdbcUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.insertClaim);
			statement.setString(1, handler.getClaimReason());
			statement.setString(2, handler.getAccidentLocationStreet());
			statement.setString(3, handler.getAccidentCity());
			statement.setString(4, handler.getAccidentstate());
			statement.setInt(5, handler.getAccidentZip());
			statement.setString(6, handler.getClaimType());
			statement.setLong(7, handler.getPolicyNumber());
			statement.executeUpdate();
			connection.commit();
			statement = connection.prepareStatement(QueryMapper.selectClaimNumber);
			resultSet = statement.executeQuery();
			resultSet.next();
			generatedId = resultSet.getLong(1);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new InsuranceException("Rollback Failed");
			}
			throw new InsuranceException(" Claim Not Inserted" + e);
		}
		return generatedId;
	}

	@Override
	public void insertAnswer(long policyNumber, int questionId, String answer) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		try {
			statement = connection.prepareStatement(QueryMapper.insertAnswerQuery);
			statement.setLong(1, policyNumber);
			statement.setInt(2, questionId);
			statement.setString(3, answer);
			
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new InsuranceException("Rollback Failed");
			}
			throw new InsuranceException(" Answer Not Inserted" + e);
		}
		
	}

	@Override
	public Map<Long, Long> getPolicyNumberClaimNumber(String userName) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		Map<Long, Long> claimPolicyMap=new HashMap<Long, Long>();
		try {
			statement = connection.prepareStatement(QueryMapper.getPolicyClaimNumberQuery);
			statement.setString(1, userName);
			
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				claimPolicyMap.put(resultSet.getLong(1), resultSet.getLong(2));
				}
			connection.commit();
			return claimPolicyMap;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new InsuranceException("Rollback Failed");
			}
			throw new InsuranceException(" Answer Not Inserted" + e);
		}
	}


}
