package bus;

import static bus.Account.accounts;

import java.sql.SQLException;
import java.util.ArrayList;

import data.CheckingServiceDB;

public abstract class Account {
	
	private String accountType;
	private int accountNumber;
	private double accountBalance;
	private String creationDate;
	private int customerID;
	
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Account() {
		super();
	}

	public Account(String accountType, int accountNumber, double accountBalance, String creationDate, int customerID) {
		super();
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.creationDate = creationDate;
		this.customerID = customerID;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	protected void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", accountNumber=" + accountNumber + ", accountBalance="
				+ accountBalance + ", creationDate=" + creationDate + ", customerID=" + customerID + "]";
	}
	
//	private Account getAccountFromListWithAccountNumber(int accNum) {
//		
//		for (Account account : accounts) {
//			if (account.getAccountNumber() == accNum) {
//				return account;
//			}
//		}	
//		return null;
//	}
//
//	 private Customer getAccountFromListWithId(int id) {
//		 for (Customer cust : customers) { 
//			 if (cust.getCustomerID() == id) {
//				 return cust;
//			 } 
//		} 
//		 return null;
//	}
//	 
//	 private Manager getAccountMNGFromListWithId(int id) {
//		 for (Manager manager : managers) { 
//			 if (manager.getManagerId() == id) {
//				 return manager;
//			 } 
//		} 
//		 return null;
//	}
	
	public abstract void addAccount() throws ValidationException, Account_Exception, SQLException;
	
	public abstract void searchByCustomerID(int id) throws SQLException;
	
	//public abstract void ShowAllAccounts() throws SQLException;
	
	//public abstract void deleteAccount(int accNum) throws SQLException;

}
