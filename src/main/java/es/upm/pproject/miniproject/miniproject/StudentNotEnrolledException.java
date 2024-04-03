package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to cancel a student's enrollment in a course in which the student was not previously enrolled.
 *
 */
public class StudentNotEnrolledException extends Exception{
	
	/**
	 * {@summary Constructs an exception of type StudentNotEnrolledException}
	 */
	public StudentNotEnrolledException() {
		super("Error: The student is not enrolled in the course");
	}
}
