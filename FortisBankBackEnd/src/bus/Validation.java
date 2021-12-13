package bus;


public class Validation {
	
	public static void isAlphabetic(String name) throws ValidationException {
		
		for(int i = 0; i != name.length(); i++) {
			
			if(!Character.isAlphabetic(name.charAt(i))) {
				throw new ValidationException("The name and last name must be only alphabet letter.");			
			}		
		}		
	}
	
	public static void notAlphabeticAndPositive(double account_balance) throws ValidationException {
		
		if( account_balance < 0){			
			throw new ValidationException("Sorry, the value needs to be positive.");
			
		}	
	}
}
