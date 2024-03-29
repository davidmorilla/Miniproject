package es.upm.pproject.miniproject.miniproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AppTest {
	private EnrollmentManager man;
	
	@BeforeEach
	void runBeforeEach () {
		man = new EnrollmentManager();
	}

	@DisplayName ("Tests related to registering courses in the system")
	@Nested
	class RegisterCoursesTest {
		
		@Test
		public void testBlankInput() {
	        assertThrows(CourseBlankInputException.class, () -> man.registerCourse(1, "", "Coordinator1"));
	    }
		
		@Test
		public void testBlankInput2() {
	        assertThrows(CourseBlankInputException.class, () -> man.registerCourse(15632, "Course1", "  "));
	    }
		
		@Test
		public void testBlankInput3() {
	        assertThrows(CourseBlankInputException.class, () -> man.registerCourse(0, "Course1", "Coordinator1"));
	    }
		
		@Test
		public void testBlankInput4() {
	        assertThrows(CourseBlankInputException.class, () -> man.registerCourse(-123, "Course1", "Coordinator1"));
	    }
		
		@Test
	    public void testEmptyList() throws CourseAlreadyExistsException, CourseBlankInputException {
	        man.registerCourse(1, "Course1", "Coordinator1");
	        assertFalse(man.getCourses().isEmpty());
	    }
		
		@Test
		public void testRegisteredCourse() throws CourseAlreadyExistsException, CourseBlankInputException {
	        man.registerCourse(1, "Course1", "Coordinator1");
	        assertThrows(CourseAlreadyExistsException.class, () -> man.registerCourse(1, "Course1", "Coordinator1"));
	        assertThrows(CourseAlreadyExistsException.class, () -> man.registerCourse(1, "OtherName", "Coordinator1"));
	    }
		
		@Test
		public void testRegisterCourses() throws CourseAlreadyExistsException, CourseBlankInputException {
			for(int i = 1; i <= 100; i++) {
				man.registerCourse(i, "Course" + i, "Coordinator" + i);
			}
			assertEquals(100, man.getCourses().size());
		}
	}
	
	@DisplayName ("Tests related to registering students in the system")
	@Nested
	class RegisterStudentsTest {
		
		@Test
		public void testBlankInput() {
	        assertThrows(StudentBlankInputException.class, () -> man.registerStudent(1, "", "student1@gmail.com"));
	    }
		
		@Test
		public void testBlankInput2() {
	        assertThrows(StudentBlankInputException.class, () -> man.registerStudent(100, "Student1", "  "));
	    }
		
		@Test
		public void testBlankInput3() {
	        assertThrows(StudentBlankInputException.class, () -> man.registerStudent(0, "Student1", "student1@gmail.com"));
	    }
		
		@Test
		public void testEmailFormat() {
	        assertThrows(EmailFormatException.class, () -> man.registerStudent(1, "Student1", "student1gmail.com"));
	    }
		
		@Test
		public void testEmailFormat2() {
	        assertThrows(EmailFormatException.class, () -> man.registerStudent(1, "Student1", "student1@gmail."));
	    }
		
		
		@Test
	    public void testEmptyList() throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
	        man.registerStudent(1, "Student1", "student1@gmail.com");
	        assertFalse(man.getStudents().isEmpty());
	    }
		
		@Test
		public void testRegisteredStudent() throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
	        man.registerStudent(1, "Student1", "student1@gmail.com");
	        man.registerStudent(2, "Student2", "student2@gmail.com");
	        man.registerStudent(3, "Student3", "student3@gmail.com");
	        assertThrows(StudentAlreadyExistsException.class, () -> man.registerStudent(2, "Student2", "student2@gmail.com"));
	        assertThrows(StudentAlreadyExistsException.class, () -> man.registerStudent(2, "OtherName", "student2@gmail.com"));
	    }
		
		@Test
		public void testRegisterStudents() throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
			for(int i = 1; i <= 100; i++) {
				man.registerStudent(i, "Student" + i, "Student" + i + "@gmail.com");
			}
			assertEquals(100, man.getStudents().size());
		}
	}
	
	
	@DisplayName ("Tests related to enrolling students in courses")
	@Nested
	class EnrollTests {
		
		@Test
		public void testStudentNotRegisteredBeforeEnroll() throws CourseAlreadyExistsException, CourseBlankInputException {
			man.registerCourse(1, "Course1", "Coordinator1");
			assertThrows(MissingStudentException.class, () -> man.enroll(1, 1));
		}
		
		@Test
		public void testCourseNotRegisteredBeforeEnroll() throws StudentAlreadyExistsException, StudentBlankInputException, EmailFormatException {
			man.registerStudent(1, "Student1", "student1@gmail.com");
			assertThrows(MissingCourseException.class, () -> man.enroll(1, 1));
		}
		
		@Test
		public void testEnrollSuccess() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.enroll(1, 1);
			
			assertNotNull(man.getStudentsEnrolledInCourse(1));
		}
		
		@Test
		public void testEnrollSuccess2() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.registerStudent(2, "Student2", "student2@gmail.com");
			man.registerStudent(3, "Student3", "student3@gmail.com");
			
			man.enroll(1, 1);
			man.enroll(1, 2);
			man.enroll(1, 3);
			
			assertEquals(3, man.getStudentsEnrolledInCourse(1).size());
		}
		
		@Test
		public void testEnrollSuccess3() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			man.registerCourse(10, "Course10", "Coordinator10");
			
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.registerStudent(2, "Student2", "student2@gmail.com");
			man.registerStudent(3, "Student3", "student3@gmail.com");
			
			man.enroll(1, 1);
			man.enroll(1, 2);
			man.enroll(1, 3);
			man.enroll(10, 1);
			man.enroll(10, 2);
			
			assertEquals(3, man.getStudentsEnrolledInCourse(1).size());
			assertEquals(2, man.getStudentsEnrolledInCourse(10).size());
		}
		
		@Test
		public void testMaxCapacity() throws Exception{
			man.registerCourse(100, "Course100", "Coordinator100");
			int i;
			for(i = 1; i <= 50; i++) {
				man.registerStudent(i, "Student" + i, "student" + i + "@gmail.com");
				man.enroll(100, i);
			}
			assertEquals(50, man.getStudentsEnrolledInCourse(100).size());
			
		}
		
		@Test
		public void testExceptionCapacity() throws Exception{
			man.registerCourse(100, "Course100", "Coordinator100");
			int i;
			for(i = 1; i <= 50; i++) {
				man.registerStudent(i, "Student" + i, "student" + i + "@gmail.com");
				man.enroll(100, i);
			}
			man.registerStudent(51, "Student51", "student51@gmail.com");
			assertThrows(FullCourseException.class, () -> man.enroll(100, 51));
			
		}
		
		@Test
		public void testEnrollDifferentCourses() throws Exception {
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.registerCourse(1, "Course1", "Coordinator1");
			man.registerCourse(2, "Course2", "Coordinator2");
			
			man.enroll(1, 1);
			man.enroll(2, 1);
			
			assertEquals(1, man.getStudentsEnrolledInCourse(1).size());
			assertEquals(1, man.getStudentsEnrolledInCourse(2).size());
		}
		
		@Test
		public void testEnrollTwice() throws Exception {
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.registerCourse(1, "Course1", "Coordinator1");
			
			man.enroll(1, 1);
			
			assertThrows(StudentAlreadyEnrolledException.class, () -> man.enroll(1, 1));
		}
	}
	
	@DisplayName ("Tests related to students enrolled in a course")
	@Nested
	class StudentsEnrolledInCourseTests {
		@Test
		public void testMissingCourseException() {
			assertThrows(MissingCourseException.class, () -> man.getStudentsEnrolledInCourse(2121));
		}
		
		@Test
		public void testGetStudentsEnrolled() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			
			assertEquals(0, man.getStudentsEnrolledInCourse(1).size());
		}
		
		@Test
		public void testGetStudentsEnrolled2() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			man.registerStudent(1, "Student1", "student1@gmail.com");
			
			man.enroll(1, 1);
			
			assertEquals(1, man.getStudentsEnrolledInCourse(1).get(0).getId());
			assertEquals("Student1", man.getStudentsEnrolledInCourse(1).get(0).getName());
		}
		
		@Test
		public void testOrderedtudents() throws Exception {
			man.registerCourse(1, "Course1", "Coordinator1");
			
			man.registerStudent(1, "Student1", "student1@gmail.com");
			man.registerStudent(2, "Student2", "student2@gmail.com");
			man.registerStudent(3, "Student3", "student3@gmail.com");
			man.registerStudent(4, "Student4", "student4@gmail.com");
			
			man.enroll(1, 2);
			man.enroll(1, 4);
			man.enroll(1, 3);
			man.enroll(1, 1);
			
			List<Student> res = man.getStudentsEnrolledInCourse(1);
			
			assertTrue((res.get(0).getId() < res.get(1).getId()) &&
					   (res.get(1).getId() < res.get(2).getId()) &&
					   (res.get(2).getId() < res.get(3).getId()));
		}
	}
	
	

}
