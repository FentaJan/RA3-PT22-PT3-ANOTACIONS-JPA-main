package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

/**
 * Clase Student
 *
 * - Representa un estudiante.
 * - Hereda de la clase base Person.
 * - Sin anotaciones JPA (Db4o persiste la jerarquía completa)
 */
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	// Necesario por herencia de Serializable desde Person

	// ===== ATRIBUTOS PROPIOS DE STUDENT =====

	private String enrollmentNumber;
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
