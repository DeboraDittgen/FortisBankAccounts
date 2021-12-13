package bus;

import java.util.ArrayList;
import java.util.Scanner;

import bus.Customer;
import bus.Manager;
import data.CheckingServiceDB;
import data.DataCollectionDB;

import java.sql.SQLException;

public class DataCollection {

	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static ArrayList<Manager> managers = new ArrayList<Manager>();

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(ArrayList<Customer> customers) {
		DataCollection.customers = customers;
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}

	public static void setAccounts(ArrayList<Account> accounts) {
		DataCollection.accounts = accounts;
	}

	public static ArrayList<Manager> getManangers() {
		return managers;
	}

	public static void setManangers(ArrayList<Manager> manangers) {
		DataCollection.managers = manangers;
	}
	
	public static int delete(int accNum) throws SQLException {
		return DataCollectionDB.delete(accNum);
	}

	public void searchByCustomerID(int id) throws SQLException {
		//Scanner scanner = new Scanner(System.in);
		Account searchedAccount;

		//System.out.println("\n__________Looking for a customer__________");
		//System.out.println("\n Please, type a customer ID number you are looking for >");

		//id = scanner.nextInt();
		searchedAccount = AccountService.search(id);
		int qt = 0;

		for (int i = 0; i < accounts.size() + 1; i++) {
			if (id == searchedAccount.getCustomerID()) {
//				System.out.println("\nOur system found the data fot this account " + "\nCustomer ID : "
//						+ searchedAccount.getCustomerID() + "\nAccount number : " + searchedAccount.getAccountNumber()
//						+ "\nAccount Type: " + searchedAccount.getAccountType() + "\nBalance : "
//						+ searchedAccount.getAccountBalance() + "\nOpened Date : " + searchedAccount.getCreationDate()
//						+ "\n_______________________________________________________________\n");

				qt++;

				System.out.println("Do you want to search another account Number? ");
				System.out.println("Type 1 to continue or any number to return to the main menu. ");

//				int ans2 = scanner.nextInt();
//				if (ans2 != 1) {
//					break;
//				}

//				if (qt == 0) {
//					System.out.println("Account number: " + id + " not found!");
//					System.out.println("Type 1 to continue or any number to return to the main menu. ");
//
//					int ans3 = scanner.nextInt();
//					if (ans3 != 1) {
//						break;
//					}
//				}
			}
		}
	}

	public void ShowAllAccounts() throws SQLException {
		System.out.println("\n__________ All Accounts __________");

		customers = new ArrayList<>();
		customers = Customer.getData();

		accounts = new ArrayList<>();
		accounts = AccountService.getData();

		for (Customer current : customers) {
			System.out.println(current);
		}
		for (Account current : accounts) {
			System.out.println(current);
		}
	}

	public void deleteAccount(int accNum) throws SQLException {

		Account searchedAccount;
		searchedAccount = AccountService.search(accNum);

		if (accNum == searchedAccount.getAccountNumber()) {
			if (searchedAccount.getAccountBalance() == 0) {
				DataCollectionDB.delete(accNum);
			} 
		} else {
			//System.out.println("Account not found!");
		}
	}
}
