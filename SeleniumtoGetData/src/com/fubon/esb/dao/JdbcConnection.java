package com.fubon.esb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class JdbcConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://45.32.49.87:3306/luckairship?characterEncoding=latin1&useConfigs=maxPerformance";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "36f57bc6fd";

	public static void inserPeriodNumber(String resultPeriod, String winningNumbers, int issue) {

		Connection conn = null;
		PreparedStatement preparedStmt = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			Calendar calendar = Calendar.getInstance();
			java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");

			String sql = " insert into bet_each_draw (PERIOD, PERIODSTR, PERIOD_NUMBER, CREATION_TIME)"
					+ " values (?, ?, ?, ?)";

			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, issue);
			preparedStmt.setString(2, resultPeriod);
			preparedStmt.setString(3, winningNumbers);
			preparedStmt.setDate(4, startDate);

			preparedStmt.execute();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (preparedStmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main
}// end JDBCExample