package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase Plane - Hereda de Vehicle
 * Utiliza la estrategia JOINED con su propia tabla "plane"
 */
@Entity
@Table(name = "plane")
@PrimaryKeyJoinColumn(name = "vehicle_id")
@DiscriminatorValue("PLANE")
public class Plane extends Vehicle {

	private static final long serialVersionUID = 1L;

	@Column(name = "max_altitude")
	private Double maxAltitude;

	@Column(name = "num_engines")
	private Integer numEngines;

	// Constructores
	public Plane() {
	}

	public Plane(String brand, String model, Integer year, String licensePlate, Double maxAltitude, Integer numEngines) {
		super(brand, model, year, licensePlate);
		this.maxAltitude = maxAltitude;
		this.numEngines = numEngines;
	}

	public Plane(String brand, String model, Integer year, String licensePlate, Double price, Double maxAltitude, Integer numEngines) {
		super(brand, model, year, licensePlate, price);
		this.maxAltitude = maxAltitude;
		this.numEngines = numEngines;
	}

	// Getters y Setters
	public Double getMaxAltitude() {
		return maxAltitude;
	}

	public void setMaxAltitude(Double maxAltitude) {
		this.maxAltitude = maxAltitude;
	}

	public Integer getNumEngines() {
		return numEngines;
	}

	public void setNumEngines(Integer numEngines) {
		this.numEngines = numEngines;
	}

	@Override
	public String toString() {
		return "Plane [vehicleId=" + this.getVehicleId() + ", brand=" + this.getBrand() + ", model=" + this.getModel()
				+ ", year=" + this.getYear() + ", licensePlate=" + this.getLicensePlate() + ", maxAltitude="
				+ maxAltitude + ", numEngines=" + numEngines + "]";
	}
}
