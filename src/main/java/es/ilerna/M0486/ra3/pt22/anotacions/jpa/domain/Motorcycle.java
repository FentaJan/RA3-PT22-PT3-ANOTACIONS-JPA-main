package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

/**
 * Clase Motorcycle
 *
 * - Representa una motocicleta en el modelo de dominio.
 * - Hereda de la clase Vehicle.
 * - Sin anotaciones JPA (Db4o persiste la jerarquía completa)
 */
public class Motorcycle extends Vehicle {

	private static final long serialVersionUID = 1L;
	// Necesario por herencia de Serializable desde Vehicle

	// ===== ATRIBUTOS PROPIOS DE MOTORCYCLE =====

	private Integer engineCc;
	private Boolean hasSidecar;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 * Hibernate lo utiliza para crear instancias mediante reflexión.
	 */
	public Motorcycle() {
	}

	/**
	 * Constructor sin precio.
	 * Inicializa los atributos comunes (Vehicle)
	 * y los específicos de Motorcycle.
	 */
	public Motorcycle(String brand,
					  String model,
					  Integer year,
					  String licensePlate,
					  Integer engineCc,
					  Boolean hasSidecar) {

		super(brand, model, year, licensePlate);
		this.engineCc = engineCc;
		this.hasSidecar = hasSidecar;
	}

	/**
	 * Constructor con precio.
	 * Llama al constructor de Vehicle que incluye el precio.
	 */
	public Motorcycle(String brand,
					  String model,
					  Integer year,
					  String licensePlate,
					  Double price,
					  Integer engineCc,
					  Boolean hasSidecar) {

		super(brand, model, year, licensePlate, price);
		this.engineCc = engineCc;
		this.hasSidecar = hasSidecar;
	}

	// ===== GETTERS Y SETTERS =====

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

	// ===== MÉTODOS =====

	/**
	 * Representación corta del objeto.
	 * Formato: Motorcycle (brand, year, price, hasSidecar)
	 */
	public String toStringShort() {
		return "Motorcycle (" + this.getBrand()
				+ ", " + this.getYear()
				+ ", " + this.getPrice()
				+ ", " + hasSidecar + ")";
	}

	/**
	 * Representación en texto del objeto.
	 * Incluye atributos heredados de Vehicle y propios de Motorcycle.
	 */
	@Override
	public String toString() {
		return "Motorcycle [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", price=" + this.getPrice()
				+ ", engineCc=" + engineCc
				+ ", hasSidecar=" + hasSidecar
				+ "]";
	}
}
