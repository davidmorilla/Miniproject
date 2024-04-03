package es.upm.pproject.miniproject.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Course {
	private int code;
	private String name;
	private String coordinator;
	
	private static final Logger logger = LoggerFactory.getLogger(Course.class);
	
	public Course(int code, String name, String coordinator) throws CourseBlankInputException {
		if(code <= 0 || name.isBlank() || coordinator.isBlank()) {
			logger.error("\n\t----CourseBlankInputException(): Can't create course. The call contains blank or null inputs or the code is not a positive integer.");
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
