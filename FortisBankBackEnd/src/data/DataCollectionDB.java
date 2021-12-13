package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.Account;



public class DataCollectionDB {
	static private Connection myConnection;
	static private PreparedStatement statement;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Account aAccount = null;
	
	public static int delete(int accNum) throws SQLException {
		myConnection = DBConnection.getConnection();
		mySQLStatement = "Delete FROM Accounts WHERE Account_Number = " + accNum;

		try {
			myStatemnt = myConnection.createStatement();
			int rowAffected = myStatemnt.executeUpdate(mySQLStatement);
			myConnection.commit();

			if (rowAffected > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
