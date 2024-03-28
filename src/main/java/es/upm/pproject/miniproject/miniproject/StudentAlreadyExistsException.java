package es.upm.pproject.miniproject.miniproject;

public class StudentAlreadyExistsException extends Exception{
	public StudentAlreadyExistsException() {
		super("Error: The student already exists.");
	}
}
