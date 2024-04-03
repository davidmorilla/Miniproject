package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to create a student with an e-mail that does not contain a '@' or that ends with a '.'.
 *
 */
public class EmailFormatException extends Exception {
	
	/**
	 * {@summary Constructs an exception of type EmailFormatException}
	 */
	public EmailFormatException(){
		super("Error: email must contain '@' and can not end with the character '.'");
	}    
}
