package bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
	
	private int accountNumber;
	private int customerID;
	private int transaction_no;
	private String transaction_type;
	private double transaction_amount;

	private static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

	public Transaction() {
	}

	public Transaction(int accountNumber, int customerID, int transaction_no, String transaction_type,
			double transaction_amount) {
		super();
		this.accountNumber = accountNumber;
		this.customerID = customerID;
		this.transaction_no = transaction_no;
		this.transaction_type = transaction_type;
		this.transaction_amount = transaction_amount;
	}

	public int getTransaction_no() {
		return transaction_no;
	}

	public void setTransaction_no(int transaction_no) {
		this.transaction_no = getTransaction_no() + 1;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	@Override
	public String toString() {
		return "Transaction [accountNumber =" + accountNumber + ", customerID =" + customerID + ", transaction_no="
				+ transaction_no + ", transaction_type=" + transaction_type
				+ ", transaction_amount=" + transaction_amount + ", transactionList=" + transactionList + "]";
	}

	// Methods
	public void withdraw(int accNum, double w) throws ValidationException, SQLException {
		Account searchedAccount;
		Transaction aTrans = new Transaction();

		searchedAccount = AccountService.search2(accNum);

		if (accNum == searchedAccount.getAccountNumber()) {
		
			// Checks if the value is less than or equal to 0, otherwise no withdrawal will be made.
			if (w <= 0) {
				//System.out.println("No withdrawals can be made with a value of 0 (zero) or less.\n");
				return;
			}
			if (w <= searchedAccount.getAccountBalance()) {
				double value = searchedAccount.getAccountBalance() - w;
				searchedAccount.setAccountBalance(value);
				  
				AccountService.updateAccount(searchedAccount);
				
				int searchedCustId = searchedAccount.getCustomerID();
				int searchedAccountNum = searchedAccount.getAccountNumber();
				
				aTrans.setCustomerID(searchedCustId);
				aTrans.setAccountNumber(searchedAccountNum);
				aTrans.setTransaction_amount(value);
				aTrans.setTransaction_type(Transaction_Type.Withdraw.toString());
				aTrans.setTransaction_no(1);
				
				TransactionService.insert(aTrans);
					
			} else {
				//System.out.println("Insufficient balance" + "\nTry again");
			}
		}
	}

	public void deposit(int accNum, double d) throws ValidationException, SQLException {

		Account searchedAccount;
		Transaction aTrans = new Transaction();
		searchedAccount = AccountService.search2(accNum);

		if (accNum == searchedAccount.getAccountNumber()) {
			// Checks if the value is less than or equal to 0, otherwise no deposits will be made.
			if (d <= 0) {
				return;
			}
			if (d >= 0) {
				double value = searchedAccount.getAccountBalance() + d;
				searchedAccount.setAccountBalance(value);
				
				AccountService.updateAccount(searchedAccount);
				//System.out.println("Withdrawal of $" + d + " was successfully performed!" 
					//	+ "\nCurrent balance: $" + searchedAccount.getAccountBalance());
				
				int searchedCustId = searchedAccount.getCustomerID();
				int searchedAccountNum = searchedAccount.getAccountNumber();
				
				aTrans.setCustomerID(searchedCustId);
				aTrans.setAccountNumber(searchedAccountNum);
				aTrans.setTransaction_amount(value);
				aTrans.setTransaction_type(Transaction_Type.Deposit.toString());
				aTrans.setTransaction_no(1);
				
				TransactionService.insert(aTrans);
			} else {
				//System.out.println("Insufficient balance" + "\nTry again");
			}
		}
	}

	public void printTransactions(int accNum) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		Transaction searchedTrans = new Transaction();

//		System.out.println("\n__________Transaction__________");
//		System.out.println("\n Please, type the Account Number>");
		
		//accNum = scanner.nextInt();
		searchedTrans = TransactionService.search2(accNum);
		int qt = 0;

		for(int i = 0; i < transactionList.size()+1; i++) {
			if(accNum == searchedTrans.getAccountNumber()) {
				System.out.println("\nYour Transactions: " 
					+ "\n_______________________________________________________________"
					+ "\nCustomer ID : " + searchedTrans.getCustomerID()
					+ "\nAccount number : " + searchedTrans.getAccountNumber() 
					+ "\nTransaction number : " + searchedTrans.getTransaction_no()
					+ "\nTransaction Type : " + searchedTrans.getTransaction_type()
					+ "\nAmmount : " + searchedTrans.getTransaction_amount()
					+ "\n_______________________________________________________________\n");
				
				qt++;

				System.out.println("Do you want to search another account Number? ");
				System.out.println("Type 1 to continue or any number to return to the main menu. ");

				int ans2 = scanner.nextInt();
				if (ans2 != 1) {
					break;
				}

				if (qt == 0) {
					System.out.println("Account number: "+ accNum + " not found!");
					System.out.println("Type 1 to continue or any number to return to the main menu. ");

					int ans3 = scanner.nextInt();
					if (ans3 != 1) {
						break;
					}
				}
			}
		}
	}
}
