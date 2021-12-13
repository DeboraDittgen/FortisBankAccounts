package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.Account;
import bus.Transaction;
import data.DBConnection;

public class TransactionServiceDB {
	
	static private Connection myConnection;
	static private PreparedStatement statement;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Transaction aTrans = null;
	
	public static int insert(Transaction newTrans) throws SQLException {
		myConnection = DBConnection.getConnection();

		mySQLStatement = "Insert into Transactions (" +
		  "Account_Number, Customer_ID, Transaction_no, Transaction_type, Transaction_Ammount) " +
		  "values( ?, ?, ?, ?, ?) ";
	
		try {
			statement = myConnection.prepareStatement(mySQLStatement);
			statement.setInt(1, newTrans.getAccountNumber());
			statement.setInt(2, newTrans.getCustomerID());
			statement.setInt(3, newTrans.getTransaction_no());
			statement.setString(4, newTrans.getTransaction_type());		
			statement.setDouble(5, newTrans.getTransaction_amount());
			
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
	
	public static Transaction search2(int accNum) throws SQLException, SQLException{
			
			myConnection = DBConnection.getConnection();
			
			mySQLQuery = "SELECT Account_Number, Customer_ID, Transaction_no, Transaction_type, Transaction_Ammount From Transactions Where Account_Number = " + accNum ;
			
			myStatemnt = myConnection.createStatement();
			
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			if (myResultSet.next()) {
				aTrans = new Transaction(
						Integer.parseInt(myResultSet.getString(1)),
						Integer.parseInt(myResultSet.getString(2)),
						Integer.parseInt(myResultSet.getString(3)),
						myResultSet.getString(4), 	
						Double.parseDouble(myResultSet.getString(5)));
						
			}
			return aTrans;
	}
	
	public static ArrayList<Transaction> select() throws SQLException, NumberFormatException, SQLException{
			
			myConnection = DBConnection.getConnection();
			
			mySQLQuery = "SELECT * FROM Transactions";
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			ArrayList<Transaction> myList = new ArrayList<Transaction>();
			while(myResultSet.next()) {	
				aTrans = new Transaction(Integer.parseInt(myResultSet.getString(1)), Integer.parseInt(myResultSet.getString(2)) , Integer.parseInt(myResultSet.getString(3)), myResultSet.getString(4), Double.parseDouble(myResultSet.getString(5)));
				
				myList.add(aTrans);
			}
			return myList;
	}
}
