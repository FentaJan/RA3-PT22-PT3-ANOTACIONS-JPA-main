package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

// Importaciones JPA
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entidad Student
 *
 * - Representa un estudiante.
 * - Hereda de la clase base Person.
 * - Usa herencia JPA con estrategia SINGLE_TABLE.
 * - Se almacena en la misma tabla "person".
 */
@Entity                         // Marca la clase como entidad JPA
@DiscriminatorValue("STUDENT")
/*
 * Valor que se guarda en la columna person_type
 * para indicar que este registro corresponde a un Student
 */
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	// Necesario por herencia de Serializable desde Person

	// ===== ATRIBUTOS PROPIOS DE STUDENT =====

	@Column(name = "enrollment_number")
	/*
	 * Número de matrícula del estudiante.
	 * Solo se usa cuando person_type = 'STUDENT'
	 */
	private String enrollmentNumber;

	@Column(name = "major")
	/*
	 * Carrera o especialidad que estudia el estudiante.
	 */
	private String major;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 */
	public Student() {
	}

	/**
	 * Constructor completo.
	 * Inicializa los atributos heredados (Person)
	 * y los específicos de Student.
	 */
	public Student(String name,
				   String email,
				   String phone,
				   String enrollmentNumber,
				   String major) {

		super(name, email, phone);   // Inicializa Person
		this.enrollmentNumber = enrollmentNumber;
		this.major = major;
	}

	// ===== GETTERS Y SETTERS =====

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

	// ===== MÉTODOS =====

	/**
	 * Representación en texto del estudiante.
	 * Incluye campos heredados y propios.
	 */
	@Override
	public String toString() {
		return "Student [personId=" + this.getPersonId()
				+ ", name=" + this.getName()
				+ ", email=" + this.getEmail()
				+ ", phone=" + this.getPhone()
				+ ", enrollmentNumber=" + enrollmentNumber
				+ ", major=" + major
				+ "]";
	}
}
