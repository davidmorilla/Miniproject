package es.upm.pproject.miniproject.miniproject;
/**
 * 
 * Thrown when an application attempts to create a course with one or more blank or null fields or a course code <=0.
 *
 */
public class CourseBlankInputException extends Exception {
	
	/**
	 * {@summary Constructs an exception of type CourseBlankInputException}
	 */
   public CourseBlankInputException(){
        super("Error: code, name and coordinator cannot be blank. Code must be >0");
   }
    
}
