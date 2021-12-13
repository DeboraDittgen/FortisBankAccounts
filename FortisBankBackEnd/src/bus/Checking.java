package bus;

import java.sql.SQLException;
import java.util.Scanner;

public class Checking extends Account{
	

	public Checking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Checking(String accountType, int accountNumber, double accountBalance, String creationDate, int customerID) {
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

//	private Customer getAccountFromListWithId(int id) {
//	for (Customer cust : customers) { 
//		 if (cust.getCustomerID() == id) {
//			 return cust;
//		 } 
//	} 
//	 return null;
//	}

	public void addAccount(String firstName, String lastName, int id, int pin, int accountNumber, double balance, String creationDate, int managerID) throws ValidationException, Account_Exception, SQLException {
		
		for(int i = 0; i < accounts.size() +1; i++) {
			
			Account aAccount = new Checking();
			Customer aCustomer = new Customer();
			
			aAccount.setAccountType(AccountTypes.CHECKING.toString());
			aCustomer.setCustomerID(id);
			aAccount.setCustomerID(id);
			aCustomer.setPin(pin);

			boolean valid = false;
			
			do {
				try {
					aCustomer.setFirstName(firstName);
					valid = true;					
				}catch(ValidationException e) {
					System.out.println(e.getMessage());
				}		
			}while(!valid);
			
			valid = false;
			
			do {
				try {
					aCustomer.setLastName(lastName);
					valid = true;		
				}catch(ValidationException e) {
					System.out.println(e.getMessage());		
				}			
			}while(!valid);
			
			valid = false;
			
			do {
				try {
					aAccount.setAccountNumber(accountNumber);
					
					Account accFound = getAccountFromListWithAccountNumber(aAccount.getAccountNumber());
					if(accFound != null) {
						throw new Account_Exception("Null number");
					}
					valid = true;	
				}catch(Account_Exception ex) {
					valid = false;		
				}			
			}while(!valid);
			
			valid = false;
			
			do {
				aAccount.setAccountBalance(balance);		
				valid = true;
			}while(!valid);
 
			aAccount.setCreationDate(creationDate);
			aCustomer.setManagerID(managerID);
			
			AccountService.insert(aAccount);
			Customer.add(aCustomer);
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
