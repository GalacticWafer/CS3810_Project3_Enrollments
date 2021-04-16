import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import org.junit.*;

public class TestController {
	/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s):
 */
	private EntityManager em;
	private Session session;
	private static Controller controller = new Controller();
	public TestController(){}
	
	@Test
	public void TestGetStudent(int studentId) {
	}


	@Test
	public void TestAddStudent(final Student student) {

	}

	@Test
	public void TestGetCourses() {
	}

	@Test
	public void TestEnrollStudent(String courseCode, int studentId) {
	}

	@Test
	public void TestDropStudent(String courseCode, int studentId) {
	}

	@Test
	public void TestGetStudentsEnrolled(String courseCode) { 
	}

}
