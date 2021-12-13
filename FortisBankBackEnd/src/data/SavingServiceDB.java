package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.*;
import data.DBConnection;

public class SavingServiceDB {
	
	static private Connection myConnection;
	static private PreparedStatement statement;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Account aAccount = null;
	
	public static int insert(Account newAccount) throws SQLException {
		myConnection = DBConnection.getConnection();

		mySQLStatement = "Insert into Accounts (" +
		  "Account_Type, Account_Number, Balance, Data_Creation, Customer_ID) " +
		  "values( ?, ?, ?, ?, ?) ";
	
		try {
			statement = myConnection.prepareStatement(mySQLStatement);
			statement.setString(1, newAccount.getAccountType());
			statement.setInt(2, newAccount.getAccountNumber());
			statement.setDouble(3, newAccount.getAccountBalance());
			statement.setString(4, newAccount.getCreationDate());
			statement.setInt(5, newAccount.getCustomerID());

			int rowAffected = statement.executeUpdate();
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
	
	public static int delete(Account searchedAccount) throws SQLException {
		myConnection = DBConnection.getConnection();
		mySQLStatement = "Delete FROM Accounts WHERE Account_Number = " + searchedAccount;

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
	
	public static Account search(int id) throws SQLException, SQLException{
			
			myConnection = DBConnection.getConnection();
			
			mySQLQuery = "SELECT Account_Type, Account_Number, Balance, Data_Creation, Customer_ID From Accounts Where Customer_ID = " + id ;
			
			myStatemnt = myConnection.createStatement();
			
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			if (myResultSet.next()) {
				aAccount = new Checking(
						myResultSet.getString(1),
						Integer.parseInt(myResultSet.getString(2)), 	
						Double.parseDouble(myResultSet.getString(3)),
						myResultSet.getString(4),
						Integer.parseInt(myResultSet.getString(5)));
			}
			return aAccount;
	}
	public static Account search2(int accNum) throws SQLException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT Account_Type, Account_Number, Balance, Data_Creation, Customer_ID From Accounts Where Account_Number = " + accNum ;
		
		myStatemnt = myConnection.createStatement();
		
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if (myResultSet.next()) {
			aAccount = new Checking(
					myResultSet.getString(1),
					Integer.parseInt(myResultSet.getString(2)), 	
					Double.parseDouble(myResultSet.getString(3)),
					myResultSet.getString(4),
					Integer.parseInt(myResultSet.getString(5)));
		}
		return aAccount;
	}
	
	public static ArrayList<Account> select() throws SQLException, NumberFormatException, SQLException{
			
			myConnection = DBConnection.getConnection();
			
			mySQLQuery = "SELECT * FROM Accounts";
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			ArrayList<Account> myList3 = new ArrayList<Account>();
			while(myResultSet.next()) {
				
			aAccount = new Checking(myResultSet.getString(1), Integer.parseInt(myResultSet.getString(2)), Double.parseDouble(myResultSet.getString(3)), myResultSet.getString(4), Integer.parseInt(myResultSet.getString(5)));
				
				myList3.add(aAccount);
			}
			
			return myList3;
	}
	
	public static int updateAccount(Account searchedAccount) throws SQLException {
			
			myConnection = DBConnection.getConnection();
			
			mySQLStatement = "UPDATE Accounts set Balance = " + searchedAccount.getAccountBalance() + " WHERE Account_Number = " + searchedAccount.getAccountNumber() ;
			
			try {	
				myStatemnt = myConnection.createStatement();
				int rowAffected = myStatemnt.executeUpdate(mySQLStatement);
				    myConnection.commit();	
				    
				if(rowAffected > 0) {
					return 1;
				}else {
					return 0;
				}			
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
}
