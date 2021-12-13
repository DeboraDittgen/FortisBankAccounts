package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
	
	public static Connection getConnection()
	{
         Connection myConnection = null;		
	     String userName, password, service, url;
		
		 userName = "FortisMngBankDB" ;
		 password = "123" ;
		 service = "localhost" ;
		
		 url = "jdbc:oracle:thin:";
		
		try {
			
	            myConnection = DriverManager.getConnection(url + userName + "/" + password + "@" + service);
	            System.out.println(" Connection successful");
		
		} catch (SQLException ex) {
			  ex.printStackTrace();
			      System.out.println(" Connection failed  ");
		}
		
		return myConnection;		
	}
}
