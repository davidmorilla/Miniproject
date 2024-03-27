package es.upm.pproject.miniproject.miniproject;

public class EnrollmentException extends Exception{
	public EnrollmentException(String a) {
		super("Error: The student is " + a + " enrolled in the course");
	}
}
