package es.upm.pproject.miniproject.miniproject;

public class BlankInputException extends Exception {
	
   public BlankInputException(){
        super("Error: code, name or coordinator cannot be blank");
    }
    
}
