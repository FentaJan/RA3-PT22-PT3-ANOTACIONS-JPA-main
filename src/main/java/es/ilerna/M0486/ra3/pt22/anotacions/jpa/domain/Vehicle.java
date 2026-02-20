package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.io.Serializable;

// Importaciones JPA
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
 * Entidad Vehicle
 *
 * - Clase base para Car, Plane y Motorcycle
 * - Usa herencia JPA con estrategia JOINED
 * - Contiene los atributos comunes a todos los vehículos
 * - Define la relación Many-to-One con Person
 */
@Entity                         // Marca la clase como entidad JPA
@Table(name = "vehicle")        // Tabla base de la jerarquía
@Inheritance(strategy = InheritanceType.JOINED)
/*
 * JOINED:
 * - vehicle → atributos comunes
 * - car / plane / motorcycle → atributos específicos
 * - Se realizan JOINs al consultar
 */
@DiscriminatorColumn(
		name = "vehicle_type",
		discriminatorType = DiscriminatorType.STRING
)
/*
 * Columna que indica el subtipo real del vehículo:
 * "CAR", "PLANE", "MOTORCYCLE"
 */
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;
	// Necesario para entidades JPA serializables

	// ===== CLAVE PRIMARIA =====

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * IDENTITY:
	 * - El ID se genera automáticamente en la base de datos
	 */
	@Column(name = "vehicle_id")
	private Integer vehicleId;

	// ===== ATRIBUTOS COMUNES =====

	@Column(name = "brand", nullable = false)
	/*
	 * Marca del vehículo (obligatoria)
	 */
	private String brand;

	@Column(name = "model", nullable = false)
	/*
	 * Modelo del vehículo (obligatorio)
	 */
	private String model;

	@Column(name = "year")
	/*
	 * Año de fabricación
	 */
	private Integer year;

	@Column(name = "price")
	/*
	 * Precio del vehículo
	 */
	private Double price;

	@Column(name = "license_plate", unique = true)
	/*
	 * Matrícula del vehículo.
	 * Se marca como UNIQUE para evitar duplicados.
	 */
	private String licensePlate;

	// ===== RELACIÓN MANY-TO-ONE CON PERSON =====

	@ManyToOne(fetch = FetchType.LAZY)
	/*
	 * Muchos vehículos pueden pertenecer a una persona.
	 * Este es el lado propietario de la relación.
	 */
	@JoinColumn(name = "person_id")
	/*
	 * Clave foránea en la tabla vehicle que apunta a person.person_id
	 */
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
	 * No incluye el owner para evitar cargas LAZY innecesarias.
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
