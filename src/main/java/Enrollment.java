/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Enrollment
 * Student(s) Name(s):
 */

import javax.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {
	@EmbeddedId
	private EnrollmentPK enrollmentPK;
	@ManyToOne
	@JoinColumns( {
	 @JoinColumn(name = "id", insertable = false, updatable = false),
	 @JoinColumn(name = "code", insertable = false, updatable = false)
	})
	private Course course;
	
	@Override public String toString() {
		return "Enrollment{" +
			   "enrollmentPK=" + enrollmentPK +
			   ", course=" + course +
			   '}';
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public EnrollmentPK getEnrollmentPK() {
		return enrollmentPK;
	}
	
	public void setEnrollmentPK(EnrollmentPK enrollmentPK) {
		this.enrollmentPK = enrollmentPK;
	}
}
