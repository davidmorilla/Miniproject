package es.upm.pproject.miniproject.miniproject;

public class CourseBlankInputException extends Exception {
	
   public CourseBlankInputException(){
        super("Error: code, name and coordinator cannot be blank. Code must be >0");
    }
    
}
