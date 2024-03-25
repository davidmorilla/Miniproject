package es.upm.pproject.miniproject.miniproject;

public class Course {
	private int code;
	private String name;
	private String coordinator;
	
	public Course(int code, String name, String coordinator) {
		if(code <= 0 || name.isBlank() || coordinator.isBlank()) {
			//DEVOLVER EXCEPCION
			System.out.println("Error: code, name, coordinator can not be blank");
		}
		this.code = code;
		this.name = name;
		this.coordinator = coordinator;
	}
}
