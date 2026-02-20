package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

/**
 * Clase Teacher
 *
 * - Representa un profesor.
 * - Hereda de la clase base Person.
 * - Sin anotaciones JPA (Db4o persiste la jerarquía completa)
 */
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;
	// Heredado de Serializable desde Person

	// ===== ATRIBUTOS PROPIOS DE TEACHER =====

	private String employeeId;
	private String department;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 */
	public Teacher() {
	}

	/**
	 * Constructor completo.
	 * Inicializa los atributos comunes (Person)
	 * y los específicos de Teacher.
	 */
	public Teacher(String name,
				   String email,
				   String phone,
				   String employeeId,
				   String department) {

		super(name, email, phone);   // Inicializa atributos comunes
		this.employeeId = employeeId;
		this.department = department;
	}

	// ===== GETTERS Y SETTERS =====

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

	// ===== MÉTODOS =====

	/**
	 * Representación en texto del profesor.
	 * Incluye campos heredados y específicos.
	 */
	@Override
	public String toString() {
		return "Teacher [personId=" + this.getPersonId()
				+ ", name=" + this.getName()
				+ ", email=" + this.getEmail()
				+ ", phone=" + this.getPhone()
				+ ", employeeId=" + employeeId
				+ ", department=" + department
				+ "]";
	}
}
