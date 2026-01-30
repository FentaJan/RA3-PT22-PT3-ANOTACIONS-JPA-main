package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Clase Teacher - Hereda de Person
 * Utiliza la estrategia SINGLE_TABLE con discriminador "TEACHER"
 */
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "department")
	private String department;

	// Constructores
	public Teacher() {
	}

	public Teacher(String name, String email, String phone, String employeeId, String department) {
		super(name, email, phone);
		this.employeeId = employeeId;
		this.department = department;
	}

	// Getters y Setters
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Teacher [personId=" + this.getPersonId() + ", name=" + this.getName() + ", email=" + this.getEmail()
				+ ", phone=" + this.getPhone() + ", employeeId=" + employeeId + ", department=" + department + "]";
	}
}
