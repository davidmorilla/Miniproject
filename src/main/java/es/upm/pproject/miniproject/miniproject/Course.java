package es.upm.pproject.miniproject.miniproject;

public class Course {
	private int code;
	private String name;
	private String coordinator;
	
	public Course(int code, String name, String coordinator) throws BlankInputException {
		if(code <= 0 || name.isBlank() || coordinator.isBlank()) {
			throw new BlankInputException();
		}
		this.code = code;
		this.name = name;
		this.coordinator = coordinator;
	}
}
