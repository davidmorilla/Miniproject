package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to enroll a student that has already been enrolled.
 *
 */
public class StudentAlreadyEnrolledException extends Exception{
	
	
	/**
	 * {@summary Constructs an exception of type StudentAlreadyEnrolledException}
	 */
	public StudentAlreadyEnrolledException() {
		super("Error: The student is already enrolled in the course");
	}
}
