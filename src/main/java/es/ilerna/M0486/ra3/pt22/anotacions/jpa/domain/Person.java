package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Person
 *
 * - Clase base para Teacher y Student
 * - Sin anotaciones JPA (Db4o persiste objetos directamente)
 * - Todos los tipos de personas se heredan de esta clase
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	// Requerido para entidades serializables

	// ===== ATRIBUTOS =====

	private Integer personId;
	private String name;
	private String email;
	private String phone;
	private List<Vehicle> vehicles = new ArrayList<>();

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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
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
