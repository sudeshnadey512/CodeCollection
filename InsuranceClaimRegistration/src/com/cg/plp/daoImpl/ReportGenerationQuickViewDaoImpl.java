package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.plp.dao.QueryMapper;
import com.cg.plp.dao.ReportGenerationQuickViewDao;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.Claim;
import com.cg.plp.utility.JdbcUtility;

public class ReportGenerationQuickViewDaoImpl implements ReportGenerationQuickViewDao {
	static Logger logger = Logger.getLogger(ReportGenerationQuickViewDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	@Override
	public List<Claim> selectQuickView() throws InsuranceException {
		
			connection = JdbcUtility.getConnection();
			logger.info("connection object created");
			ResultSet resultSet = null;
			List<Claim> list1 = new ArrayList<>();

			try {
				statement = connection.prepareStatement(QueryMapper.ReportGenaratorQuickViewQuery);
				logger.info("connection established..");
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
				
					Long policyNumber = resultSet.getLong(1);
					Long claimNumber = resultSet.getLong(2);
					String claimType = resultSet.getString(3);
					Long accountNumber=resultSet.getLong(4);
					Claim quickView  = new Claim();
					quickView.setPolicyNumber(policyNumber);
					quickView.setClaimNumber(claimNumber);
					quickView.setClaimType(claimType);
					quickView.setAccountNumber(accountNumber);
					
					list1.add(quickView);

				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new InsuranceException("problem occured while creating statement"+e);
			} /*finally {
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
			}*/
			return list1;
		}
	}


