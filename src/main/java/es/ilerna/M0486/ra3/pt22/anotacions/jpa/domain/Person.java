package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// Importaciones JPA
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad Person
 *
 * - Clase base para Teacher y Student
 * - Usa herencia SINGLE_TABLE
 * - Todos los tipos de personas se almacenan en la tabla "person"
 * - La columna "person_type" distingue el subtipo
 */
@Entity                         // Marca la clase como entidad JPA
@Table(name = "person")         // Tabla única para toda la jerarquía
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/*
 * SINGLE_TABLE:
 * - Una sola tabla para Person, Teacher y Student
 * - Columnas extra pueden ser NULL según el subtipo
 */
@DiscriminatorColumn(
		name = "person_type",
		discriminatorType = DiscriminatorType.STRING
)
/*
 * Columna que indica el tipo real de la entidad:
 * por ejemplo: "STUDENT", "TEACHER"
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	// Requerido para entidades serializables

	// ===== CLAVE PRIMARIA =====

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * IDENTITY:
	 * - La base de datos genera el ID (AUTO_INCREMENT)
	 */
	@Column(name = "person_id")
	private Integer personId;

	// ===== ATRIBUTOS BÁSICOS =====

	@Column(name = "name", nullable = false)
	/*
	 * Nombre obligatorio (NOT NULL en la BD)
	 */
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	// ===== RELACIÓN ONE-TO-MANY CON VEHICLE =====

	@OneToMany(
			mappedBy = "owner",          // Campo en Vehicle que mantiene la FK
			cascade = CascadeType.ALL,   // Propaga persist, remove, update, etc.
			fetch = FetchType.LAZY       // Carga diferida (recomendado)
	)
	/*
	 * Relación bidireccional:
	 * - Una persona puede tener muchos vehículos
	 * - Vehicle tiene la FK (ManyToOne)
	 */
	private Set<Vehicle> vehicles = new HashSet<>();

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 */
	public Person() {
	}

	/**
	 * Constructor de conveniencia.
	 */
	public Person(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	// ===== GETTERS Y SETTERS =====

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	// ===== MÉTODOS DE GESTIÓN DE LA RELACIÓN =====

	/**
	 * Añade un vehículo a la persona y
	 * sincroniza ambos lados de la relación.
	 */
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);     // lado OneToMany
		vehicle.setOwner(this);    // lado ManyToOne (FK)
	}

	/**
	 * Elimina un vehículo de la persona
	 * y rompe la relación bidireccional.
	 */
	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
		vehicle.setOwner(null);
	}

	// ===== MÉTODOS =====

	@Override
	public String toString() {
		return "Person [personId=" + personId
				+ ", name=" + name
				+ ", email=" + email
				+ ", phone=" + phone
				+ "]";
	}
}
