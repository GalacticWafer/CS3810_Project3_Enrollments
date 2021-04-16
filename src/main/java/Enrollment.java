/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Enrollment
 * Student(s) Name(s):
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrollments")
public class Enrollment {
	@EmbeddedId
	private EnrollmentPK key;
	public Enrollment(){}
	public Enrollment(int studentId, String courseCode) {
		super();
		this.key = new EnrollmentPK(studentId, courseCode);
	}
	
	public String getCode() { return key.getCode(); }
	public int getId() { return key.getId(); }
	public static Object getKey(int studentId, String courseCode) { return new EnrollmentPK(studentId, courseCode);	}

	public void setCode(String code) { key.setCode(code); }
	public void setId(int id) { key.setId(id); }    
	public void setKey(int studentId, String courseCode){
		key = new EnrollmentPK(studentId, courseCode);
	}

	@Override public String toString() {
		return "Enrollment{" +
			//"enrollmentPK=" + enrollmentPK +
			"course=" + key.getCode() +
			", id=" + key.getId() +
			'}';
	}
}
