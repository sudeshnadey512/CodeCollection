package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.plp.dao.QueryMapper;
import com.cg.plp.dao.ReportGenerationDetailedViewDao;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.utility.JdbcUtility;

public class ReportGenerationDetailedViewDaoImpl implements ReportGenerationDetailedViewDao {
	static Logger logger = Logger.getLogger(ReportGenerationDetailedViewDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public List<Claim> selectDetailedView() throws InsuranceException {
		connection = JdbcUtility.getConnection();
		logger.info("connection object created");
		ResultSet resultSet1 = null;
		List<Claim> list2 = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.ReportGenaratorDetailedQuery);
			logger.info("connection established..");
			resultSet1 = statement.executeQuery();
			
			while (resultSet1.next()) {
				Long policyNumber = resultSet1.getLong(1);
				String claimReason=resultSet1.getString(2);
				String accidentLocationStreet=resultSet1.getString(3);
				String accidentcity=resultSet1.getString(4);
				String accidentState=resultSet1.getString(5);
				Integer accidentZip=resultSet1.getInt(6);
				String claimType = resultSet1.getString(7);
				Long accountNumber=resultSet1.getLong(8);
				Claim detailedView = new Claim();
				detailedView.setPolicyNumber(policyNumber);
				detailedView.setClaimReason(claimReason);
				detailedView.setAccidentLocationStreet(accidentLocationStreet);
				detailedView.setAccidentCity(accidentcity);
				detailedView.setAccidentstate(accidentState);
				detailedView.setAccidentZip(accidentZip);
				detailedView.setClaimType(claimType);
				detailedView.setAccountNumber(accountNumber);
				
				
				list2.add(detailedView);

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
		return list2;
	}
	}


