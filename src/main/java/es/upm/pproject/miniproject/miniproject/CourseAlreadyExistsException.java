package es.upm.pproject.miniproject.miniproject;

public class CourseAlreadyExistsException extends Exception{
	public CourseAlreadyExistsException() {
		super("Error: The course already exists.");
	}
}
