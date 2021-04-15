/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s):
 */

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	private EntityManager em;
	private Session session;
	public Controller() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
		em = emf.createEntityManager();
		session = em.unwrap(Session.class);
	}
	public void close() {
		session.close();
	}
	// TODOd: return a Student entity from the given id (or null if the entity does not exist)
	public Student getStudent(int id) {
		return em.find(Student.class, id);
	}
	// TODOd: add the given student entity, returning true/false depending whether the operation was successful or not
	public boolean addStudent(final Student student) {
		
		try {
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}
	// TODOd: return a list of all Course entities
	public List<Course> getCourses() {
		return em.createQuery("SELECT * FROM enrollments.courses", Course.class).getResultList();
	}
	// TODOd: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
	public boolean enrollStudent(String courseCode, int studentId) {
		EntityTransaction et = em.getTransaction();
		boolean bool = false;
		try{
			et.begin();
			Student student = em.find(Student.class, studentId);
			Course course = em.find(Course.class, courseCode);
			Enrollment enrollment = new Enrollment();
			enrollment.setCourse(course);
			EnrollmentPK key = new EnrollmentPK(studentId, courseCode);
			course.getStudents().add(student);
			et.commit();
			bool = true;
		}catch(Exception e) {
			e.printStackTrace();
			et.rollback();
		}finally {
			return bool;
		}
	}
	// TODOd: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
	public boolean dropStudent(String courseCode, int studentId) {
		EnrollmentPK epk = new EnrollmentPK(studentId, courseCode);
		try {
			var enr = em.find(Enrollment.class, epk);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		var trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(epk);
			trans.commit();
			return true;
		}catch(Exception e) {
			trans.rollback();   
			return false;
		}
	}
	// TODOd: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
	public List<Student> getStudentsEnrolled(String course) {
		return em.createQuery("call list_students(" + course + ")").getResultList();
	}
}
