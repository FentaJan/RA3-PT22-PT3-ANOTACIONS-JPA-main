package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase Car - Hereda de Vehicle
 * Utiliza la estrategia JOINED con su propia tabla "car"
 */
@Entity
@Table(name = "car")
@PrimaryKeyJoinColumn(name = "vehicle_id")
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

	private static final long serialVersionUID = 1L;

	@Column(name = "num_doors")
	private Integer numDoors;

	@Column(name = "trunk_capacity")
	private Double trunkCapacity;

	// Constructores
	public Car() {
	}

	public Car(String brand, String model, Integer year, String licensePlate, Integer numDoors, Double trunkCapacity) {
		super(brand, model, year, licensePlate);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	public Car(String brand, String model, Integer year, String licensePlate, Double price, Integer numDoors, Double trunkCapacity) {
		super(brand, model, year, licensePlate, price);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	// Getters y Setters
	public Integer getNumDoors() {
		return numDoors;
	}

	public void setNumDoors(Integer numDoors) {
		this.numDoors = numDoors;
	}

	public Double getTrunkCapacity() {
		return trunkCapacity;
	}

	public void setTrunkCapacity(Double trunkCapacity) {
		this.trunkCapacity = trunkCapacity;
	}

	@Override
	public String toString() {
		return "Car [vehicleId=" + this.getVehicleId() + ", brand=" + this.getBrand() + ", model=" + this.getModel()
				+ ", year=" + this.getYear() + ", licensePlate=" + this.getLicensePlate() + ", numDoors=" + numDoors
				+ ", trunkCapacity=" + trunkCapacity + "]";
	}
}
