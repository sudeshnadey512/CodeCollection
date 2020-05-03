package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.plp.dao.ClaimViewDao;
import com.cg.plp.dao.QueryMapper;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.utility.JdbcUtility;

public class ClaimViewDaoImpl implements ClaimViewDao {
	static Logger logger = Logger.getLogger(ClaimViewDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	@Override
	public List<Claim> viewClaimDetails() throws InsuranceException {
		
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet1 = null;
		List<Claim> list1 = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.selectPolicyDetails);
			logger.info("connection established..");
			resultSet1 = statement.executeQuery();
		
			while (resultSet1.next()) {
				
				Long policyNumber1 = resultSet1.getLong(1);
				Long claimNumber=resultSet1.getLong(2);
				Claim policy =new Claim();
				policy.setPolicyNumber(policyNumber1);
				policy.setClaimNumber(claimNumber);
				list1.add(policy);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new InsuranceException("problem occured while creating statement"+e);
		} finally {
			try {
				resultSet1.close();
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
		return list1;

	}

	@Override
	public List<Claim> getDetailsByClaimNumber(Long claimNumber1) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet1 = null;
		List<Claim> list1 = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.selectClaimByClaimNumber);
			logger.info("connection established..");
			statement.setLong(1, claimNumber1);
			resultSet1 = statement.executeQuery();
			while (resultSet1.next()) {
				String claimReason=resultSet1.getString(1);
				String accidentLocationStreet=resultSet1.getString(2);
				String accidentcity=resultSet1.getString(3);
				String accidentState=resultSet1.getString(4);
				Integer accidentZip=resultSet1.getInt(5);
				String claimType = resultSet1.getString(6);
				Claim claim1 =new Claim();
				claim1.setClaimReason(claimReason);
				claim1.setAccidentLocationStreet(accidentLocationStreet);
				claim1.setAccidentCity(accidentcity);
				claim1.setAccidentstate(accidentState);
				claim1.setAccidentZip(accidentZip);
				claim1.setClaimType(claimType);
				list1.add(claim1);
			
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new InsuranceException("problem occured while creating statement"+e);
		} finally {
			try {
				resultSet1.close();
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
		return list1;
	}

	@Override
	public List<ClaimQuestion> claimQuestionsView(Long policyNumber1) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		ResultSet resultSet1 = null;
		List<ClaimQuestion> list = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.claimViewQuestionsView);
			statement.setLong(1, policyNumber1);
			resultSet1 = statement.executeQuery();

			while (resultSet1.next()) {

				String question = resultSet1.getString(1);
			
				ClaimQuestion claimQuestion = new ClaimQuestion();
			
				claimQuestion.setQuestionDesc(question);
				

				list.add(claimQuestion);
			
			}

		} catch (SQLException e) {
			throw new InsuranceException("Problem" + e);
		}

		return list;
		
	}

	@Override
	public List<PolicyDetails> claimAnswer(Long policyNumber1) throws InsuranceException {
		connection = JdbcUtility.getConnection();
		ResultSet resultSet1 = null;
		List<PolicyDetails> list1 = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.claimViewAnswerView);
			statement.setLong(1, policyNumber1);
			resultSet1 = statement.executeQuery();

			while (resultSet1.next()) {

				String answer = resultSet1.getString(1);

				PolicyDetails policydetails = new PolicyDetails();

				policydetails.setAnswer(answer);

				list1.add(policydetails);
			}

		} catch (SQLException e) {
			throw new InsuranceException("Problem" + e);
		}

		return list1;
	}

}
