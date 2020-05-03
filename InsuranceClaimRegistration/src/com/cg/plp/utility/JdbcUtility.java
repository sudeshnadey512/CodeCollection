package com.cg.plp.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.plp.exception.InsuranceException;



	public class JdbcUtility {
		private static Connection connection=null;
		public static Connection getConnection() throws InsuranceException
		{
			File file = new File("resources/jdbc.properties");
			FileInputStream inputStream=null;
			try {
				inputStream=new FileInputStream(file);
				Properties properties=new Properties();
				properties.load(inputStream);
				String driver=properties.getProperty("db.driver");
				String url=properties.getProperty("db.url");
				String username=properties.getProperty("db.username");
				String password= properties.getProperty("db.password");
				Class.forName(driver);
				connection=DriverManager.getConnection(url,username,password);
				
			} catch (FileNotFoundException e) {
				throw new InsuranceException("properties file is not found");
			} catch (IOException e) {
				throw new InsuranceException("I/O exception in File reading");
			} catch (ClassNotFoundException e) {
				throw new InsuranceException("Driver class not found");
			} catch (SQLException e) {
				throw new InsuranceException("Connection not established "+e);
			}
			finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new InsuranceException("error in closing file");
				}
			}
			return connection;
			
		}

	}


