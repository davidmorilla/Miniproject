package es.upm.pproject.miniproject.miniproject;

public class Student {
	private int identification_number;
	private String name;
	private String email_address;
	public Student(int identification_number, String name, String email_address) {
		if(identification_number <= 0 || name.isBlank() || email_address.isBlank()) {
			//DEVOLVER EXCEPCION
			System.out.println("Error: code, name, coordinator can not be blank");
		}
		else if(!email_address.contains("@") || email_address.endsWith(".")) {
			//DEVOLVER EXCEPCION
			System.out.println("Error: email must contain '@' and can not end with the character '.'");
		}
		this.identification_number = identification_number;
		this.name = name;
		this.email_address = email_address;
	}
	public int getId() {
		return identification_number;
	}
	
	
}
