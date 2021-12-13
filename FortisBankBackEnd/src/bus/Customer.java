package bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.ToIntFunction;

import data.CustomerDB;

public class Customer {

	private int customer_id;
	private String firstName;
	private String lastName;
	private int pin;
	private int manager_id;
	
	public static ArrayList<Customer> customers = new ArrayList<Customer>();

	public Customer( ) {
		super();
	}
		
	public Customer(int customerID, String firstName, String lastName, int pin, int managerID) {
		super();
		this.customer_id = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.manager_id = managerID;
	}

	public int getCustomerID() {
		return customer_id;
	}

	public void setCustomerID(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstName() {	
		return firstName;
	}

	public void setFirstName(String firstName) throws ValidationException {
		Validation.isAlphabetic(firstName);
		this.firstName = firstName;
	}

	public int getManagerID() {
		return manager_id;
	}

	public void setManagerID(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws ValidationException {	
		Validation.isAlphabetic(lastName);
		this.lastName = lastName;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Customer [customer_id = " + customer_id + ", manager_id = " + manager_id + ", firstName = " + firstName
				+ ", lastName = " + lastName + ", pin = " + pin + "]";
	}

	public static int add(Customer element) throws SQLException {
		return CustomerDB.insert(element);
	}

	public static int remove(int id) throws SQLException {
		return CustomerDB.delete(id);
	}
	
	public static Customer search(int id) throws SQLException, SQLException {
		return CustomerDB.search(id);
	}
	
	public static ArrayList<Customer> getData() throws SQLException, SQLException {
		return CustomerDB.select();
	}
}
