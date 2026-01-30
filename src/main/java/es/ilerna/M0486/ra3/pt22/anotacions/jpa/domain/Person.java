package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
 * Clase Person - Entidad base para Teacher y Student
 * Utiliza herencia SINGLE_TABLE: toda la información se guarda en una única tabla "person"
 * La columna "person_type" diferencia entre Teacher, Student y Person
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Integer personId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	// Relación One-to-Many con Vehicle
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Vehicle> vehicles = new HashSet<>();

	// Constructores
	public Person() {
	}

	public Person(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	// Getters y Setters
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

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.setOwner(this);
	}

	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
		vehicle.setOwner(null);
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
}
