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
        logger.info(String.format("\n--Resgistering course...\n\tCode: %d\n\tName: %s\n\tCoordinator: %s.", code, name, coordinator));
        if(courses.get(code)==null) {
            try {
                logger.info(String.format("\n\t----Creating course %d ...", code));
                courses.put(code, new Course(code, name, coordinator));
            } catch(CourseBlankInputException cbie) {
                logger.error("\n\t----CourseBlankInputException(): Can't create course. The call contains blank inputs.");
                throw new CourseBlankInputException();
            }
            logger.info(String.format("\n\t----Course %d successfully created.", code));
            enrollment.put(code, new ArrayList<Student>());
            logger.info(String.format("\n--Course successfully registered in the system\n\tCode: %d\n\tName: %s\n\tCoordinator: %s.", code, name, coordinator));
        }else {
            logger.error(String.format("\n--CourseAlreadyExistsException(): Can't register course. Course with code: %d already registered in the system.", code));
            throw new CourseAlreadyExistsException();
        }
    }

    public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
        logger.info(String.format("\n--Registering student...\n\tIdentification: %d\n\tName: %s\n\tE-mail: %s.", id, name, email));
        if(students.get(id)==null) {
            try {
                logger.info(String.format("\n\t----Creating student %d ...", id));
                students.put(id, new Student(id, name, email));
            } catch(StudentBlankInputException sbie) {
                logger.error("\n\t----StudentBlankInputException(): Can't create student. The call contains blank inputs.");
                throw new StudentBlankInputException();
            } catch(EmailFormatException efe) {
                logger.error("\n\t----EmailFormatException(): Can't register student. The e-mail is not in the correct format.");
                throw new EmailFormatException();
            }
            logger.info(String.format("\n\t----Student %d successfully created.", id));
            logger.info(String.format("\n--Student successfully registered in the system\n\tIdentification: %d\n\tName: %s\n\tE-mail: %s.", id, name, email));
        }else {
            logger.error(String.format("\n--StudentAlreadyExistsException(): Can't register student. Student with identification: %d already registered in the system.", id));
            throw new StudentAlreadyExistsException();
        }
    }

    public void enroll(int course_code, int student_id) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException {
        logger.info(String.format("\n--Enrolling student...\n\tIdentification: %d\n\tin course:\n\tCode: %d.", student_id, course_code));
        if(students.get(student_id)!= null && courses.get(course_code)!=null) {
            List<Student> students_enrolled = enrollment.get(course_code);
            if(students_enrolled.size()<50) {
                if(!isEnrolled(student_id, students_enrolled)) {
                    sortedInsert(course_code, student_id, students_enrolled);
                    logger.info(String.format("\n--Student %d successfully enrolled in course %d.", student_id, course_code));
                } else {
                    logger.error("\n--StudentAlreadyEnrolledException(): Can't enroll student. Student already enrolled in the course." );
                    throw new StudentAlreadyEnrolledException();
                }
            } else {
                logger.error("\n--FullCourseException(): Can't enroll the student "+student_id+". There are already 50 students enrolled in " + course_code +".");
                throw new FullCourseException();
            }
        } else if(students.get(student_id)== null) {
            logger.error("\n--MissingStudentException(): Can't enroll student. Student " + student_id + " was not registered in the system.");
            throw new MissingStudentException();
        } else {
            logger.error("\n--MissingCourseException(): Can't enroll student. Course "+course_code+" was not registered in the system.");
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
        logger.info(String.format("\n--Getting students enrolled in course %d ...", course ));
        if(courses.get(course)==null) {
            logger.error("\n--MissingCourseException(): Can't get the students enrolled in the course. Course "+course+" was not registered in the system.");
            throw new MissingCourseException();
        }
        String log= "\n--Students enrolled in the course have been successfully obtained:\n";
        int i=0;
        if(0!=enrollment.get(course).size()) {
            for(i=0; i<enrollment.get(course).size()-1; i++) {
                Student s= new ArrayList<Student>(enrollment.get(course)).get(i);

                log += String.format("\tIdentification: %d\n\tName: %s\n\tE-mail: %s\n\n", s.getId(),s.getName(),s.getEmail_address()) ;
            }
            Student s= new ArrayList<Student>(enrollment.get(course)).get(i);
            log += String.format("\tIdentification: %d\n\tName: %s\n\tE-mail: %s.", s.getId(),s.getName(),s.getEmail_address()) ;
            logger.info(log);
        }else {
            log += "\tThe course is empty.";
            logger.info(log);
        }
           return enrollment.get(course);
    }

    public void cancelEnrollment(int course_code, int student_id) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException {
        logger.info(String.format("\n--Cancelling student's (%d) enrollment in course %d ...", student_id, course_code));
        if(students.get(student_id)!= null && courses.get(course_code)!=null) {
            List<Student> enrolled = enrollment.get(course_code);
            if(isEnrolled(student_id, enrolled)) {
                enrolled.remove(students.get(student_id));
                enrollment.put(course_code, enrolled);
                logger.info(String.format("\n--The student's (%d) enrollment in the course (%d) has been sucessfully cancelled.", student_id, course_code));
            } else {
                logger.error("\n--StudentNotEnrolledException(): Can't cancel enrollment. Student: "+ student_id +" was not enrolled in the course: " + course_code+ ".");
                throw new StudentNotEnrolledException();
            }
        } else if(students.get(student_id)== null) {
            logger.error("\n--MissingStudentException(): Can't cancel enrollment. Student "+student_id+" was not registered in the system.");
            throw new MissingStudentException();
        } else {
            logger.error("\n--MissingCourseException(): Can't cancel enrollment. Course "+course_code+" was not registered in the system.");
            throw new MissingCourseException();
        }
    }

    public void restartCourse(int course_code) throws MissingCourseException {
        logger.info(String.format("\n--Restarting course %d ...", course_code ));
        if(courses.get(course_code) != null) {
            enrollment.put(course_code, new ArrayList<Student>());
            logger.info("\n--The course "+course_code+" has been successfully restarted.");
        }else {
            logger.error("\n--MissingCourseException(): Can't restart course. Course "+course_code+" was not registered in the system.");
            throw new MissingCourseException();
        }
    }

    public Collection<Student> getStudents() {

        logger.info("\n--Students successfully obtained.");
        return students.values();
    }

    public Collection<Course> getCourses() {

        logger.info("\n--Courses successfully obtained.");
        return courses.values();
    }
}

