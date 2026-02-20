package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

// Importaciones JPA
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidad Plane
 *
 * - Representa un avión en el modelo de dominio.
 * - Hereda de la clase Vehicle.
 * - Usa herencia JPA con estrategia JOINED.
 * - Tiene su propia tabla "plane" relacionada con "vehicle".
 */
@Entity                         // Marca la clase como entidad JPA
@Table(name = "plane")          // Tabla específica de la subclase
@PrimaryKeyJoinColumn(name = "vehicle_id")
/*
 * Indica que:
 * - plane.vehicle_id es la clave primaria
 * - y también clave foránea que referencia a vehicle.id
 */
@DiscriminatorValue("PLANE")
/*
 * Valor almacenado en la columna discriminadora
 * (definida en Vehicle) para identificar este tipo de vehículo
 */
public class Plane extends Vehicle {

	private static final long serialVersionUID = 1L;
	// Necesario porque Vehicle implementa Serializable

	// ===== ATRIBUTOS PROPIOS DE PLANE =====

	@Column(name = "max_altitude")
	/*
	 * Altitud máxima que puede alcanzar el avión.
	 * Normalmente medida en metros o pies (según diseño).
	 */
	private Double maxAltitude;

	@Column(name = "num_engines")
	/*
	 * Número de motores del avión.
	 */
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
	 * Representación en texto del objeto.
	 * Incluye atributos heredados y específicos.
	 */
	@Override
	public String toString() {
		return "Plane [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", maxAltitude=" + maxAltitude
				+ ", numEngines=" + numEngines
				+ "]";
	}
}
