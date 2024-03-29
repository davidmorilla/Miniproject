package es.upm.pproject.miniproject.miniproject;

public class StudentBlankInputException extends Exception {
	
	public StudentBlankInputException(){
		super("Error: id_number, name and email cannot be blank. id_number must be >0");
	}    
}
