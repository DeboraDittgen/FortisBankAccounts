package bus;

import java.sql.SQLException;
import java.util.Scanner;

public class Credit extends Account {
	
	public Credit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Credit(String accountType, int accountNumber, double accountBalance, String creationDate, int customerID) {
		super(accountType, accountNumber, accountBalance, creationDate, customerID);
		// TODO Auto-generated constructor stub
	}

	private Account getAccountFromListWithAccountNumber(int accNum) {
		
		for (Account account : accounts) {
			if (account.getAccountNumber() == accNum) {
				return account;
			}
		}	
		return null;
	}
	
	public void addAccount(int id, int accountNumber, double balance, String creationDate) throws ValidationException, Account_Exception, SQLException {
	
		Account searchedAccount = new Credit();
		boolean valid1 = false;
		
		searchedAccount = AccountService.search(id);

		for (int i = 0; i < accounts.size() + 1; i++) {
			if (id == searchedAccount.getCustomerID()) {

				int searchedId = searchedAccount.getCustomerID();
				Account cAccount = new Credit();

				cAccount.setCustomerID(searchedId);
				cAccount.setAccountType(AccountTypes.CREDIT.toString());
				cAccount.setCreationDate(creationDate);

				do {
					try {
						cAccount.setAccountNumber(accountNumber);

						Account accFound = getAccountFromListWithAccountNumber(cAccount.getAccountNumber());
						if (accFound != null) {
							throw new Account_Exception("Null number");
						}
						valid1 = true;

					} catch (Account_Exception ex) {
						valid1 = false;
					}
				} while (!valid1);

				valid1 = false;

				do {
					cAccount.setAccountBalance(balance);
					valid1 = true;

				} while (!valid1);

				AccountService.insert(cAccount);
			}
		}
	}

	@Override
	public void searchByCustomerID(int id) throws SQLException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void addAccount() throws ValidationException, Account_Exception, SQLException {
		// TODO Auto-generated method stub	
	}
}
