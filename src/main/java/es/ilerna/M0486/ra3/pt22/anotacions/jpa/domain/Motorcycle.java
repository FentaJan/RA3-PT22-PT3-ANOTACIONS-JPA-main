package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase Motorcycle - Hereda de Vehicle
 * Utiliza la estrategia JOINED con su propia tabla "motorcycle"
 */
@Entity
@Table(name = "motorcycle")
@PrimaryKeyJoinColumn(name = "vehicle_id")
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {

	private static final long serialVersionUID = 1L;

	@Column(name = "engine_cc")
	private Integer engineCc;

	@Column(name = "has_sidecar")
	private Boolean hasSidecar;

	// Constructores
	public Motorcycle() {
	}

	public Motorcycle(String brand, String model, Integer year, String licensePlate, Integer engineCc,
			Boolean hasSidecar) {
		super(brand, model, year, licensePlate);
		this.engineCc = engineCc;
		this.hasSidecar = hasSidecar;
	}

	public Motorcycle(String brand, String model, Integer year, String licensePlate, Double price, Integer engineCc,
			Boolean hasSidecar) {
		super(brand, model, year, licensePlate, price);
		this.engineCc = engineCc;
		this.hasSidecar = hasSidecar;
	}

	// Getters y Setters
	public Integer getEngineCc() {
		return engineCc;
	}

	public void setEngineCc(Integer engineCc) {
		this.engineCc = engineCc;
	}

	public Boolean getHasSidecar() {
		return hasSidecar;
	}

	public void setHasSidecar(Boolean hasSidecar) {
		this.hasSidecar = hasSidecar;
	}

	@Override
	public String toString() {
		return "Motorcycle [vehicleId=" + this.getVehicleId() + ", brand=" + this.getBrand() + ", model="
				+ this.getModel() + ", year=" + this.getYear() + ", licensePlate=" + this.getLicensePlate()
				+ ", engineCc=" + engineCc + ", hasSidecar=" + hasSidecar + "]";
	}
}
