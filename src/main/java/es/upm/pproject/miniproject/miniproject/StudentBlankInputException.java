package es.upm.pproject.miniproject.miniproject;
/**
 * 
 * Thrown when an application attempts to create a student with one or more blank or null fields or a student identifier <=0.
 *
 */
public class StudentBlankInputException extends Exception {
	
	/**
	 * {@summary Constructs an exception of type StudentBlankInputException}
	 */
	public StudentBlankInputException(){
		super("Error: id_number, name and email cannot be blank. id_number must be >0");
	}    
}
