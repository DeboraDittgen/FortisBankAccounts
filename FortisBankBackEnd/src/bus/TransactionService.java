package bus;

import java.sql.SQLException;
import java.util.ArrayList;

import data.CheckingServiceDB;
import data.TransactionServiceDB;


public class TransactionService {
	public static int insert(Transaction newTrans) throws SQLException {
		return TransactionServiceDB.insert(newTrans);
	}
	
	public static ArrayList<Transaction> getData() throws SQLException, SQLException {
		return TransactionServiceDB.select();
	}
	
	public static Transaction search2(int accNum) throws SQLException, SQLException {
		return TransactionServiceDB.search2(accNum);
	}

}
