package bus;

import java.sql.SQLException;
import java.util.ArrayList;

import data.ManagerDB;

public class Manager {
	
	private int managerId;
	private String firstname;
	private String lastName;
	
	public Manager() {
		super();
	}

	public Manager(int managerId, String firstname, String lastName) {
		super();
		this.managerId = managerId;
		this.firstname = firstname;
		this.lastName = lastName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) throws ValidationException {
		Validation.isAlphabetic(firstname);
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws ValidationException {
		Validation.isAlphabetic(lastName);
		this.lastName = lastName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Management [firstname=" + firstname + ", lastName=" + lastName + ", managerId=" + managerId + "]";
	}

	public static int add(Manager element) throws SQLException {
		return ManagerDB.insert(element);
	}

	public static int remove(int id) throws SQLException {
		return ManagerDB.delete(id);
	}
	
	public static Manager search(int id) throws SQLException, SQLException {
		return ManagerDB.search(id);
	}
	
	public static ArrayList<Manager> getData() throws SQLException, SQLException {
		return ManagerDB.select();
	}
}
