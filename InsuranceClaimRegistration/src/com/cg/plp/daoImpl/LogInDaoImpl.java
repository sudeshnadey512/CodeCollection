package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.plp.dao.LogInDao;
import com.cg.plp.dao.QueryMapper;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.utility.JdbcUtility;

public class LogInDaoImpl implements LogInDao {
	static Logger logger = Logger.getLogger(LogInDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public String logInValidate(String user, String pass) throws InsuranceException {
		ResultSet resultSet = null;
		connection = JdbcUtility.getConnection();
		logger.info("connection done");
		try {
			statement = connection.prepareStatement(QueryMapper.logInQuery);
			statement.setString(1, user);
			statement.setString(2, pass);
			logger.info("statement done");
			resultSet = statement.executeQuery();

			if (resultSet.next() == true)

			{
				String userId = resultSet.getString(1);
				String password = resultSet.getString(2);
				String roleCode = resultSet.getString(3);

				if (userId.equals(user) && password.equals(pass)) {
					logger.info("validated user");
					return roleCode;
				}

			}
		} catch (SQLException e) {
			throw new InsuranceException("Sql exception in LogInDaiImpl" + e);
		}

		return null;
	}

}
