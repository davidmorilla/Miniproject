package es.upm.pproject.miniproject.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class allows you to create a course object. It contains the next attributes:
 * <br><li> <strong>Code</strong>, which must be a unique integer >0.
 * <br><li> <strong>Name</strong>, which must not be a blank or null string.
 * <br><li> <strong>Coordinator</strong>, which must not be a blank or null string.
 * 
 */
public class Course {

	private int code;
	private String name;
	private String coordinator;
	
	private static final Logger logger = LoggerFactory.getLogger(Course.class);
	
	/**
     * {@summary It creates a new course.}
     * @param code Specifies the unique course identifier. Must be a positive integer.
     * @param name Specifies the title of the course.
     * @param coordinator Specifies the name of the person coordinating the course.
     * @throws CourseBlankInputException when any of the input parameters are blank or null or the code is not a positive integer.
     * <br><li> An example of use is provided below: 
     * <br><li> {@code new Course(1, "Computer Science 101", "Coordinator 1")} will successfully create the course.
     * <br><li> {@code new Course(1, "", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(1, "Computer Science 101", null)} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(-1, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(0, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(1, null, "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(1, " ", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code new Course(1, "Computer Science 101", " ")} will throw a {@code CourseBlankInputException()}.
     */
	public Course(int code, String name, String coordinator) throws CourseBlankInputException {
		if(name == null || coordinator== null || code <= 0 || name.isBlank() || coordinator.isBlank()) {
			logger.error("\n\t----CourseBlankInputException(): Can't create course. The call contains blank or null inputs or the code is not a positive integer.");
			throw new CourseBlankInputException();
		}
		this.code = code;
		this.name = name;
		this.coordinator = coordinator;
	}
	
	/**
	 * 
	 * @return An integer corresponding to the unique code of the course.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 
	 * @return A string corresponding to the name of the course.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return A string corresponding to the name of the course's coordinator.
	 */
	public String getCoordinator() {
		return coordinator;
	}
	
	
}
