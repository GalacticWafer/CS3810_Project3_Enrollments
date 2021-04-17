import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
	@Test
	void getStudent() {
		Controller controller = new Controller();
		// Add students
		assertEquals(controller.addStudent(new Student(4, "Student 4 Test")), true);
		// Can't add the same student twice
		assertEquals(controller.addStudent(new Student(4, "Student 4 Test")), false);
		assertEquals(controller.addStudent(new Student(5, "Student 5 Test")), true);
		assertEquals(controller.addStudent(new Student(6, "Student 6 Test")), true);
		
		// Enroll students all in the same class
		int startingEnrolled = (int)controller.getStudentsEnrolled("CS1050").stream().count();
		controller.enrollStudent("CS1050", 4);
		controller.enrollStudent("CS1050", 5);
		controller.enrollStudent("CS1050", 6);
		int afterEnrolled = (int)controller.getStudentsEnrolled("CS1050").stream().count();
		assertEquals(startingEnrolled + 3, afterEnrolled);
		
		// Drop test students
		controller.dropStudent("CS1050", 4);
		controller.dropStudent("CS1050", 5);
		controller.dropStudent("CS1050", 6);
		int removedCount = (int)controller.getStudentsEnrolled("CS1050").stream().count();
		assertEquals(startingEnrolled, removedCount);
		
		// Remove test students
		controller.removeStudent(4);
		controller.removeStudent(5);
		controller.removeStudent(6);
	}
}
