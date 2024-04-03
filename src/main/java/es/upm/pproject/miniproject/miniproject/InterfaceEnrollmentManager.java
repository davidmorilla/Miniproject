package es.upm.pproject.miniproject.miniproject;

import java.util.Collection;
import java.util.List;

public interface InterfaceEnrollmentManager {

	public void registerCourse(int code, String name, String coordinator) throws CourseAlreadyExistsException, CourseBlankInputException;
	
	public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException;

	public void enroll(int courseCode, int studentId) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException;
	
	public List<Student> getStudentsEnrolledInCourse(int course) throws MissingCourseException;
	
	public void cancelEnrollment(int courseCode, int studentId) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException;
	
	public void restartCourse(int courseCode) throws MissingCourseException;
	
	public Collection<Student> getStudents();
	
	public Collection<Course> getCourses();
}
