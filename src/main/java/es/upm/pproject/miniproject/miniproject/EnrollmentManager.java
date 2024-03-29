package es.upm.pproject.miniproject.miniproject;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EnrollmentManager {
	private Map<Integer, Course> courses;
	private Map<Integer, Student> students;
	private Map<Integer, List<Student>> enrollment;
	
	public EnrollmentManager() {
		courses= new TreeMap<Integer, Course>();
		students= new TreeMap<Integer, Student>();
		enrollment= new HashMap<Integer, List<Student>>();
	}
	
	public void registerCourse(int code, String name, String coordinator) throws CourseAlreadyExistsException, CourseBlankInputException {
		if(courses.get(code)==null) {
			courses.put(code, new Course(code, name, coordinator));
			enrollment.put(code, new ArrayList<Student>());	
		}else {
			throw new CourseAlreadyExistsException();
		}
	}
	
	public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
		if(students.get(id)==null) {
			students.put(id, new Student(id, name, email));
		}else {
			throw new StudentAlreadyExistsException();
		}
	}
	
	public void enroll(int course_code, int student_id) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException {
		if(students.get(student_id)!= null && courses.get(course_code)!=null) {
			List<Student> students_enrolled = enrollment.get(course_code);
			if(students_enrolled.size()<50) {
				if(!isEnrolled(student_id, students_enrolled)) {
					sortedInsert(course_code, student_id, students_enrolled);
				} else {
					throw new StudentAlreadyEnrolledException();
				}
			} else {
				throw new FullCourseException();
			}
		} else if(students.get(student_id)== null) {
			throw new MissingStudentException();
		} else {
			throw new MissingCourseException();
		}
	}
		
    private void sortedInsert(int course_code, int student_id, List<Student> students_enrolled) {
		int a = 0;
		int b = students_enrolled.size() - 1;
		while(a<=b) {
			int middle = (a + b)/2;
			if(students_enrolled.get(middle).getId()<=student_id) {
				a = middle + 1;
			} else {
				b = middle -1;
			}
		}
		students_enrolled.add(a,students.get(student_id));
		enrollment.put(course_code, students_enrolled);
	}

	private boolean isEnrolled(int student_id, List<Student> students_enrolled) {
		boolean enrolled = false;
		int i = 0;
		while(i<students_enrolled.size() && !enrolled) {
			if(students_enrolled.get(i).getId() == student_id) {
				enrolled = true;
			}
			i++;
		}
		return enrolled;
	}

    public List<Student> getStudentsEnrolledInCourse(int course) throws MissingCourseException {
    	if(courses.get(course)==null) {
    		throw new MissingCourseException();
    	}
       	return enrollment.get(course);
    }
    
    public void cancelEnrollment(int course_code, int student_id) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException {
    	if(students.get(student_id)!= null && courses.get(course_code)!=null) {
    		List<Student> enrolled = enrollment.get(course_code);
			if(isEnrolled(student_id, enrolled)) {
				enrolled.remove(students.get(student_id));
				enrollment.put(course_code, enrolled);
			} else {
				throw new StudentNotEnrolledException();
			}
		} else if(students.get(student_id)== null) {
			throw new MissingStudentException();
		} else {
			throw new MissingCourseException();
		}
    }
    
    public void restartCourse(int course_code) throws MissingCourseException {
    	if(courses.get(course_code) != null) {
    		enrollment.put(course_code, new ArrayList<Student>());
    	}else {
    		throw new MissingCourseException();
    	}
    }
    
    public Map<Integer, Student> getStudents() {
    	return students;
    }
    
    public Map<Integer, Course> getCourses() {
    	return courses;
    }    
}
