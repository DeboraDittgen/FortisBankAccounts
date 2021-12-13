package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.Customer;

public class CustomerDB {
	
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Customer aCustomer = null;
	
	public static int insert(Customer aNewCustomer) throws SQLException {
		myConnection = DBConnection.getConnection();		
		mySQLStatement = "Insert into Customer(Customer_ID, First_Name, Last_Name, Pin, Mananger_ID) values("
				+ aNewCustomer.getCustomerID() + ", \'"
				+ aNewCustomer.getFirstName() + "\', \'"
				+ aNewCustomer.getLastName() + "\', "
				+ aNewCustomer.getPin() + ", "
				+ aNewCustomer.getManagerID() + ")";
                                                   
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
	
	public static int delete(int id ) throws SQLException {
		myConnection = DBConnection.getConnection();
		
		mySQLStatement = "Delete FROM Customer WHERE Customer_ID = "  + id  ;
		
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
	
	public static Customer search(int id) throws SQLException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT Customer_Id, First_Name, Last_Name, Pin, Mananger_ID FROM Customer WHERE Customer_ID = " + id ;
		
		myStatemnt = myConnection.createStatement();
		
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			aCustomer = new Customer(
									Integer.parseInt(myResultSet.getString(1)),
									myResultSet.getString(2), 
									myResultSet.getString(3),
									Integer.parseInt(myResultSet.getString(4)),
									Integer.parseInt(myResultSet.getString(5)));
		}		
		return aCustomer;
	}
	
	public static ArrayList<Customer> select() throws SQLException, NumberFormatException, SQLException{
			
			myConnection = DBConnection.getConnection();
			
			mySQLQuery = "SELECT * FROM Customer";
			
			myStatemnt = myConnection.createStatement();
			myResultSet = myStatemnt.executeQuery(mySQLQuery);
			
			ArrayList<Customer> myList = new ArrayList<Customer>();
			while(myResultSet.next()) {
				
				aCustomer = new Customer(Integer.parseInt(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), Integer.parseInt(myResultSet.getString(4)),Integer.parseInt(myResultSet.getString(5)));
				
				myList.add(aCustomer);
			}
			
			return myList;
	}
}
