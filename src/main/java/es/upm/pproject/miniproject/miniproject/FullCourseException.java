package es.upm.pproject.miniproject.miniproject;

/**
 * 
 * Thrown when an application attempts to enroll a student in a course that already has 50 students enrolled in it.
 *
 */
public class FullCourseException extends Exception {
	
	/**
	 * {@summary Constructs an exception of type FullCourseException}
	 */
	public FullCourseException() {
		super("Error: There are already 50 students enrolled in this course.");
	}
}
