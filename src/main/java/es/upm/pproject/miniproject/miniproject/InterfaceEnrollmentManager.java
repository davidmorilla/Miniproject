package es.upm.pproject.miniproject.miniproject;

import java.util.Collection;
import java.util.List;

public interface InterfaceEnrollmentManager {

	
    /**
     * {@summary It registers a new course.}
     * @
     * @param code Specifies the unique course identifier. Must be a positive integer.
     * @param name Specifies the title of the course.
     * @param coordinator Specifies the name of the person coordinating the course.
     * @throws CourseAlreadyExistsException when the course's code is already registered in the database.
     * @throws CourseBlankInputException when any of the input parameters are blank or null or the code is not a positive integer.
     * <br><li> An example of use is provided below: 
     * <br><li> {@code registerCourse(1, "Computer Science 101", "Coordinator 1")} will successfully register the course.
     * <br><li> {@code registerCourse(1, "", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(1, "Computer Science 101", null)} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(-1, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(0, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(1, null, "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(1, " ", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * <br><li> {@code registerCourse(1, "Computer Science 101", " ")} will throw a {@code CourseBlankInputException()}.
     */
	public void registerCourse(int code, String name, String coordinator) throws CourseAlreadyExistsException, CourseBlankInputException;
	
    /**
     * {@summary It registers a new student.}
     * @param id Specifies the unique student identifier. Must be a positive integer.
     * @param name Specifies the student's name.
     * @param email Specifies the student's e-mail address.
     * @throws StudentAlreadyExistsException when the student's id or e-mail is already registered in the database.
     * @throws StudentBlankInputException when any of the input parameters are blank or null or the id is not a positive integer.
     * @throws EmailFormatException when the email ends with a '.' or does not contain '@'.
     * <br><li> An example of use is provided below: 
     * <br><li> {@code registerStudent(1, "Student 1", "student1@example.com")} will successfully register the course.
     * <br><li> {@code registerStudent(-1, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(0, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, "", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, "Student 1", "")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, " ", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, "Student 1", " ")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, null, "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, "Student 1", null)} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code registerStudent(1, "Student 1", "student1.example.com")} will throw an {@code EmailFormatException}.
     * <br><li> {@code registerStudent(1, "Student 1", "student1@example.")} will throw an {@code EmailFormatException}.
     * 
     */
	public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException;

	/**
	 * {@summary It enrolls a student in a course.} 
	 * @param courseCode Specifies the unique course identifier of an already registered course.
	 * @param studentId Specifies the unique student identifier of an already registered student.
	 * @throws StudentAlreadyEnrolledException when the student is already enrolled in the course.
	 * @throws FullCourseException when there are already 50 students enrolled in the course.
	 * @throws MissingStudentException when the student's unique identifier is not registered in the database.
	 * @throws MissingCourseException  when the course's unique code is not registered in the database.
	 * @
	 */
	public void enroll(int courseCode, int studentId) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException;
	
    /**
     * {@summary It returns the sorted list of students enrolled in a course.}
     * @param course Specifies the course's unique code.
     * @returns List<Students> when the course is registered in the system. This list is sorted by their identifiers in an ascending order.
     * @throws MissingCourseException when the course is not registered in the system.
     */
	public List<Student> getStudentsEnrolledInCourse(int course) throws MissingCourseException;
	
    /**
     * {@summary It cancels a student's enrollment in a course.}
     * @param courseCode Specifies the course's unique code.
     * @param studentId Specifies the student's unique identifier.
     * @throws StudentNotEnrolledException when the student is not enrolled in the course.
     * @throws MissingStudentException when the student is not registered in the system.
     * @throws MissingCourseException when the course is not registered in the system.
     */
	public void cancelEnrollment(int courseCode, int studentId) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException;
	
    /**
     * {@summary It restarts a course, canceling any active enrollments in that course at the moment of the call.}
     * @param courseCode Specifies the course's unique code.
     * @throws MissingCourseException when the course is not registered in the system. 
     */
	public void restartCourse(int courseCode) throws MissingCourseException;
	
	  /**
	    * {@summary It returns a list containing all registered students in the system at the moment of the call.}
	    */
	public Collection<Student> getStudents();
	
	  /**
	    * {@summary It returns a list containing all registered courses in the system at the moment of the call.}
	    */
	public Collection<Course> getCourses();
}
