package es.upm.pproject.miniproject.miniproject;

public class MissingStudentException extends Exception{
	public MissingStudentException() {
		super("Error: The student does not exist.");
	}
}
