package bus;

import java.util.Scanner;

public class Auth {

	private boolean authenticated = false;

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void login() {
		if (isAuthenticated()) {
			return;
		}

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		String user = "123";
		final int chances = 3;
		int attempts = 0;

		System.out.print("Password:(try 123) > ");
		String password = scanner.nextLine();
		
		authenticated = password.equals(user);

		if (authenticated) {
			System.out.println("user successfully logged! ");

		} else {
			do {

				System.out.println("Sorry, invalid password! ");
				System.out.print("Password: ");
				password = scanner.nextLine();
				attempts++;

			} while (attempts <= chances);
		}

		if ((attempts > chances) && (!authenticated)) {
			System.out.println("Sorry, you exceded the chances to login, contact the admin. ");
		}
	}
}
