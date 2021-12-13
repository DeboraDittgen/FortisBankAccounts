package bus;

public class ValidationException extends Exception {
	
	private static String message = "Non Valid!" ;
		
		public ValidationException()
		{
			super(message) ;
		}
		
		public ValidationException(String msg)
		{
		 super (msg);
		}

}
