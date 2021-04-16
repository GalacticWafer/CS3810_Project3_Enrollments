/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s):
 */

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.*;
import java.sql.Connection;
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
	public Student getStudent(int studentId) {
		return em.find(Student.class, studentId);
	}
	// TODOd: add the given student entity, returning true/false depending whether the operation was successful or not
	public boolean addStudent(final Student student) {
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(student);
			trans.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}
	}
	// TODOd: return a list of all Course entities
	public List<Course> getCourses() {
		List<Course> courses = em.createQuery("From Course", Course.class).getResultList();
		courses.forEach(x -> session.evict(x));
		return courses;
	}
	// TODOd: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
	public boolean enrollStudent(String courseCode, int studentId) {
		EntityTransaction et = em.getTransaction();
		boolean bool = false;
		try{
			et.begin();
			Student student = em.find(Student.class, studentId);
			Course course = em.find(Course.class, courseCode);
			Enrollment enrollment = new Enrollment(studentId, courseCode);
			em.persist(enrollment);
			et.commit();
			//course.setActual(course.getActual() + 1);
			bool = true;
		}catch(Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		return bool;
	}
	// TODOd: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
	public boolean dropStudent(String courseCode, int studentId) {
		Enrollment enr;
		try {
			enr = em.find(Enrollment.class, Enrollment.getKey(studentId, courseCode));
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		var trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(enr);
			Course course = em.find(Course.class, enr.getCode());
			trans.commit();
			//course.setActual(course.getActual() - 1);
			return true;
		}catch(Exception e) {
			trans.rollback();   
			return false;
		}
	}
	// TODOd: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
	public List<Student> getStudentsEnrolled(String courseCode) { 
		return session.createSQLQuery("CALL list_students(:course_code)").addEntity(Student.class)
    .setParameter("course_code", courseCode).list();
	}
}
