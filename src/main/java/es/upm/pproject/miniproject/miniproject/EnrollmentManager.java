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
	
	public void registerCourse(int code, String name, String coordinator) {
		if(courses.get(code)==null) {
			try {
				courses.put(code, new Course(code, name, coordinator));
			} catch (BlankInputException e) {
				e.printStackTrace();
			}
			enrollment.put(code, new ArrayList<Student>());	
		}else {
			// excepcion de que ya existe
		}
	}
	
	public void registerStudent(int id, String name, String email) {
		if(students.get(id)==null) {
			students.put(id, new Student(id, name, email));
		}else {
			//throw exc.
		}
	}
	
	public void enroll(int course_code, int student_id) {
		if(students.get(student_id)!= null && courses.get(course_code)!=null) {
			List<Student> students_enrolled = enrollment.get(course_code);
			if(students_enrolled.size()<50) {
				if(!isEnrolled(student_id, students_enrolled)) {
					sortedInsert(course_code, student_id, students_enrolled);
				} else {
					//exc ya esta matriculado en ese curso
				}
			} else {
				//exc max 50 /curso
			}
		} else if(students.get(student_id)== null) {
			//exc el estudiante no existe
		} else {
			//exc el curso no existe
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

    private List<Student> getStudentsEnrolledInCourse(int course) {
    	if(courses.get(course)==null) {
    		//exc
    	}
       	return enrollment.get(course);
    }
    
    private void cancelEnrollment(int course_code, int student_id) {
    	if(students.get(student_id)!= null && courses.get(course_code)!=null) {
    		List<Student> enrolled = enrollment.get(course_code);
			if(isEnrolled(student_id, enrolled)) {
				enrolled.remove(students.get(student_id));
				enrollment.put(course_code, enrolled);
			} else {
				//exc no esta en el curso
			}
		} else if(students.get(student_id)== null) {
			//exc el estudiante no existe
		} else {
			//exc el curso no existe
		}
    }
    
    private void restartCourse(int course_code) {
    	enrollment.put(course_code, new ArrayList<Student>());
    }
    
    private Map<Integer, Student> getStudents() {
    	return students;
    }
    
    private Map<Integer, Course> getCourses() {
    	return courses;
    }
    
       
    
    
    
    
    
    
    
    
    
	public static void main(String[] args) {
    }
    
    
    
}
