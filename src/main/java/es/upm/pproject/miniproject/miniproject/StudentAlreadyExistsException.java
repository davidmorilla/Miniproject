package es.upm.pproject.miniproject.miniproject;
/**
 * 
 * Thrown when an application attempts to register a student that was already registered.
 *
 */
public class StudentAlreadyExistsException extends Exception{
	/**
	 * {@summary Constructs an exception of type StudentAlreadyExistsException.}
	 */
	public StudentAlreadyExistsException() {
		super("Error: The student already exists.");
	}
}
