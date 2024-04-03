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

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentManager.class);
    
    private static final String MSG_NOT_REGISTERED = " was not registered in the system.";
    private static final String MSG_NAME = "\n\tName: ";
    private static final String MSG_EMAIL = "\n\tE-mail: ";
    
    
    /**
     * This constructor creates an object of type EnrollmentManager that will take care of managing the database.
     * 
     */
    public EnrollmentManager() {
        courses= new TreeMap<>();
        students= new TreeMap<>();
        enrollment= new HashMap<>();
    }
    
    /**
     * {@summary It registers a new course.}
     * @param code Specifies the unique course identifier. Must be a positive integer.
     * @param name Specifies the title of the course.
     * @param coordinator Specifies the name of the person coordinating the course.
     * @throws CourseAlreadyExistsException when the course's code is already registered in the database.
     * @throws CourseBlankInputException when any of the input parameters are blank or null or the code is not a positive integer.
     * @implNote An example of use is provided below: 
     * @implNote <li>{@code registerCourse(1, "Computer Science 101", "Coordinator 1")} will successfully register the course.
     * @implNote <li>{@code registerCourse(1, "", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(1, "Computer Science 101", null)} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(-1, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(0, "Computer Science 101", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(1, null, "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(1, " ", "Coordinator 1")} will throw a {@code CourseBlankInputException()}.
     * @implNote <li>{@code registerCourse(1, "Computer Science 101", " ")} will throw a {@code CourseBlankInputException()}.
     */
    public void registerCourse(int code, String name, String coordinator) throws CourseAlreadyExistsException, CourseBlankInputException {
    	String msglog = "\n--Resgistering course...\n\tCode: " + code + MSG_NAME + name + "\n\tCoordinator: " + coordinator + ".";
    	logger.info(msglog);
        if(courses.get(code)==null) {
            msglog = "\n\t----Creating course " + code + "...";
            logger.info(msglog);
                
            courses.put(code, new Course(code, name, coordinator));
            
            msglog = "\n\t----Course " + code + " successfully created.";
            logger.info(msglog);
            
            enrollment.put(code, new ArrayList<>());
            
            msglog = "\n--Course successfully registered in the system\n\tCode: " + code + MSG_NAME + name + "\n\tCoordinator: "+ coordinator + ".";
            logger.info(msglog);
        }else {
        	msglog = "\n--CourseAlreadyExistsException(): Can't register course. Course with code: " + code + " already registered in the system.";
            logger.error(msglog);
            throw new CourseAlreadyExistsException();
        }
    }

    /**
     * {@summary It registers a new student.}
     * @param id Specifies the unique student identifier. Must be a positive integer.
     * @param name Specifies the student's name.
     * @param email Specifies the student's e-mail address.
     * @throws StudentAlreadyExistsException when the student's id or e-mail is already registered in the database.
     * @throws StudentBlankInputException when any of the input parameters are blank or null or the id is not a positive integer.
     * @throws EmailFormatException when the email ends with a '.' or does not contain '@'.
     * @implNote An example of use is provided below: 
     * @implNote <li>{@code registerStudent(1, "Student 1", "student1@example.com")} will successfully register the course.
     * @implNote <li>{@code registerStudent(-1, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(0, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, "", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, "Student 1", "")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, " ", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, "Student 1", " ")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, null, "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, "Student 1", null)} will throw a {@code StudentBlankInputException()}.
     * @implNote <li>{@code registerStudent(1, "Student 1", "student1.example.com")} will throw an {@code EmailFormatException}.
     * @implNote <li>{@code registerStudent(1, "Student 1", "student1@example.")} will throw an {@code EmailFormatException}.
     * 
     */
    public void registerStudent(int id, String name, String email) throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
    	String msglog = "\n--Registering student...\n\tIdentification: " + id + MSG_NAME + name + MSG_EMAIL + email + ".";
        logger.info(msglog);
        if(students.get(id)==null) {
            msglog = "\n\t----Creating student " + id + "...";
            logger.info(msglog);
                
            students.put(id, new Student(id, name, email));
            
            msglog = "\n--Student successfully registered in the system\n\tIdentification: " + id + MSG_NAME + name + MSG_EMAIL + email + ".";
            logger.info(msglog);
        }else {
        	msglog = "\n--StudentAlreadyExistsException(): Can't register student. Student with identification: " + id + " already registered in the system.";
            logger.error(msglog);
            
            throw new StudentAlreadyExistsException();
        }
    }
	/**
	 * {@summary It enrolls a student in a course.} 
	 * @param course_code Specifies the unique course identifier of an already registered course.
	 * @param student_id Specifies the unique student identifier of an already registered student.
	 * @throws StudentAlreadyEnrolledException when the student is already enrolled in the course.
	 * @throws FullCourseException when there are already 50 students enrolled in the course.
	 * @throws MissingStudentException when the student's unique identifier is not registered in the database.
	 * @throws MissingCourseException  when the course's unique code is not registered in the database.
	 * @
	 */
    public void enroll(int courseCode, int studentId) throws StudentAlreadyEnrolledException, FullCourseException, MissingStudentException, MissingCourseException {
    	String msglog = "\n--Enrolling student...\n\tIdentification: " + studentId + "\n\tin course:\n\tCode: " + courseCode + ".";
        logger.info(msglog);
        if(students.get(studentId)!= null && courses.get(courseCode)!=null) {
            List<Student> studentsEnrolled = enrollment.get(courseCode);
            if(studentsEnrolled.size()<50) {
                if(!isEnrolled(studentId, studentsEnrolled)) {
                    sortedInsert(courseCode, studentId, studentsEnrolled);
                    msglog = "\n--Student " + studentId + " successfully enrolled in course " + courseCode + ".";
                    logger.info(msglog);
                } else {
                    logger.error("\n--StudentAlreadyEnrolledException(): Can't enroll student. Student already enrolled in the course.");
                    throw new StudentAlreadyEnrolledException();
                }
            } else {
            	msglog = "\n--FullCourseException(): Can't enroll the student "+studentId+". There are already 50 students enrolled in " + courseCode +".";
                logger.error(msglog);
                throw new FullCourseException();
            }
        } else if(students.get(studentId)== null) {
        	msglog = "\n--MissingStudentException(): Can't enroll student. Student " + studentId + MSG_NOT_REGISTERED;
            logger.error(msglog);
            throw new MissingStudentException();
        } else {
        	msglog = "\n--MissingCourseException(): Can't enroll student. Course "+ courseCode + MSG_NOT_REGISTERED;
            logger.error(msglog);
            throw new MissingCourseException();
        }
    }
    /**
     * {@summary It enrolls a student in a course in a sorted way.}
     * @param course_code Specifies the course's unique code.
     * @param student_id Specifies the student's unique identifier.
     * @param students_enrolled Specifies the list of students already enrolled in the course.
     */
    private void sortedInsert(int courseCode, int studentId, List<Student> studentsEnrolled) {
        int a = 0;
        int b = studentsEnrolled.size() - 1;
        while(a<=b) {
            int middle = (a + b)/2;
            if(studentsEnrolled.get(middle).getId()<=studentId) {
                a = middle + 1;
            } else {
                b = middle -1;
            }
        }
        studentsEnrolled.add(a,students.get(studentId));
        enrollment.put(courseCode, studentsEnrolled);
    }
    /**
     * 
     * @param student_id Specifies the student's unique identifier.
     * @param students_enrolled Specifies the list of students already enrolled in the course.
     * @return <strong>true</strong> if the student is enrolled in the course and <strong>false</strong> otherwise.
     */
    private boolean isEnrolled(int studentId, List<Student> studentsEnrolled) {
        boolean enrolled = false;
        int i = 0;
        while(i<studentsEnrolled.size() && !enrolled) {
            if(studentsEnrolled.get(i).getId() == studentId) {
                enrolled = true;
            }
            i++;
        }
        return enrolled;
    }

    /**
     * 
     */
    public List<Student> getStudentsEnrolledInCourse(int course) throws MissingCourseException {
    	String msglog = "\n--Getting students enrolled in course " + course + "...";
        logger.info(msglog);
        if(courses.get(course)==null) {
        	msglog = "\n--MissingCourseException(): Can't get the students enrolled in the course. Course "+ course + MSG_NOT_REGISTERED;
            logger.error(msglog);
            throw new MissingCourseException();
        }
        msglog= "\n--Students enrolled in the course have been successfully obtained:\n";
        int i=0;
        if(!enrollment.get(course).isEmpty()) {
            for(i=0; i<enrollment.get(course).size()-1; i++) {
                Student s= new ArrayList<Student>(enrollment.get(course)).get(i);
                
                StringBuilder sb = new StringBuilder();
                sb.append("\tIdentification: ").append(s.getId()).append(MSG_NAME)
                  .append(s.getName()).append(MSG_EMAIL).append(s.getEmailAddress()).append(".\n\n");
                msglog = msglog.concat(sb.toString());
            }
            Student s= new ArrayList<Student>(enrollment.get(course)).get(i);
            msglog += "\tIdentification: " + s.getId() + MSG_NAME + s.getName() + MSG_EMAIL + s.getEmailAddress() + ".";
            logger.info(msglog);
        }else {
            msglog += "\tThe course is empty.";
            logger.info(msglog);
        }
           return enrollment.get(course);
    }

    public void cancelEnrollment(int courseCode, int studentId) throws StudentNotEnrolledException, MissingStudentException, MissingCourseException {
        String msglog = "\n--Cancelling student's (" + studentId + ") enrollment in course " + courseCode + "...";
    	logger.info(msglog);
        if(students.get(studentId)!= null && courses.get(courseCode)!=null) {
            List<Student> enrolled = enrollment.get(courseCode);
            if(isEnrolled(studentId, enrolled)) {
                enrolled.remove(students.get(studentId));
                enrollment.put(courseCode, enrolled);
                msglog = "\n--The student's (" + studentId + ") enrollment in the course (" + courseCode + ") has been sucessfully cancelled.";
                logger.info(msglog);
            } else {
            	msglog = "\n--StudentNotEnrolledException(): Can't cancel enrollment. Student: "+ studentId +" was not enrolled in the course: " + courseCode+ ".";
                logger.error(msglog);
                throw new StudentNotEnrolledException();
            }
        } else if(students.get(studentId)== null) {
        	msglog = "\n--MissingStudentException(): Can't cancel enrollment. Student "+ studentId + MSG_NOT_REGISTERED;
            logger.error(msglog);
            throw new MissingStudentException();
        } else {
        	msglog = "\n--MissingCourseException(): Can't cancel enrollment. Course "+ courseCode + MSG_NOT_REGISTERED;
            logger.error(msglog);
            throw new MissingCourseException();
        }
    }

    public void restartCourse(int courseCode) throws MissingCourseException {
    	String msglog = "\n--Restarting course " + courseCode + "...";
        logger.info(msglog);
        if(courses.get(courseCode) != null) {
            enrollment.put(courseCode, new ArrayList<>());
            msglog = "\n--The course "+courseCode+" has been successfully restarted.";
            logger.info(msglog);
        }else {
        	msglog = "\n--MissingCourseException(): Can't restart course. Course "+ courseCode + MSG_NOT_REGISTERED;
            logger.error(msglog);
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