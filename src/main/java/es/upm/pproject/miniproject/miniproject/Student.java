package es.upm.pproject.miniproject.miniproject;

public class Student {
	private int identification_number;
	private String name;
	private String email_address;
	
	public Student(int identification_number, String name, String email_address) throws StudentBlankInputException, EmailFormatException {
		if(identification_number <= 0 || name.isBlank() || name.isEmpty()|| name==null || email_address.isBlank()|| email_address.isEmpty()|| email_address==null) {
			throw new StudentBlankInputException();
		}
		else if(!email_address.contains("@") || email_address.endsWith(".")) {
			throw new EmailFormatException();
		}
		this.identification_number = identification_number;
		this.name = name;
		this.email_address = email_address;
	}
	public int getId() {
		return identification_number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail_address() {
		return email_address;
	}
	
	
	
	
	
}
