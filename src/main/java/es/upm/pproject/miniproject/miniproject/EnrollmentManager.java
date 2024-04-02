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
    
    
    
    /**
     * This constructor creates an object of type EnrollmentManager that will take care of managing the database.
     * 
     */
    public EnrollmentManager() {
        courses= new TreeMap<Integer, Course>();
        students= new TreeMap<Integer, Student>();
        enrollment= new HashMap<Integer, List<Student>>();
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
        logger.info(String.format("\n--Resgistering course...\n\tCode: %d\n\tName: %s\n\tCoordinator: %s.", code, name, coordinator));
        if(courses.get(code)==null) {
            try {
                logger.info(String.format("\n\t----Creating course %d ...", code));
                courses.put(code, new Course(code, name, coordinator));
            } catch(CourseBlankInputException cbie) {
                logger.error("\n\t----CourseBlankInputException(): Can't create course. The call contains blank or null inputs or the code is not a positive integer.");
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
        logger.info(String.format("\n--Registering student...\n\tIdentification: %d\n\tName: %s\n\tE-mail: %s.", id, name, email));
        if(students.get(id)==null) {
            try {
                logger.info(String.format("\n\t----Creating student %d ...", id));
                students.put(id, new Student(id, name, email));
            } catch(StudentBlankInputException sbie) {
                logger.error("\n\t----StudentBlankInputException(): Can't create student. The call contains blank or null inputs or the id is not a positive integer.");
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
    /**
     * {@summary It enrolls a student in a course in a sorted way.}
     * @param course_code Specifies the course's unique code.
     * @param student_id Specifies the student's unique identifier.
     * @param students_enrolled Specifies the list of students already enrolled in the course.
     */
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
    /**
     * 
     * @param student_id Specifies the student's unique identifier.
     * @param students_enrolled Specifies the list of students already enrolled in the course.
     * @return <strong>true</strong> if the student is enrolled in the course and <strong>false</strong> otherwise.
     */
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

    /**
     * 
     */
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

