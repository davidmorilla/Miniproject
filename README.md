# Mini-Project: Managing University Courses and Students

## Authors

This project has been implemented by **Iván Moratalla Rivera** and **David Morilla Sorlí**.

## Description

The goal of this mini-project is the development of a Java program to manage the students that are
matriculated in the different courses offered by a university.

## Interface Description

The `InterfaceEnrollmentManager` interface provides the following public methods:

### `void registerCourse(int code, String name, String coordinator)`

Registers a new course with the specified code, name, and coordinator in the system.

- **Parameters:**
  - `code` (int): Specifies the unique course identifier. Must be a positive integer.
  - `name` (String): Specifies the name of the course.
  - `coordinator` (String): Specifies the name of the person coordinating the course.
- **Exceptions:**
  - `CourseAlreadyExistsException`: When the course's code is already registered in the database.
  - `CourseBlankInputException`: When any of the input parameters are blank, null, or the code is not a positive integer.

### `void registerStudent(int id, String name, String email)`

Registers a new student with the specified id, name, and email in the system.

- **Parameters:**
  - `id` (int): Specifies the unique student identifier. Must be a positive integer.
  - `name` (String): Specifies the student's name.
  - `email` (String): Specifies the student's email address. It must contain an '@' and cannot end with a '.'.
- **Exceptions:**
  - `StudentAlreadyExistsException`: When the student's ID or email is already registered in the database.
  - `StudentBlankInputException`: When any of the input parameters are blank, null, or the ID is not a positive integer.
  - `EmailFormatException`: When the email address format is incorrect.

### `void enroll(int courseCode, int studentId)`

Enrolls a student in a course.

- **Parameters:**
  - `courseCode` (int): Specifies the unique course identifier of an already registered course.
  - `studentId` (int): Specifies the unique student identifier of an already registered student.
- **Exceptions:**
  - `StudentAlreadyEnrolledException`: When the student is already enrolled in the course.
  - `FullCourseException`: When there are already 50 students enrolled in the course.
  - `MissingStudentException`: When the student's unique identifier is not registered in the database.
  - `MissingCourseException`: When the course's unique code is not registered in the database.

### `List<Student> getStudentsEnrolledInCourse(int course)`

Returns a sorted list of students enrolled in the specified course.

- **Parameters:**
  - `course` (int): Specifies the course's unique code.
- **Exceptions:**
  - `MissingCourseException`: When the course is not registered in the system.

### `void cancelEnrollment(int courseCode, int studentId)`

Cancels a student's enrollment in a course.

- **Parameters:**
  - `courseCode` (int): Specifies the course's unique code.
  - `studentId` (int): Specifies the student's unique identifier.
- **Exceptions:**
  - `StudentNotEnrolledException`: When the student is not enrolled in the course.
  - `MissingStudentException`: When the student is not registered in the system.
  - `MissingCourseException`: When the course is not registered in the system.

### `void restartCourse(int courseCode)`

Restarts a course, canceling all active enrollments.

- **Parameters:**
  - `courseCode` (int): Specifies the course's unique code.
- **Exceptions:**
  - `MissingCourseException`: When the course is not registered in the system.

### `Collection<Student> getStudents()`

Returns a list of all registered students.

### `Collection<Course> getCourses()`

Returns a list of all registered courses.

***

## Running test suite

To execute the test suite for the project, follow these steps:

``` bash
git clone [project_repository_url]
cd miniproject
mvn test
```

## Usage

To use the provided functionalities, instantiate an object implementing the `InterfaceEnrollmentManager` interface and call the appropriate methods.

```java
// Example usage:
EnrollmentManager manager = new EnrollmentManager();
// Now you can use all the public methods
manager.registerCourse(1, "Computer Science 101", "Coordinator 1");
manager.registerStudent(1, "Student 1", "student1@example.com");
manager.enroll(1, 1);
List<Student> enrolledStudents = manager.getStudentsEnrolledInCourse(1);
// Perform other operations as needed...

```

## Dependencies

This project requires JUnit 5.6 for testing purposes and SLF4J for logging.
