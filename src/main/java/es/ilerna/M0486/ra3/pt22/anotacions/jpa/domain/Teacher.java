package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

// Importaciones JPA
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entidad Teacher
 *
 * - Representa un profesor.
 * - Hereda de la clase base Person.
 * - Usa herencia JPA con estrategia SINGLE_TABLE.
 * - Se diferencia mediante el discriminador "TEACHER".
 */
@Entity                         // Marca la clase como entidad JPA
@DiscriminatorValue("TEACHER")
/*
 * Valor que se almacena en la columna person_type
 * para indicar que este registro corresponde a un Teacher
 */
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;
	// Heredado de Serializable desde Person

	// ===== ATRIBUTOS PROPIOS DE TEACHER =====

	@Column(name = "employee_id")
	/*
	 * Identificador del empleado (profesor).
	 * Solo se utiliza cuando person_type = 'TEACHER'
	 */
	private String employeeId;

	@Column(name = "department")
	/*
	 * Departamento al que pertenece el profesor.
	 */
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
