package es.upm.pproject.miniproject.miniproject;

public class Course {
	private int code;
	private String name;
	private String coordinator;
	
	public Course(int code, String name, String coordinator) throws CourseBlankInputException {
		if(code <= 0 || name.isBlank() || coordinator.isBlank()) {
			throw new CourseBlankInputException();
		}
		this.code = code;
		this.name = name;
		this.coordinator = coordinator;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getCoordinator() {
		return coordinator;
	}
	
	
}
