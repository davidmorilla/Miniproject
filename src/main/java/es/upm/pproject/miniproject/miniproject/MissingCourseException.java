package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to operate with a course that has not been registered.
 * <br>This includes:
 * <br><li> Enrolling a student in a course.
 * <br><li> Getting a list of students enrolled in a course.
 * <br><li> Canceling a student's enrollment in a course.
 * <br><li> Restarting a course.
 * 
 *
 */
public class MissingCourseException extends Exception{
	
	/**
	 * {@summary Constructs an exception of type MissingCourseException}
	 */
	public MissingCourseException() {
		super("Error: The course does not exist.");
	}
}
