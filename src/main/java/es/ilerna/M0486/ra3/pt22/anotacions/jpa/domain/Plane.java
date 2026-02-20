package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

/**
 * Clase Plane
 *
 * - Representa un avión en el modelo de dominio.
 * - Hereda de la clase Vehicle.
 * - Sin anotaciones JPA (Db4o persiste la jerarquía completa)
 */
public class Plane extends Vehicle {

	private static final long serialVersionUID = 1L;
	// Necesario porque Vehicle implementa Serializable

	// ===== ATRIBUTOS PROPIOS DE PLANE =====

	private Double maxAltitude;
	private Integer numEngines;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 * Hibernate lo usa para instanciar la entidad por reflexión.
	 */
	public Plane() {
	}

	/**
	 * Constructor sin precio.
	 * Inicializa los atributos comunes (Vehicle)
	 * y los específicos de Plane.
	 */
	public Plane(String brand,
				 String model,
				 Integer year,
				 String licensePlate,
				 Double maxAltitude,
				 Integer numEngines) {

		super(brand, model, year, licensePlate);
		this.maxAltitude = maxAltitude;
		this.numEngines = numEngines;
	}

	/**
	 * Constructor con precio.
	 * Llama al constructor de Vehicle que incluye el precio.
	 */
	public Plane(String brand,
				 String model,
				 Integer year,
				 String licensePlate,
				 Double price,
				 Double maxAltitude,
				 Integer numEngines) {

		super(brand, model, year, licensePlate, price);
		this.maxAltitude = maxAltitude;
		this.numEngines = numEngines;
	}

	// ===== GETTERS Y SETTERS =====

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

	// ===== MÉTODOS =====

	/**
	 * Representación corta del objeto.
	 * Formato: Plane (brand, year, price, maxAltitude, numEngines)
	 */
	public String toStringShort() {
		return "Plane (" + this.getBrand()
				+ ", " + this.getYear()
				+ ", " + this.getPrice()
				+ ", " + maxAltitude
				+ ", " + numEngines + ")";
	}

	/**
	 * Representación en texto del objeto.
	 * Incluye atributos heredados de Vehicle y específicos de Plane.
	 */
	@Override
	public String toString() {
		return "Plane [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", price=" + this.getPrice()
				+ ", maxAltitude=" + maxAltitude
				+ ", numEngines=" + numEngines
				+ "]";
	}
}
