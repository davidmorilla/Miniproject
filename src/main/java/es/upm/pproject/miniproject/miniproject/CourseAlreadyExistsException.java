package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to register a course that was already registered.
 *
 */
public class CourseAlreadyExistsException extends Exception{
	/**
	 * {@summary Constructs an exception of type CourseAlreadyExistsException}
	 */
	public CourseAlreadyExistsException() {
		super("Error: The course already exists.");
	}
}
