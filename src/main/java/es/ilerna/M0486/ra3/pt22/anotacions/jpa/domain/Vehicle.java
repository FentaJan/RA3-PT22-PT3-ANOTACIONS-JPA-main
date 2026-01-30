package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase Vehicle - Entidad base para Car, Plane y Motorcycle
 * Utiliza herencia JOINED: tablas separadas (car, plane, motorcycle) unidas a vehicle
 * La relación Many-to-One con Person se establece aquí
 */
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private Integer vehicleId;

	@Column(name = "brand", nullable = false)
	private String brand;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "year")
	private Integer year;

	@Column(name = "price")
	private Double price;

	@Column(name = "license_plate", unique = true)
	private String licensePlate;

	// Relación Many-to-One con Person
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person owner;

	// Constructores
	public Vehicle() {
	}

	public Vehicle(String brand, String model, Integer year, String licensePlate) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
	}

	public Vehicle(String brand, String model, Integer year, String licensePlate, Double price) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.price = price;
	}

	// Getters y Setters
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

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", brand=" + brand + ", model=" + model + ", year=" + year
			+ ", licensePlate=" + licensePlate + ", price=" + price + "]";
	}
}
