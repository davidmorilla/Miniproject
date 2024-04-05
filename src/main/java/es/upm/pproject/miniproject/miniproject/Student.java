package es.upm.pproject.miniproject.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class allows you to create a student object. It contains the next attributes:
 * <br><li> <strong>Identification</strong>, which must be a unique integer >0.
 * <br><li> <strong>Name</strong>, which must not be a blank or null string.
 * <br><li> <strong>E-mail</strong>, which must not be a blank or null string, must not end with a '.' and must contain a '@'.
 * 
 */
public class Student {
	private int identificationNumber;
	private String name;
	private String emailAddress;
	
	private static final Logger logger = LoggerFactory.getLogger(Student.class);
	
    /**
     * {@summary It creates a new student.}
     * @param identificationNumber Specifies the unique student identifier. Must be a positive integer.
     * @param name Specifies the student's name.
     * @param emailAddress Specifies the student's e-mail address. It must contain a '@' and cannot end with a '.'.
     * @throws StudentBlankInputException when any of the input parameters are blank or null or the id is not a positive integer.
     * @throws EmailFormatException when the email ends with a '.' or does not contain '@'.
     * <br><li> An example of use is provided below: 
     * <br><li> {@code new Student(1, "Student 1", "student1@example.com")} will successfully create the course.
     * <br><li> {@code new Student(-1, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(0, "Student 1", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, "", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, "Student 1", "")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, " ", "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, "Student 1", " ")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, null, "student1@example.com")} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, "Student 1", null)} will throw a {@code StudentBlankInputException()}.
     * <br><li> {@code new Student(1, "Student 1", "student1.example.com")} will throw an {@code EmailFormatException}.
     * <br><li> {@code new Student(1, "Student 1", "student1@example.")} will throw an {@code EmailFormatException}.
     * 
     */
	public Student(int identificationNumber, String name, String emailAddress) throws StudentBlankInputException, EmailFormatException {
		if(name==null || emailAddress==null || identificationNumber <= 0 || name.isBlank() || emailAddress.isBlank()) {
			logger.error("\n\t----StudentBlankInputException(): Can't create student. The call contains blank or null inputs or the id is not a positive integer.");
			throw new StudentBlankInputException();
		}
		else if(!emailAddress.contains("@") || emailAddress.endsWith(".")) {
			logger.error("\n\t----EmailFormatException(): Can't create student. The e-mail is not in the correct format.");
			throw new EmailFormatException();
		}
		this.identificationNumber = identificationNumber;
		this.name = name;
		this.emailAddress = emailAddress;
	}
	
	/**
	 * 
	 * @return An integer corresponding to the student's unique identifier.
	 */
	public int getId() {
		return identificationNumber;
	}
	
	/**
	 * 
	 * @return A string corresponding to the student's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return A string corresponding to the student's e-mail.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	
	
	
	
}
