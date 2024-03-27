package es.upm.pproject.miniproject.miniproject;

public class MissingCourseException extends Exception{
	public MissingCourseException() {
		super("Error: The course does not exist.");
	}
}
