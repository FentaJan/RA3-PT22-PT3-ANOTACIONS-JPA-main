package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

// Importaciones JPA
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidad Motorcycle
 *
 * - Representa una motocicleta en el modelo de dominio.
 * - Hereda de la clase Vehicle.
 * - Usa herencia JPA con estrategia JOINED.
 * - Tiene su propia tabla "motorcycle" relacionada con "vehicle".
 */
@Entity                         // Marca la clase como entidad JPA
@Table(name = "motorcycle")     // Tabla específica para la subclase
@PrimaryKeyJoinColumn(name = "vehicle_id")
/*
 * Indica que:
 * - motorcycle.vehicle_id es la clave primaria
 * - y a la vez clave foránea que referencia a vehicle.id
 */
@DiscriminatorValue("MOTORCYCLE")
/*
 * Valor almacenado en la columna discriminadora
 * (definida en Vehicle) para identificar este tipo de vehículo
 */
public class Motorcycle extends Vehicle {

	private static final long serialVersionUID = 1L;
	// Necesario por herencia de Serializable desde Vehicle

	// ===== ATRIBUTOS PROPIOS DE MOTORCYCLE =====

	@Column(name = "engine_cc")
	/*
	 * Cilindrada del motor (en centímetros cúbicos).
	 * Se guarda en la columna engine_cc de la tabla motorcycle.
	 */
	private Integer engineCc;

	@Column(name = "has_sidecar")
	/*
	 * Indica si la motocicleta tiene sidecar.
	 * true  -> tiene sidecar
	 * false -> no tiene sidecar
	 */
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
	 * Representación en texto del objeto.
	 * Incluye atributos heredados y propios.
	 */
	@Override
	public String toString() {
		return "Motorcycle [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", engineCc=" + engineCc
				+ ", hasSidecar=" + hasSidecar
				+ "]";
	}
}
