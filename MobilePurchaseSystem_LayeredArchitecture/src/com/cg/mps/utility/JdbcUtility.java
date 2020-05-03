package com.cg.mps.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.cg.mps.exception.MPSException;

public class JdbcUtility {

	private static Connection connection = null;

	public static Connection getConnection() throws MPSException {

		File file = null;
		FileInputStream inputStream = null;

		file = new File("resources/jdbc.properties");
		try {
			inputStream = new FileInputStream(file);

			Properties properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (FileNotFoundException e) {
			throw new MPSException("File not exists");
		} catch (IOException e) {
			throw new MPSException("unable to read the data from the File");
		} catch (ClassNotFoundException e) {
			throw new MPSException("class not loaded");
		} catch (SQLException e) {
			throw new MPSException("connection issue");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new MPSException("unable to close the file");
			}
		}

		return connection;
	}
}
