package com.cg.mps.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.cg.mps.exception.MPSException;
import com.cg.mps.model.Mobiles;
import com.cg.mps.model.PurchaseDetails;
import com.cg.mps.utility.JdbcUtility;

public class MobileDaoImpl implements MobileDao {

	static Logger logger = Logger.getLogger(MobileDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;

	/**
	 * method name : validateLogin argument : mobileId return type : boolean author
	 * : Capgemini date : 14-02-2019
	 * 
	 * description : This method will take the mobileId as an argument and returns
	 * true or false to the user
	 */

	@Override
	public boolean validateLogin(int mobileId) throws MPSException {

		ResultSet resultSet = null;
		boolean result = false;
		logger.info("in DAO impl class");
		logger.info("Login details is : " + mobileId);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.validateLoginQuery);
			statement.setInt(1, mobileId);
			resultSet = statement.executeQuery();
			logger.info("connection established..");

			while (resultSet.next()) {
				int mId = resultSet.getInt(1);

				if (mId == mobileId) {
					result = true;

				}

			}

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println(e.getMessage());
				throw new MPSException("Rollback failed");
			}

			logger.error(e.getMessage());
			throw new MPSException("unable to create the statement" +e);
		} /*finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close resultset object");
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close statement object");
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close connection object");
			}
		}
*/
		return result;

	}

	@Override
	public boolean checkMobileData(int mobileId1) throws MPSException {
		ResultSet resultSet = null;
		boolean result1 = false;
		logger.info("in DAO impl class");
		logger.info("Login details is : " + mobileId1);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.validateLoginQuery);
			statement.setInt(1, mobileId1);

			resultSet = statement.executeQuery();
			logger.info("connection established..");

			while (resultSet.next()) {
				int mId = resultSet.getInt(mobileId1);

				if (mId == mobileId1) {
					result1 = true;

				}

			}

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.err.println(e.getMessage());
				throw new MPSException("Rollback failed");
			}

			logger.error(e.getMessage());
			throw new MPSException("unable to create the statement"+e);
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close resultset object"+e);
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close statement object"+e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close connection object"+e);
			}
		}

		return result1;
	}

	@Override
	public int insertMobileDetails(Mobiles mobiles) throws MPSException {
		int generatedId = 0;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("Mobile data is : " + mobiles);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.insertMobileQuery);
			logger.info("connection established..");

			statement.setInt(1, mobiles.getMobileId());
			statement.setString(2, mobiles.getName());
			statement.setDouble(3, mobiles.getPrice());
			statement.setString(4, mobiles.getQuantity());

			
			statement.executeUpdate();
			logger.info("statement executed, record inserted");

			statement = connection.prepareStatement(QueryMapper.selectGeneratedId);

			resultSet = statement.executeQuery();
			logger.info("resultset cretaed");

			resultSet.next();

			generatedId = resultSet.getInt(1);
			logger.info("generated id is: " + generatedId);

			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
				System.err.println("rollback failed");
			}
			logger.error(e.getMessage());
			throw new MPSException("unable to create the statement"+e);
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close resultset object"+e);
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close statement object"+e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close connection object"+e);
			}
		}

		return generatedId;
	}

	@Override
	public boolean checkQuantity(String quantity1) throws MPSException {
		ResultSet resultSet = null;
		boolean result = false;
		
		logger.info("in DAO impl class");
		logger.info("Purchase Quantity : " + quantity1);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.validateCheckQuery);
			statement.setString(1, quantity1);

			resultSet = statement.executeQuery();
			logger.info("connection established..");

			while (resultSet.next()) {
				String qty = resultSet.getString(1);

				

				if (qty.equals(quantity1)) {
					result = true;
					
					}

				}
			
			

			connection.commit();
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
				System.err.println("rollback failed");
			}
			logger.error(e.getMessage());
			throw new MPSException("unable to create the statement"+e);
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close resultset object"+e);
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close statement object"+e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close connection object"+e);
			}

		}
		return result;
	}

	@Override
	public int insertPurchaseDetails(PurchaseDetails details) throws MPSException {
		int generatedId1 = 0;
		ResultSet resultSet = null;
		logger.info("in DAO impl class");
		logger.info("Purchase details is : " + details);

		connection = JdbcUtility.getConnection();
		logger.info("connection object created");

		try {
			statement = connection.prepareStatement(QueryMapper.insertPurchaseQuery);
			logger.info("connection established..");

			statement.setInt(1, details.getPurchaseId());
			statement.setString(2, details.getcName());
			statement.setString(3, details.getMailId());
			statement.setDate(4, Date.valueOf(details.getPurchaseDate()));
			statement.setInt(5, details.getMobileId());

			statement.executeUpdate();
			logger.info("statement executed, record inserted");

			statement = connection.prepareStatement(QueryMapper.selectGeneratedId);

			resultSet = statement.executeQuery();
			logger.info("resultset cretaed");

			resultSet.next();

			generatedId1 = resultSet.getInt(1);
			logger.info("generated id is: " + generatedId1);

			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
				System.err.println("rollback failed");
			}
			logger.error(e.getMessage());
			throw new MPSException("unable to create the statement"+e);
		} finally {

			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close resultset object"+e);
			}

			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close statement object"+e);
			}

			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new MPSException("unable to close connection object"+e);
			}
		}

		return generatedId1;
	}

}
