package com.cg.bms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.bms.exceptions.BMSException;

public class JdbcUtility {

	public static Connection connection = null;
	public static Connection getConnection() throws BMSException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "sudeshna");
			
		}catch (ClassNotFoundException e) {
			throw new BMSException("driver not loaded"+e);
		}catch (SQLException e) {
			throw new BMSException("not connected to DB");
		}
		
		return connection;
		
	}
	
	

}
