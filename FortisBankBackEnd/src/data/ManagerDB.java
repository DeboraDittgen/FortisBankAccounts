package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.Manager;

public class ManagerDB {

	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatemnt = null;
	static private ResultSet myResultSet = null;
	static private Manager aManager = null;
	
	public static int insert(Manager aNewManager) throws SQLException {
		myConnection = DBConnection.getConnection();		
		mySQLStatement = "Insert into Manager(Manager_ID, First_Name, Last_Name)  values( " 
				+ aNewManager.getManagerId() + ", \'" 
                + aNewManager.getFirstname() + "\', \'" 
                + aNewManager.getLastName()  + "\')";
		
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
		
		mySQLStatement = "Delete FROM Manager WHERE Manager_ID = "  + id  ;
		
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
	
	public static Manager search(int id) throws SQLException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT Manager_ID, First_Name, Last_Name FROM Manager WHERE Manager_ID = " + id ;
		
		myStatemnt = myConnection.createStatement();
		
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) {
			aManager = new Manager(Integer.parseInt(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3));
		}		
		return aManager;
	}
	
	public static ArrayList<Manager> select() throws SQLException, NumberFormatException, SQLException{
		
		myConnection = DBConnection.getConnection();
		
		mySQLQuery = "SELECT * FROM Manager";
		
		myStatemnt = myConnection.createStatement();
		myResultSet = myStatemnt.executeQuery(mySQLQuery);
		
		ArrayList<Manager> myList2 = new ArrayList<Manager>();
		while(myResultSet.next()) {
			
			aManager = new Manager(Integer.parseInt(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3));
			
			myList2.add(aManager);
		}
		
		return myList2;
	}	
}
