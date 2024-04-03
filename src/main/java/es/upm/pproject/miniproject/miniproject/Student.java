package es.upm.pproject.miniproject.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Student {
	private int identificationNumber;
	private String name;
	private String emailAddress;
	
	private static final Logger logger = LoggerFactory.getLogger(Student.class);
	
	public Student(int identificationNumber, String name, String emailAddress) throws StudentBlankInputException, EmailFormatException {
		if(identificationNumber <= 0 || name.isBlank() || emailAddress.isBlank()) {
			logger.error("\n\t----StudentBlankInputException(): Can't create student. The call contains blank or null inputs or the id is not a positive integer.");
			throw new StudentBlankInputException();
		}
		else if(!emailAddress.contains("@") || emailAddress.endsWith(".")) {
			logger.error("\n\t----EmailFormatException(): Can't register student. The e-mail is not in the correct format.");
			throw new EmailFormatException();
		}
		this.identificationNumber = identificationNumber;
		this.name = name;
		this.emailAddress = emailAddress;
	}
	public int getId() {
		return identificationNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	
	
	
	
}
