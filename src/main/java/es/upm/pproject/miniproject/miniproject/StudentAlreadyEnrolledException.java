package es.upm.pproject.miniproject.miniproject;

public class StudentAlreadyEnrolledException extends Exception{
	public StudentAlreadyEnrolledException() {
		super("Error: The student is already enrolled in the course");
	}
}
