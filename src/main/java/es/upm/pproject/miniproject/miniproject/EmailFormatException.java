package es.upm.pproject.miniproject.miniproject;

public class EmailFormatException extends Exception {
	
	public EmailFormatException(){
		super("Error: email must contain '@' and can not end with the character '.'");
	}    
}
