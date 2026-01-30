package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {

	private static final long serialVersionUID = 1L;

	@Column(name = "enrollment_number")
	private String enrollmentNumber;

	@Column(name = "major")
	private String major;

	// Constructores
	public Student() {
	}

	public Student(String name, String email, String phone, String enrollmentNumber, String major) {
		super(name, email, phone);
		this.enrollmentNumber = enrollmentNumber;
		this.major = major;
	}

	// Getters y Setters
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [personId=" + this.getPersonId() + ", name=" + this.getName() + ", email=" + this.getEmail()
				+ ", phone=" + this.getPhone() + ", enrollmentNumber=" + enrollmentNumber + ", major=" + major + "]";
	}
}
