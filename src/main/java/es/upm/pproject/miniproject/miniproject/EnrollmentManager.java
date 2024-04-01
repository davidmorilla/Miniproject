package es.upm.pproject.miniproject.miniproject;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrollmentManager implements InterfaceEnrollmentManager{
	private Map<Integer, Course> courses;
	private Map<Integer, Student> students;
	private Map<Integer, List<Student>> enrollment;
	
	private static final Logger logger =
			 LoggerFactory.getLogger(EnrollmentManager.class);
	
	public EnrollmentManager() {
		courses= new TreeMap<Integer, Course>();
		students= new TreeMap<Integer, Student>();
		enrollment= new HashMap<Integer, List<Student>>();
	}
	
	public void registerCourse(int code, String name, String coordinator) throws CourseAlreadyExistsException, CourseBlankInputException {
		logger.info(String.format("--Resgistering course...\n\tCode: %d\n\tName: %s\n\tCoordinator: %s", code, name, coordinator));
		if(courses.get(code)==null) {
			courses.put(code, new Course(code, name, coordinator));
			enrollment.put(code, new ArrayList<Student>());	
			logger.info(String.format("--Course registered in the system\n\tCode: %d\n\tName: %s\n\tCoordinator: %s", code, name, coordinator));
		}else {
			logger.error(String.format("Course with code: %d already registered in the system", code));
			throw new CourseAlreadyExistsException();
		}
	}
	
	public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
		logger.info(String.format("--Registering student...\n\tIdentification: %d\n\tName: %s\n\tE-mail: %s", id, name, email));
		if(students.get(id)==null) {
			students.put(id, new Student(id, name, email));
			logger.info(String.format("--Student registered in the system\n\tIdentification: %d\n\tName: %s\n\tE-mail: %s", id, name, email));
		}else {
			logger.error(String.format("--Student with identification: %s already registered in the system", id));
			throw new StudentAlreadyExistsException();
		}
	}
	
	public void enroll(int course_code, int student_id) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException {
		logger.info(String.format("--Enrolling student...\n\tIdentification: %d\n in course\n\tCode: %d", student_id, course_code));
		if(students.get(student_id)!= null && courses.get(course_code)!=null) {
			List<Student> students_enrolled = enrollment.get(course_code);
			if(students_enrolled.size()<50) {
				if(!isEnrolled(student_id, students_enrolled)) {
					sortedInsert(course_code, student_id, students_enrolled);
					logger.info(String.format("--Student \n\tIdentification: %d\n enrolled in course\n\tCode: %d", student_id, course_code));
				} else {
					logger.error("--Student already enrolled in the course" );
					throw new StudentAlreadyEnrolledException();
				}
			} else {
				logger.error("--There are already 50 students enrolled in " + course_code +".");
				throw new FullCourseException();
			}
		} else if(students.get(student_id)== null) {
			logger.error("--Student" + student_id + " was not registered in the system");
			throw new MissingStudentException();
		} else {
			logger.error("--Course"+course_code+" was not registered in the system");
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
    		logger.error("--Course"+course+" was not registered in the system");
    		throw new MissingCourseException();
    	}
    	String a= "--Students enrolled in the course have been obtained:\n";
    	
    	for(int i=0; i<enrollment.get(course).size(); i++) {
    		Student s= new ArrayList<Student>(enrollment.get(course)).get(i);
    		
    		a += String.format("\tIdentification: %d\n\tName: %s\n\tE-mail: %s\n\n", s.getId(),s.getName(),s.getEmail_address()) ;
    	}
    	logger.info(a);
       	return enrollment.get(course);
    }
    
    public void cancelEnrollment(int course_code, int student_id) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException {
    	if(students.get(student_id)!= null && courses.get(course_code)!=null) {
    		List<Student> enrolled = enrollment.get(course_code);
			if(isEnrolled(student_id, enrolled)) {
				enrolled.remove(students.get(student_id));
				enrollment.put(course_code, enrolled);
				logger.info("The student's enrollment in the course has been cancelled");
			} else {
				logger.error("Student: "+ students.get(student_id).getName() +" was not enrolled in the course: " + courses.get(course_code).getName());
				throw new StudentNotEnrolledException();
			}
		} else if(students.get(student_id)== null) {
			logger.error("Student was not registered in the system");
			throw new MissingStudentException();
		} else {
			logger.error("Course was not registered in the system");
			throw new MissingCourseException();
		}
    }
    
    public void restartCourse(int course_code) throws MissingCourseException {
    	if(courses.get(course_code) != null) {
    		enrollment.put(course_code, new ArrayList<Student>());
    		logger.info("The course has been restarted");
    	}else {
    		logger.error("Course was not registered in the system");
    		throw new MissingCourseException();
    	}
    }
    
    public Collection<Student> getStudents() {
    	logger.info("Students obtained");
    	
    	return students.values();
    }
    
    public Collection<Course> getCourses() {

    	logger.info("Courses obtained");
    	return courses.values();
    }    
}

