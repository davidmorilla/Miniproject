package es.upm.pproject.miniproject.miniproject;

public class FullCourseException extends Exception {
	public FullCourseException() {
		super("Error: There are already 50 students enrolled in this course.");
	}
}
