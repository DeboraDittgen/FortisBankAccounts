package bus;

import java.sql.SQLException;
import java.util.Scanner;

public class Saving extends Account {

	public Saving() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Saving(String accountType, int accountNumber, double accountBalance, String creationDate, int customerID) {
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

	public void addAccount(int id, int accountNumber, double balance, String creationDate)
			throws ValidationException, Account_Exception, SQLException {

		// Remember: You must open a checking account to open a SAVING ACCOUNT.
		Account searchedAccount = new Saving();
		boolean valid2 = false;

		searchedAccount = AccountService.search(id);

		for (int i = 0; i < accounts.size() + 1; i++) {
			if (id == searchedAccount.getCustomerID()) {

				int serchedId = searchedAccount.getCustomerID();
				Account sAccount = new Saving();

				sAccount.setCustomerID(serchedId);
				sAccount.setAccountType(AccountTypes.SAVING.toString());
				sAccount.setCreationDate(creationDate);

				do {
					try {
						sAccount.setAccountNumber(accountNumber);

						Account accFound = getAccountFromListWithAccountNumber(sAccount.getAccountNumber());
						if (accFound != null) {
							throw new Account_Exception("Null number");
						}
						valid2 = true;
					} catch (Account_Exception ex) {
						valid2 = false;
					}
				} while (!valid2);

				valid2 = false;

				do {
					sAccount.setAccountBalance(balance);
					valid2 = true;

				} while (!valid2);

				AccountService.insert(sAccount);
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
