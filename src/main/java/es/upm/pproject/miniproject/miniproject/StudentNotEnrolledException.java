package es.upm.pproject.miniproject.miniproject;

public class StudentNotEnrolledException extends Exception{
	public StudentNotEnrolledException() {
		super("Error: The student is not enrolled in the course");
	}
}
