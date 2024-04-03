package es.upm.pproject.miniproject.miniproject;
/**
 * 
 * Thrown when an application attempts to operate with a student that has not been registered.
 * <br>This includes:
 * <br><li> Enrolling a student in a course.
 * <br><li> Canceling a student's enrollment in a course.
 *
 */
public class MissingStudentException extends Exception{
	/**
	 * {@summary Constructs an exception of type MissingStudentException}
	 */
	public MissingStudentException() {
		super("Error: The student does not exist.");
	}
}
