package bus;

import java.sql.SQLException;
import java.util.ArrayList;
import data.CheckingServiceDB;

public class AccountService {

	public static int insert(Account account) throws SQLException {
		return CheckingServiceDB.insert(account);
	}

	public static int delete(Account searchedAccount) throws SQLException {
		return CheckingServiceDB.delete(searchedAccount);
	}
	
	public static Account search2(int accNum) throws SQLException, SQLException {
		return CheckingServiceDB.search2(accNum);
	}

	public static Account search(int id) throws SQLException, SQLException {
		return CheckingServiceDB.search(id);
	}

	public static ArrayList<Account> getData() throws SQLException, SQLException {
		return CheckingServiceDB.select();
	}
	
	public static int updateAccount(Account searchedAccount) throws SQLException {
		return CheckingServiceDB.updateAccount(searchedAccount);
	}
}
