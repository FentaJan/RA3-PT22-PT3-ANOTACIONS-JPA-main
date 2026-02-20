package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;

/**
 * Clase Vehicle
 *
 * - Clase base para Car, Plane y Motorcycle
 * - Sin anotaciones JPA (Db4o persiste objetos directamente)
 * - Contiene los atributos comunes a todos los vehículos
 * - Define la relación con Person
 */
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;
	// Necesario para entidades JPA serializables

	// ===== ATRIBUTOS =====

	private Integer vehicleId;
	private String brand;
	private String model;
	private Integer year;
	private String licensePlate;
	private Double price;
	private Person owner;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 */
	public Vehicle() {
	}

	/**
	 * Constructor sin precio.
	 * Inicializa los atributos comunes.
	 */
	public Vehicle(String brand,
				   String model,
				   Integer year,
				   String licensePlate) {

		this.brand = brand;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
	}

	/**
	 * Constructor con precio.
	 */
	public Vehicle(String brand,
				   String model,
				   Integer year,
				   String licensePlate,
				   Double price) {

		this.brand = brand;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.price = price;
	}

	// ===== GETTERS Y SETTERS =====

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	// ===== MÉTODOS =====

	/**
	 * Representación en texto del vehículo.
	 * Incluye todos los atributos.
	 */
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId
				+ ", brand=" + brand
				+ ", model=" + model
				+ ", year=" + year
				+ ", licensePlate=" + licensePlate
				+ ", price=" + price
				+ "]";
	}
}
