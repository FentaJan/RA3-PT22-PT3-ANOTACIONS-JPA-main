package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

/**
 * Clase Car
 *
 * - Representa un coche en el modelo de dominio.
 * - Hereda de Vehicle.
 * - Sin anotaciones JPA (Db4o persiste la jerarquía completa)
 */
public class Car extends Vehicle {

	private static final long serialVersionUID = 1L;
	/*
	 * Necesario porque Vehicle (normalmente) implementa Serializable.
	 * Ayuda a la compatibilidad en serialización.
	 */

	// ===== ATRIBUTOS PROPIOS DE CAR =====

	private Integer numDoors;
	private Double trunkCapacity;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 * Hibernate lo utiliza para instanciar el objeto mediante reflexión.
	 */
	public Car() {
	}

	/**
	 * Constructor sin precio.
	 * Llama al constructor de Vehicle sin el atributo price.
	 */
	public Car(String brand,
			   String model,
			   Integer year,
			   String licensePlate,
			   Integer numDoors,
			   Double trunkCapacity) {

		super(brand, model, year, licensePlate); // Inicializa atributos comunes
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	/**
	 * Constructor con precio.
	 * Inicializa tanto los atributos de Vehicle como los específicos de Car.
	 */
	public Car(String brand,
			   String model,
			   Integer year,
			   String licensePlate,
			   Double price,
			   Integer numDoors,
			   Double trunkCapacity) {

		super(brand, model, year, licensePlate, price);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	// ===== GETTERS Y SETTERS =====

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

	// ===== MÉTODOS =====

	/**
	 * Representación en texto del objeto.
	 * Útil para depuración y logs.
	 * Incluye campos heredados de Vehicle y propios de Car.
	 */
	@Override
	public String toString() {
		return "Car [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", price=" + this.getPrice()
				+ ", numDoors=" + numDoors
				+ ", trunkCapacity=" + trunkCapacity
				+ "]";
	}
}
